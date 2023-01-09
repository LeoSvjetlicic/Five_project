package agency.five.codebase.android.five_project.data.network.model.response

import agency.five.codebase.android.five_project.data.network.model.DbCompetition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompetitionResponse(
    @SerialName("results")
    val competitions: List<DbCompetition>,
)
