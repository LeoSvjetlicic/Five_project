package agency.five.codebase.android.five_project.data.teamRepository

import agency.five.codebase.android.five_project.model.Team
import agency.five.codebase.android.five_project.model.TeamDetails
import kotlinx.coroutines.flow.Flow

interface TeamRepository {
    fun getTeams(): Flow<List<Team>>
    suspend fun getTeamDetails(teamId: Int): Flow<TeamDetails>
    suspend fun findTeam(teamId: Int): Team
}
