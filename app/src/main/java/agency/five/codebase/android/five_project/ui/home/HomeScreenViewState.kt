package agency.five.codebase.android.five_project.ui.home

import agency.five.codebase.android.five_project.ui.components.CompetitionCardViewState

data class HomeScreenViewState(
    val id: Int,
    val competitionCardViewState: CompetitionCardViewState
)

data class HomeScreenListViewState(
    val competitionViewStates: List<HomeScreenViewState> = emptyList()
)
