package agency.five.codebase.android.five_project.data.competitionRepository

import agency.five.codebase.android.five_project.data.database.DbFollowedCompetition
import agency.five.codebase.android.five_project.data.database.FollowedCompetitionDao
import agency.five.codebase.android.five_project.model.Competition
import agency.five.codebase.android.five_project.model.CompetitionDetails
import agency.five.codebase.android.five_project.model.Team
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

const val FIRESTORE_COLLECTION_COMPETITIONS = "competitions"
const val FIRESTORE_COLLECTION_TEAMS = "teams"

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class CompetitionRepositoryImpl(
    private val firestore: FirebaseFirestore,
    private val bgDispatcher: CoroutineDispatcher,
    private val competitionDao: FollowedCompetitionDao
) : CompetitionRepository {
    private val competitions: Flow<List<Competition>> = flow {
        val competitions = mutableListOf<Competition>()
        val dbCompetitions = firestore.collection(FIRESTORE_COLLECTION_COMPETITIONS)
            .get()
            .addOnSuccessListener{result->
            for(competition in result){
                val tempCompetition = Competition(
                    id=competition.id.toInt(),
                    name="competition.data",
                    imageUrl = "",
                    isFollowed=true
                )
                competitions.add(tempCompetition)
            }}
        emit(competitions)
    }.shareIn(
        scope = CoroutineScope(bgDispatcher),
        started = SharingStarted.WhileSubscribed(1000L),
        replay = 1
    )
    private val followed = competitionDao.followed().map { competitions ->
        competitions.map {
            Competition(
                id = it.id,
                imageUrl = it.imageUrl,
                name = "",
                isFollowed = true
            )
        }
    }.shareIn(
        scope = CoroutineScope(bgDispatcher),
        started = SharingStarted.WhileSubscribed(1000L),
        replay = 1
    )

    override fun competitions(): Flow<List<Competition>> = competitions

    override fun competitionDetails(competition: Competition): Flow<CompetitionDetails> {
        val teams = mutableListOf<Team>()
        val dbTeams = firestore.collection(FIRESTORE_COLLECTION_TEAMS)
            .get()
        for (team in dbTeams.result.documents) {
            val tempTeam = team.toObject(Team::class.java)
            if (tempTeam != null) {
                tempTeam.id = team.id.toInt()
            }
            if (tempTeam != null) {
                teams.add(tempTeam)
            }
        }
        return flow<CompetitionDetails> {
            CompetitionDetails(
                competition = competition,
                teams = teams.filter { it.league == competition.name })
        }.shareIn(
            scope = CoroutineScope(bgDispatcher),
            started = SharingStarted.WhileSubscribed(1000L),
            replay = 1
        )
    }

    override fun followed(): Flow<List<Competition>> = followed


    override suspend fun addCompetitionToFollowed(competitionId: Int) {
        val tempCompetition = findCompetition(competitionId)
        tempCompetition.imageUrl.let {
            competitionDao.insertCompetition(
                DbFollowedCompetition(
                    tempCompetition.id,
                    it
                )
            )
        }
    }

    override suspend fun removeCompetitionFromFollowed(competitionId: Int) {
        competitionDao.deleteCompetition(competitionId)
    }

    override suspend fun toggleFollowed(competitionId: Int) {
        runBlocking(bgDispatcher) {
            val followedCompetitions = followed.first()
            if (followedCompetitions.any { it.id == competitionId }) {
                removeCompetitionFromFollowed(competitionId)
            } else {
                addCompetitionToFollowed(competitionId)
            }
        }
    }

    private suspend fun findCompetition(competitionId: Int): Competition {
        lateinit var competiton: Competition
        val competitionList = competitions.first()
        competitionList.forEach {
            if (it.id == competitionId) {
                competiton = it
                return competiton
            }
        }
        return competiton
    }
}
