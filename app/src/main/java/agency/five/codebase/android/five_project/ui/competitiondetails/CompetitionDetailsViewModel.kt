package agency.five.codebase.android.five_project.ui.competitiondetails

import agency.five.codebase.android.five_project.data.repository.CompetitionRepository
import agency.five.codebase.android.five_project.mock.Mock
import agency.five.codebase.android.five_project.ui.competitiondetails.mapper.CompetitionDetailsMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CompetitionDetailsViewModel(
    private val competitionRepository: CompetitionRepository,
    private val competitionDetailsMapper: CompetitionDetailsMapper,
    competitionId: Int
) : ViewModel() {
    val competitionDetailsViewState: StateFlow<CompetitionDetailsViewState> =
        competitionRepository.competitionDetails(competitionId)
            .map { competitions ->
                competitionDetailsMapper.toCompetitionDetailsViewState(competitions)
            }
            .stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                competitionDetailsMapper.toCompetitionDetailsViewState(
                    Mock.getCompetitionDetails(competitionId)
                )
            )

    fun toggleFollowed(competitionId: Int) {
        viewModelScope.launch {
            competitionRepository.toggleFollowed(competitionId)
        }
    }
}
