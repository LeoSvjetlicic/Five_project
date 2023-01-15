package agency.five.codebase.android.five_project.model

data class Team(
    var id: Int=1,
    val imageUrl: String?="",
    val name: String="",
    val league: String="",
    val description: String = "",
    val position: Int=1,
    val numberOfPoints: Int=10
)
