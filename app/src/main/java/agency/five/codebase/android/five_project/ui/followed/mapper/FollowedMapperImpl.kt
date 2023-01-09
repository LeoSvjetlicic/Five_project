package agency.five.codebase.android.five_project.ui.followed.mapper

import agency.five.codebase.android.five_project.model.Competition
import agency.five.codebase.android.five_project.ui.components.CompetitionCardViewState
import agency.five.codebase.android.five_project.ui.followed.FollowedCompetitionViewState
import agency.five.codebase.android.five_project.ui.followed.FollowedViewState

class FollowedMapperImpl : FollowedMapper {
    override fun toFollowedViewState(followedCompetitions: List<Competition>) =
        FollowedViewState(
            competitionCardViewStates = followedCompetitions.map {
                FollowedCompetitionViewState(
                    id = it.id,
                    competitionViewState = CompetitionCardViewState(
                        imageUrl = it.imageUrl,
                        isFollowed = it.isFollowed,
                        name = it.name
                    )
                )
            }
        )
}
