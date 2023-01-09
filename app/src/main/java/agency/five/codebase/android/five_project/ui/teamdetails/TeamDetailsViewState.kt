package agency.five.codebase.android.five_project.ui.teamdetails

import agency.five.codebase.android.five_project.ui.components.MemberCardViewState
import agency.five.codebase.android.five_project.ui.components.TeamCardViewState

data class TeamDetailsViewState(
    val teamCardViewState: TeamCardViewState,
    val memberViewStates: List<MemberCardViewState> = emptyList()
) {
    companion object {
        val EMPTY = TeamDetailsViewState(
            TeamCardViewState(
                id = 0,
                position = 1,
                imageUrl = "",
                description = "",
                name = "",
                points = 0
            )
        )
    }
}
