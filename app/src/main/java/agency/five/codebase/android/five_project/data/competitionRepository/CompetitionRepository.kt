package agency.five.codebase.android.five_project.data.competitionRepository

import agency.five.codebase.android.five_project.model.Competition
import agency.five.codebase.android.five_project.model.CompetitionDetails
import kotlinx.coroutines.flow.Flow

interface CompetitionRepository {
    fun competitions(): Flow<List<Competition>>
    fun competitionDetails(competition: Competition): Flow<CompetitionDetails>
    fun followed(): Flow<List<Competition>>
    suspend fun addCompetitionToFollowed(competitionId: Int)
    suspend fun removeCompetitionFromFollowed(competitionId: Int)
    suspend fun toggleFollowed(competitionId: Int)
}
