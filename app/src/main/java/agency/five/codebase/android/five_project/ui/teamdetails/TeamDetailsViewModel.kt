package agency.five.codebase.android.five_project.ui.teamdetails

import agency.five.codebase.android.five_project.data.teamRepository.TeamRepository
import agency.five.codebase.android.five_project.ui.teamdetails.mapper.TeamDetailsMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*

class TeamDetailsViewModel(
    private val teamRepository: TeamRepository,
    private val mapper: TeamDetailsMapper,
    private val teamId: Int
) : ViewModel() {
    private val initialViewState = TeamDetailsViewState.EMPTY
    private val data=flow{
        emit(teamRepository.getTeamDetails(teamId))
    }
    val teamDetailsViewState: StateFlow<TeamDetailsViewState> =
        data.map { team ->
                mapper.toTeamDetailsViewState(team.first())
            }
    .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            initialViewState
        )
}
