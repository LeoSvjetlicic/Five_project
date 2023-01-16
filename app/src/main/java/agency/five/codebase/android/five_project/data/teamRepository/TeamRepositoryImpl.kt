package agency.five.codebase.android.five_project.data.teamRepository

import agency.five.codebase.android.five_project.data.firebase.CompetitionService
import agency.five.codebase.android.five_project.model.*
import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

class TeamRepositoryImpl(
    private val competitionService: CompetitionService,
    private val bgDispatcher: CoroutineDispatcher,
) : TeamRepository {
    private val teams: Flow<List<Team>> = flow {
        emit(competitionService.fetchTeams().map { it.toTeam() })
    }.shareIn(
        scope = CoroutineScope(bgDispatcher),
        started = SharingStarted.Eagerly,
        replay = 1
    )

    override fun getTeams(): Flow<List<Team>> = teams

    override suspend fun getTeamDetails(teamId: Int): Flow<TeamDetails> = flow {
        val members = competitionService.fetchMembers()
        val team = findTeam(teamId)
        val teamDetails = TeamDetails(
            team = team,
            members = members.map { it.toMember() }.filter { it.teamId == team.name })
        emit(teamDetails)
    }.shareIn(
        scope = CoroutineScope(bgDispatcher),
        started = SharingStarted.WhileSubscribed(1000L),
        replay = 1
    )

    override suspend fun findTeam(teamId: Int): Team {
        lateinit var team: Team
        val teamList = teams.first()
        teamList.forEach {
            if (it.id == teamId) {
                team = it
                return team
            }
        }
        return team
    }
}
