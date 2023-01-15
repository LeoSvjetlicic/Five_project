package agency.five.codebase.android.five_project.data.competitionRepository

import agency.five.codebase.android.five_project.data.database.DbFollowedCompetition
import agency.five.codebase.android.five_project.data.database.FollowedCompetitionDao
import agency.five.codebase.android.five_project.data.teamRepository.TeamRepository
import agency.five.codebase.android.five_project.model.Competition
import agency.five.codebase.android.five_project.model.CompetitionDetails
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

const val FIRESTORE_COLLECTION_COMPETITIONS = "competitions"

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class CompetitionRepositoryImpl(
    private val firestore: FirebaseFirestore,
    private val bgDispatcher: CoroutineDispatcher,
    private val competitionDao: FollowedCompetitionDao,
    private val teamRepository: TeamRepository
) : CompetitionRepository {
    private val competitions: Flow<List<Competition>> = flow {
        val competitions = mutableListOf<Competition>()
        firestore.collection(FIRESTORE_COLLECTION_COMPETITIONS)
            .get()
            .addOnSuccessListener { result ->
                for (competition in result) {
                    val tempCompetition = Competition(
                        id = competition.id.toInt(),
                        name = competition.get("name").toString(),
                        imageUrl = competition.get("imageUrl").toString(),
                        isFollowed = false
                    )
                    competitions.add(tempCompetition)
                }
            }
        Log. d("CompetitionDatabase", "succesfull ?")
        emit(competitions)
    }.flatMapLatest { competitions ->
        competitionDao.followed().map { followed ->
            competitions.forEach { competition ->
                competition.isFollowed = followed.any { it.id == competition.id }
            }
            competitions
        }
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
                name = it.name,
                isFollowed = true
            )
        }
    }.shareIn(
        scope = CoroutineScope(bgDispatcher),
        started = SharingStarted.WhileSubscribed(1000L),
        replay = 1
    )

    override fun competitions(): Flow<List<Competition>> = competitions

    override fun competitionDetails(competitionId: Int): Flow<CompetitionDetails> {
        val teams = teamRepository.getTeams()
        return flow<CompetitionDetails> {
            CompetitionDetails(
                competition = findCompetition(competitionId),
                teams = teams.first().filter { it.league == findCompetition(competitionId).name }
            )
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
                    tempCompetition.imageUrl,
                    tempCompetition.name
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
