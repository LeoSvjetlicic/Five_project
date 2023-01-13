package agency.five.codebase.android.five_project.ui.teamdetails

import agency.five.codebase.android.five_project.data.competitionRepository.CompetitionRepository
import agency.five.codebase.android.five_project.data.teamRepository.TeamRepository
import agency.five.codebase.android.five_project.data.teamRepository.TeamRepositoryImpl
import agency.five.codebase.android.five_project.mock.Mock
import agency.five.codebase.android.five_project.model.Team
import agency.five.codebase.android.five_project.ui.teamdetails.mapper.TeamDetailsMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TeamDetailsViewModel(
    private val teamRepository: TeamRepository,
    private val mapper: TeamDetailsMapper,
    team: Team
) : ViewModel() {
    val teamDetailsViewState: StateFlow<TeamDetailsViewState> =
        teamRepository.getTeamDetails(team)
            .map { team ->
                mapper.toTeamDetailsViewState(team)
            }
            .stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                mapper.toTeamDetailsViewState(
                    Mock.getTeamDetails(team)
                )
            )
}
