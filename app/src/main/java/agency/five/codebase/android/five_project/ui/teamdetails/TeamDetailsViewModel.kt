package agency.five.codebase.android.five_project.ui.teamdetails

import agency.five.codebase.android.five_project.data.repository.CompetitionRepository
import agency.five.codebase.android.five_project.mock.Mock
import agency.five.codebase.android.five_project.ui.teamdetails.mapper.TeamDetailsMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TeamDetailsViewModel(
    private val competitionRepository: CompetitionRepository,
    private val mapper: TeamDetailsMapper,
    teamId: Int
) : ViewModel() {
    val teamDetailsViewState: StateFlow<TeamDetailsViewState> =
        competitionRepository.teamDetails(teamId)
            .map { team ->
                mapper.toTeamDetailsViewState(team)
            }
            .stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                mapper.toTeamDetailsViewState(
                    Mock.getTeamDetails(teamId)
                )
            )
}
