package agency.five.codebase.android.five_project.ui.home.mapper

import agency.five.codebase.android.five_project.model.Competition
import agency.five.codebase.android.five_project.ui.components.CompetitionCardViewState
import agency.five.codebase.android.five_project.ui.home.HomeScreenListViewState
import agency.five.codebase.android.five_project.ui.home.HomeScreenViewState

class HomeScreenMapperImpl : HomeScreenMapper {
    override fun toHomeScreenViewState(competitions: List<Competition>) =
        HomeScreenListViewState(
            competitionViewStates = competitions.map {
                HomeScreenViewState(
                    id = it.id,
                    competitionCardViewState = CompetitionCardViewState(
                        imageUrl = it.imageUrl,
                        isFollowed = it.isFollowed,
                        name = it.name
                    )
                )
            }
        )
}
