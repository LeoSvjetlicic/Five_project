package agency.five.codebase.android.five_project.data.firebase.model

import agency.five.codebase.android.five_project.model.Team
import agency.five.codebase.android.five_project.model.TeamDetails
import kotlinx.serialization.SerialName

data class DBTeam(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("description")
    val description: String,
    @SerialName("numberOfPoints")
    val points: String,
    @SerialName("position")
    val position: String,
    @SerialName("league")
    val league: String
) {
    fun toTeam() =
        Team(
            id = id,
            name = name,
            imageUrl = imageUrl,
            position = position.toInt(),
            numberOfPoints = points.toInt(),
            league = league,
            description = description
        )

    fun toTeamDetails(members: List<DBMember>) =
        TeamDetails(
            team = toTeam(),
            members = members.map { it.toMember() }
        )
}
