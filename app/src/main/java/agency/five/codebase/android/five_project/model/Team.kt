package agency.five.codebase.android.five_project.model

data class Team(
    val id: Int,
    val imageUrl: String?,
    val name: String,
    val description: String = "",
    val position: Int,
    val numberOfPoints: Int
)
