package agency.five.codebase.android.five_project.ui.teamdetails.mapper

import agency.five.codebase.android.five_project.model.TeamDetails
import agency.five.codebase.android.five_project.ui.teamdetails.TeamDetailsViewState

interface TeamDetailsMapper {
    fun toTeamDetailsViewState(team: TeamDetails):TeamDetailsViewState
}
