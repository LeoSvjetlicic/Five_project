package agency.five.codebase.android.five_project.navigation

import agency.five.codebase.android.five_project.R

const val HOME_ROUTE = "Home"
const val FOLLOWED_ROUTE = "Followed"

sealed class NavigationItem(
    override val route: String,
    val selectedIconId: Int,
    val unselectedIconId: Int,
    val labelId: Int
) : FutAppDestination(route) {
    object HomeDestination : NavigationItem(
        route = HOME_ROUTE,
        selectedIconId = R.drawable.home_full,
        unselectedIconId = R.drawable.home_empty,
        labelId = R.string.home
    )

    object FollowedDestination : NavigationItem(
        route = FOLLOWED_ROUTE,
        selectedIconId = R.drawable.follow_button_filled,
        unselectedIconId = R.drawable.follow_button_empty,
        labelId = R.string.followed
    )
}
