package agency.five.codebase.android.five_project.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "followedCompetitions")
data class DbFollowedCompetition(
    @PrimaryKey val id: Int,
    val imageUrl: String,
    val name: String,
)
