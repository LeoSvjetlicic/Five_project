package agency.five.codebase.android.five_project.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FollowedCompetitionDao {
    @Query("SELECT * FROM followedCompetitions")
    fun followed(): Flow<List<DbFollowedCompetition>>

    @Query("SELECT * FROM followedCompetitions WHERE id=:id")
    fun getCompetitionById(id: Int): Flow<DbFollowedCompetition>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCompetition(movie: DbFollowedCompetition)

    @Query("DELETE FROM followedCompetitions WHERE id=:id")
    fun deleteCompetition(id: Int)}
