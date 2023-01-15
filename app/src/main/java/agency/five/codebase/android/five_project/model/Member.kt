package agency.five.codebase.android.five_project.model

data class Member(
    var id: Int = 1,
    val name: String = "",
    val teamId: String = "",
    val number: Int = 10,
    val isRightFooted: Boolean = true,
    val imageUrl: String? = "",
)
