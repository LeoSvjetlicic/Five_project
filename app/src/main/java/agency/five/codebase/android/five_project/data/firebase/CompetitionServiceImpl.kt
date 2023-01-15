package agency.five.codebase.android.five_project.data.firebase

import agency.five.codebase.android.five_project.data.firebase.model.DBCompetition
import agency.five.codebase.android.five_project.data.firebase.model.DBMember
import agency.five.codebase.android.five_project.data.firebase.model.DBTeam
import agency.five.codebase.android.five_project.model.Team
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

const val FIRESTORE_COLLECTION_COMPETITIONS = "competitions"
const val FIRESTORE_COLLECTION_TEAMS = "teams"
const val FIRESTORE_COLLECTION_PLAYERS = "players"

class CompetitionServiceImpl(private val firestore: FirebaseFirestore) : CompetitionService {
    override suspend fun fetchCompetitions(): List<DBCompetition> {
        val competitions = mutableListOf<DBCompetition>()
        val result = firestore.collection(FIRESTORE_COLLECTION_COMPETITIONS)
            .get()
            .await()
        for (competition in result) {
            val tempCompetition = DBCompetition(
                id = competition.id.toInt(),
                name = competition.get("name").toString(),
                imageUrl = competition.get("imageUrl").toString(),
            )
            competitions.add(tempCompetition)
        }
        return competitions
    }

    override suspend fun fetchTeams(): List<DBTeam> {
        val tempTeams = mutableListOf<DBTeam>()
        val result = firestore.collection(FIRESTORE_COLLECTION_TEAMS)
            .get()
            .await()
        for (team in result) {
            Log.d("osidjgisufnvsdklfbeiurvdnylkmnjfsvo","${team.get("description")}")
            val tempTeam = DBTeam(
                id = team.id.toInt(),
                description = team.get("description").toString(),
                name = team.get("name").toString(),
                league = team.get("league").toString(),
                imageUrl = team.get("imageUrl").toString(),
                position = team.get("position").toString(),
                points = team.get("numberOfPoints").toString()
            )
            tempTeams.add(tempTeam)
        }
        return tempTeams
    }

    override suspend fun fetchMembers(): List<DBMember> {
        val tempMembers = mutableListOf<DBMember>()
        val result = firestore.collection(FIRESTORE_COLLECTION_PLAYERS)
            .get()
            .await()
        for (team in result) {
            val tempMember = DBMember(
                id = team.id.toInt(),
                name = team.get("name").toString(),
                imageUrl = team.get("imageUrl").toString(),
                isRightFooted = true,
                number = team.get("number").toString().toInt(),
                teamId = team.get("teamId").toString(),
            )
            tempMembers.add(tempMember)
        }
        return tempMembers
    }
}
