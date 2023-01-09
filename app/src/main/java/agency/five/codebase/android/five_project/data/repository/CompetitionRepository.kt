package agency.five.codebase.android.five_project.data.repository

import agency.five.codebase.android.five_project.model.Competition
import agency.five.codebase.android.five_project.model.CompetitionDetails
import agency.five.codebase.android.five_project.model.TeamDetails
import kotlinx.coroutines.flow.Flow

interface CompetitionRepository {
    fun competitions():Flow<List<Competition>>
    fun competitionDetails(competitionId:Int):Flow<CompetitionDetails>
    fun followed():Flow<List<Competition>>
    fun teamDetails(teamId:Int):Flow<TeamDetails>
    suspend fun addCompetitionToFollowed(competitionId:Int)
    suspend fun removeCompetitionFromFollowed(competitionId:Int)
    suspend fun toggleFollowed(competitionId:Int)
}
