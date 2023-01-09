package agency.five.codebase.android.five_project.ui.competitiondetails.mapper

import agency.five.codebase.android.five_project.model.Competition
import agency.five.codebase.android.five_project.model.CompetitionDetails
import agency.five.codebase.android.five_project.ui.competitiondetails.CompetitionDetailsViewState
import agency.five.codebase.android.five_project.ui.components.CompetitionCardViewState
import agency.five.codebase.android.five_project.ui.components.TeamCardViewState

class CompetitionDetailsMapperImpl : CompetitionDetailsMapper {
    override fun toCompetitionDetailsViewState(competitionDetails: CompetitionDetails) =
        CompetitionDetailsViewState(
            id=competitionDetails.competition.id,
            competitionCardViewState = CompetitionCardViewState(
                imageUrl = competitionDetails.competition.imageUrl,
                isFollowed = competitionDetails.competition.isFollowed,
                name = competitionDetails.competition.name
            ),
            teamViewStates = competitionDetails.teams.map
            { team ->
                TeamCardViewState(
                    id = team.id,
                    imageUrl = team.imageUrl,
                    position = team.position,
                    name = team.name,
                    points = team.numberOfPoints
                )
            },
        )
}
