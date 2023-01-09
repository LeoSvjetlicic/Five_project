package agency.five.codebase.android.five_project.ui.followed

import agency.five.codebase.android.five_project.ui.components.CompetitionCardViewState

data class FollowedCompetitionViewState(
    val id: Int,
    val competitionViewState: CompetitionCardViewState
)

data class FollowedViewState(
    val competitionCardViewStates: List<FollowedCompetitionViewState> = emptyList()
)
