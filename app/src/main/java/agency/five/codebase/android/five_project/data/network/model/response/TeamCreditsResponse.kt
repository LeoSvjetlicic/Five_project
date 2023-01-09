package agency.five.codebase.android.five_project.data.network.model.response

import agency.five.codebase.android.five_project.data.network.model.DbMember
import kotlinx.serialization.SerialName

class TeamCreditsResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("teams")
    val members: List<DbMember>,
)
