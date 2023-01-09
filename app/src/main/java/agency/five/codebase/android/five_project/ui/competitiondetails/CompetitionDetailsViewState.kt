package agency.five.codebase.android.five_project.ui.competitiondetails

import agency.five.codebase.android.five_project.ui.components.CompetitionCardViewState
import agency.five.codebase.android.five_project.ui.components.TeamCardViewState

data class CompetitionDetailsViewState(
    val id: Int,
    val competitionCardViewState: CompetitionCardViewState,
    val teamViewStates: List<TeamCardViewState> = emptyList(),
) {
    companion object {
        val EMPTY: CompetitionCardViewState = CompetitionCardViewState(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/6/6c/Luka_Doncic_2021_%28cropped%29.jpg",
            isFollowed = false,
            name = "Champions League"
        )
    }
}
