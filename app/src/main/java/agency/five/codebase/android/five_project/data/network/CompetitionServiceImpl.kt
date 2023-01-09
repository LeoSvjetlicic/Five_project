package agency.five.codebase.android.five_project.data.network

import agency.five.codebase.android.five_project.data.network.model.DbCompetition
import agency.five.codebase.android.five_project.data.network.model.DbTeam
import agency.five.codebase.android.five_project.data.network.model.response.CompetitionCreditsResponse
import agency.five.codebase.android.five_project.data.network.model.response.CompetitionResponse
import agency.five.codebase.android.five_project.data.network.model.response.TeamCreditsResponse
import io.ktor.client.*

class CompetitionServiceImpl(private val client:HttpClient):CompetitionService {
    override suspend fun fetchCompetitions(): CompetitionResponse {
        TODO("Not yet implemented")
    }

    override suspend fun fetchCompetitionDetails(competitionId: Int): DbCompetition {
        TODO("Not yet implemented")
    }

    override suspend fun fetchCompetitionCredits(competitionId: Int): CompetitionCreditsResponse {
        TODO("Not yet implemented")
    }

    override suspend fun fetchTeamDetails(teamId: Int): DbTeam {
        TODO("Not yet implemented")
    }

    override suspend fun fetchTeamCredits(teamId: Int): TeamCreditsResponse {
        TODO("Not yet implemented")
    }
}
