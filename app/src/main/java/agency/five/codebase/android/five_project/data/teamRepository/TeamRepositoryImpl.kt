package agency.five.codebase.android.five_project.data.teamRepository

import agency.five.codebase.android.five_project.data.competitionRepository.FIRESTORE_COLLECTION_TEAMS
import agency.five.codebase.android.five_project.model.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

class TeamRepositoryImpl(
    private val firestore: FirebaseFirestore,
    private val bgDispatcher: CoroutineDispatcher,
) : TeamRepository {
    private val teams: Flow<List<Team>> = flow {
        val tempTeams = mutableListOf<Team>()
        val dbTeams = firestore.collection(FIRESTORE_COLLECTION_TEAMS)
            .get()
        for (t in dbTeams.result.documents) {
            val tempCompetition = t.toObject(Team::class.java)
            if (tempCompetition != null) {
                tempCompetition.id = t.id.toInt()
                tempTeams.add(tempCompetition)
            }
        }
        emit(tempTeams)
    }.shareIn(
        scope = CoroutineScope(bgDispatcher),
        started = SharingStarted.WhileSubscribed(1000L),
        replay = 1
    )

    override fun getTeams(leagueId: String): Flow<List<Team>> = teams

    override fun getTeamDetails(teamId: Int): Flow<TeamDetails> {
        val players = mutableListOf<Member>()
        val dbMember = firestore.collection(FIRESTORE_COLLECTION_TEAMS)
            .get()
        for (member in dbMember.result.documents) {
            val tempMember = member.toObject(Member::class.java)
            if (tempMember != null) {
                tempMember.id = member.id.toInt()
            }
            if (tempMember != null) {
                players.add(tempMember)
            }

        }
        return flow<TeamDetails> {
            TeamDetails(
                team = findTeam(teamId),
                members = players.filter { it.teamId.toInt() == findTeam(teamId).id })
        }.flowOn(bgDispatcher)
    }

    override suspend fun findTeam(teamId: Int): Team {
        lateinit var team: Team
        val competitionList = teams.first()
        competitionList.forEach {
            if (it.id == teamId) {
                team = it
                return team
            }
        }
        return team
    }
}
