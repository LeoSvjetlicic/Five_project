package agency.five.codebase.android.five_project.ui.competitiondetails

import agency.five.codebase.android.five_project.ui.components.CompetitionCardViewState
import agency.five.codebase.android.five_project.ui.components.TeamCardViewState

data class CompetitionDetailsViewState(
    val id: Int,
    val competitionCardViewState: CompetitionCardViewState,
    val teamViewStates: List<TeamCardViewState> = emptyList(),
) {
    companion object {
        val EMPTY: CompetitionDetailsViewState = CompetitionDetailsViewState(
            id = 1,
            competitionCardViewState = CompetitionCardViewState("", true, ""),
        )
    }
}
