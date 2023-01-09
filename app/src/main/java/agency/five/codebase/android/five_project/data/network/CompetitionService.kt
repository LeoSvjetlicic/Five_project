package agency.five.codebase.android.five_project.data.network

import agency.five.codebase.android.five_project.data.network.model.DbCompetition
import agency.five.codebase.android.five_project.data.network.model.DbTeam
import agency.five.codebase.android.five_project.data.network.model.response.CompetitionCreditsResponse
import agency.five.codebase.android.five_project.data.network.model.response.CompetitionResponse
import agency.five.codebase.android.five_project.data.network.model.response.TeamCreditsResponse

interface CompetitionService {
    suspend fun fetchCompetitions(): CompetitionResponse
    suspend fun fetchCompetitionDetails(competitionId:Int): DbCompetition
    suspend fun fetchCompetitionCredits(competitionId:Int): CompetitionCreditsResponse
    suspend fun fetchTeamDetails(teamId:Int):DbTeam
    suspend fun fetchTeamCredits(teamId:Int): TeamCreditsResponse
}
