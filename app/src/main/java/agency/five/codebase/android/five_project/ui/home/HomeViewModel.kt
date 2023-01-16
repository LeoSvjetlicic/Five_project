package agency.five.codebase.android.five_project.ui.home

import agency.five.codebase.android.five_project.data.competitionRepository.CompetitionRepository
import agency.five.codebase.android.five_project.ui.home.mapper.HomeScreenMapper
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val competitionRepository: CompetitionRepository,
    private val homeScreenMapper: HomeScreenMapper
) : ViewModel() {
    val homeScreenViewState:StateFlow<HomeScreenListViewState> =
        competitionRepository.competitions().map { list ->
            homeScreenMapper.toHomeScreenViewState(list)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1000L),
        initialValue = HomeScreenListViewState()
    )

    fun toggleFollowed(competitionId: Int) {
        viewModelScope.launch {
            competitionRepository.toggleFollowed(competitionId = competitionId)
        }
    }
}
