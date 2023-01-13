package agency.five.codebase.android.five_project.model

data class Member(
    var id: Int,
    val name: String,
    val teamId: String,
    val number: Int,
    val isRightFooted: Boolean,
    val imageUrl: String?,
)
