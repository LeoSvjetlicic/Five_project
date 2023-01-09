package agency.five.codebase.android.five_project.data.network.model

import agency.five.codebase.android.five_project.model.Member
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class DbMember(
    @SerialName("id")
    val id: Int,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("name")
    val name: String,
    @SerialName("number")
    val number: Int,
    @SerialName("isRightFooted")
    val isRightFooted: Boolean,
) {
    fun toMember() = Member(
        id = id,
        name = name,
        number = number,
        isRightFooted = isRightFooted,
        imageUrl = if (imageUrl != null) {
            imageUrl
        } else {
            "https://upload.wikimedia.org/wikipedia/commons/b/bc/Unknown_person.jpg"
        },
    )
}
