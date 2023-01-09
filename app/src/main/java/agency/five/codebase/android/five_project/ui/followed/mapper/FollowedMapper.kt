package agency.five.codebase.android.five_project.ui.followed.mapper

import agency.five.codebase.android.five_project.model.Competition
import agency.five.codebase.android.five_project.ui.followed.FollowedViewState

interface FollowedMapper{
    fun toFollowedViewState(followedCompetitions:List<Competition>):FollowedViewState
}
