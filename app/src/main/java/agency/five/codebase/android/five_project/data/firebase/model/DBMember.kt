package agency.five.codebase.android.five_project.data.firebase.model

import agency.five.codebase.android.five_project.model.Member
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DBMember(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("rightFooted")
    val rightFooted: Boolean,
    @SerialName("number")
    val number: Int,
    @SerialName("teamId")
    val teamId: String,
) {
    fun toMember() =
        Member(
            id = id,
            name = name,
            imageUrl = imageUrl,
            rightFooted = rightFooted,
            number = number,
            teamId = teamId
        )
}
