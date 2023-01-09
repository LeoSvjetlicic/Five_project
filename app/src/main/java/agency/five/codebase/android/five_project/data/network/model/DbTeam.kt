package agency.five.codebase.android.five_project.data.network.model

import agency.five.codebase.android.five_project.model.Team
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DbTeam(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("description")
    val description: String,
    @SerialName("position")
    val position: Int,
    @SerialName("numberOfPoints")
    val numberOfPoints: Int,
) {
    fun toTeam(members: List<DbMember>) = Team(
        id = id,
        name = name,
        imageUrl = imageUrl,
        description = description,
        position = position,
        numberOfPoints = numberOfPoints,
    )
}
