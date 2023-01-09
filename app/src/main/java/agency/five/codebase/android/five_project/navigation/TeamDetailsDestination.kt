package agency.five.codebase.android.five_project.navigation

const val TEAM_DETAILS_ROUTE = "TeamDetails"
const val TEAM_ID = "teamId"
const val TEAM_DETAILS_ROUTE_WITH_PARAMS = "$TEAM_DETAILS_ROUTE/{$TEAM_ID}"

object TeamDetailsDestination : FutAppDestination(TEAM_DETAILS_ROUTE_WITH_PARAMS) {
    fun createNavigationRoute(teamId: Int): String = "$TEAM_DETAILS_ROUTE/$teamId"
}
