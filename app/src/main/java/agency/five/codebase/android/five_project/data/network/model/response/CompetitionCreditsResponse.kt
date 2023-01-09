package agency.five.codebase.android.five_project.data.network.model.response

import agency.five.codebase.android.five_project.data.network.model.DbTeam
import kotlinx.serialization.SerialName

class CompetitionCreditsResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("teams")
    val teams: List<DbTeam>,
)

