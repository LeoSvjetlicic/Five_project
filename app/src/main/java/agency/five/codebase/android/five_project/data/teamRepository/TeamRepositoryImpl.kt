package agency.five.codebase.android.five_project.data.teamRepository

import agency.five.codebase.android.five_project.model.*
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await

const val FIRESTORE_COLLECTION_TEAMS = "teams"

class TeamRepositoryImpl(
    private val firestore: FirebaseFirestore,
    private val bgDispatcher: CoroutineDispatcher,
) : TeamRepository {
    private val teams: Flow<List<Team>> = flow {
        val tempTeams = mutableListOf<Team>()
        firestore.collection(FIRESTORE_COLLECTION_TEAMS)
            .get()
            .addOnSuccessListener { result ->
                for (team in result) {
                    val tempTeam = Team(
                        id = team.id.toInt(),
                        description = team.get("description").toString(),
                        name = team.get("name").toString(),
                        league = team.get("league").toString(),
                        imageUrl = team.get("imageUrl").toString(),
                        position = team.get("position").toString().toInt(),
                        numberOfPoints = team.get("numberOfPoints").toString().toInt()
                    )
                    tempTeams.add(tempTeam)
                }
            }
        emit(tempTeams)
    }.shareIn(
        scope = CoroutineScope(bgDispatcher),
        started = SharingStarted.Eagerly,
        replay = 1
    )

    override fun getTeams(): Flow<List<Team>> = teams

    override suspend fun getTeamDetails(teamId: Int): Flow<TeamDetails> {
        val players = mutableListOf<Member>()
        val dbMember = firestore.collection(FIRESTORE_COLLECTION_TEAMS)
            .get()
            .await()
        for (member in dbMember.documents) {
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
                members = players.filter { it.teamId == findTeam(teamId).name })
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
