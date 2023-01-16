package agency.five.codebase.android.five_project.ui.teamdetails.mapper

import agency.five.codebase.android.five_project.model.TeamDetails
import agency.five.codebase.android.five_project.ui.components.MemberCardViewState
import agency.five.codebase.android.five_project.ui.components.TeamCardViewState
import agency.five.codebase.android.five_project.ui.teamdetails.TeamDetailsViewState

class TeamDetailsMapperImpl : TeamDetailsMapper {
    override fun toTeamDetailsViewState(teamDetails: TeamDetails): TeamDetailsViewState =
        TeamDetailsViewState(
            teamCardViewState = TeamCardViewState(
                id = teamDetails.team.id,
                position = teamDetails.team.position,
                imageUrl = teamDetails.team.imageUrl,
                description = teamDetails.team.description,
                name = teamDetails.team.name
            ),
            memberViewStates = teamDetails.members.map { member ->
                MemberCardViewState(
                    id = member.id,
                    name = member.name,
                    number = member.number,
                    imageUrl = member.imageUrl,
                    rightFooted = member.rightFooted
                )
            }
        )
}
