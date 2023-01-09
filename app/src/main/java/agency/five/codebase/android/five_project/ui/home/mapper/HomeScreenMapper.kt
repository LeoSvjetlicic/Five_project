package agency.five.codebase.android.five_project.ui.home.mapper

import agency.five.codebase.android.five_project.model.Competition
import agency.five.codebase.android.five_project.ui.home.HomeScreenListViewState

interface HomeScreenMapper {
    fun toHomeScreenViewState(competitions:List<Competition>): HomeScreenListViewState
}
