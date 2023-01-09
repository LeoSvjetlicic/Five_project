package agency.five.codebase.android.five_project.data.network.model

import agency.five.codebase.android.five_project.model.Competition
import agency.five.codebase.android.five_project.model.Member
import agency.five.codebase.android.five_project.model.Team
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DbCompetition (
    @SerialName("id")
    val id:Int,
    @SerialName("name")
    val name:String,
    @SerialName("id")
    val imageUrl:String,
){
    fun toCompetition(isFollowed:Boolean)=Competition(
        id=id,
        name=name,
        imageUrl=imageUrl,
        isFollowed=isFollowed,
    )
}
