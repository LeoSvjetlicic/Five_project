package agency.five.codebase.android.five_project.navigation

const val COMPETITION_DETAILS_ROUTE = "CompetitionDetails"
const val COMPETITION_ID = "competitionId"
const val COMPETITION_DETAILS_ROUTE_WITH_PARAMS = "$COMPETITION_DETAILS_ROUTE/{$COMPETITION_ID}"

object CompetitionDetailsDestination : FutAppDestination(COMPETITION_DETAILS_ROUTE_WITH_PARAMS) {
    fun createNavigationRoute(competitionId: Int): String =
        "$COMPETITION_DETAILS_ROUTE/$competitionId"
}
