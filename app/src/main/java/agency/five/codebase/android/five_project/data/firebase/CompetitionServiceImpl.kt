package agency.five.codebase.android.five_project.data.firebase

import agency.five.codebase.android.five_project.data.firebase.model.DBCompetition
import agency.five.codebase.android.five_project.data.firebase.model.DBMember
import agency.five.codebase.android.five_project.data.firebase.model.DBTeam
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

const val FIRESTORE_COLLECTION_COMPETITIONS = "competitions"
const val FIRESTORE_COLLECTION_TEAMS = "teams"
const val FIRESTORE_COLLECTION_PLAYERS = "players"

class CompetitionServiceImpl(private val firestore: FirebaseFirestore) : CompetitionService {
    override suspend fun fetchCompetitions(): List<DBCompetition> =
        firestore.collection(FIRESTORE_COLLECTION_COMPETITIONS)
            .get()
            .await()
            .map { competition ->
                DBCompetition(
                    id = competition.id.toInt(),
                    name = competition.get("name").toString(),
                    imageUrl = competition.get("imageUrl").toString(),
                )
            }


    override suspend fun fetchTeams(): List<DBTeam> =
        firestore.collection(FIRESTORE_COLLECTION_TEAMS)
            .get()
            .await().map { team ->
                DBTeam(
                    id = team.id.toInt(),
                    description = team.get("description").toString(),
                    name = team.get("name").toString(),
                    league = team.get("league").toString(),
                    imageUrl = team.get("imageUrl").toString(),
                    position = team.get("position").toString(),
                    points = team.get("numberOfPoints").toString()
                )
            }


    override suspend fun fetchMembers(): List<DBMember> =
        firestore.collection(FIRESTORE_COLLECTION_PLAYERS)
            .get()
            .await()
            .map { member ->
                DBMember(
                    id = member.id.toInt(),
                    name = member.get("name").toString(),
                    imageUrl = member.get("imageUrl").toString(),
                    rightFooted = member.get("rightFooted").toString().toBooleanStrict(),
                    number = member.get("number").toString().toInt(),
                    teamId = member.get("teamId").toString(),
                )
            }
}
