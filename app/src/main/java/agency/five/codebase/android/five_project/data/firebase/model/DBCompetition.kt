package agency.five.codebase.android.five_project.data.firebase.model

import agency.five.codebase.android.five_project.model.Competition
import agency.five.codebase.android.five_project.model.CompetitionDetails
import agency.five.codebase.android.five_project.ui.competitiondetails.CompetitionDetailsRoute
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DBCompetition (
    @SerialName("id")
    val id:Int,
    @SerialName("name")
    val name:String,
    @SerialName("imageUrl")
    val imageUrl:String,
){
    fun toCompetition(isFollowed:Boolean)=
        Competition(
            id=id,
            isFollowed = isFollowed,
            name = name,
            imageUrl = imageUrl
        )
    fun toCompetitionDetails(isFollowed:Boolean,teams:List<DBTeam>)=
        CompetitionDetails(
            competition = toCompetition(isFollowed),
            teams = teams.map{it.toTeam()}
        )
}
