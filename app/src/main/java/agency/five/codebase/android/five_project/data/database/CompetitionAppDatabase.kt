package agency.five.codebase.android.five_project.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DbFollowedCompetition::class],
    version = 1,
    exportSchema = false
)
abstract class CompetitionAppDatabase : RoomDatabase() {
    abstract fun getCompetitionDao():FollowedCompetitionDao
}
