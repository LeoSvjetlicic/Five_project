package agency.five.codebase.android.five_project.ui.home

import agency.five.codebase.android.five_project.data.competitionRepository.CompetitionRepository
import agency.five.codebase.android.five_project.ui.home.mapper.HomeScreenMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val competitionRepository: CompetitionRepository,
    private val homeScreenMapper: HomeScreenMapper
) : ViewModel() {
        private val _homeScreenViewState = MutableStateFlow(HomeScreenListViewState())
    val homeScreenViewState = _homeScreenViewState.asStateFlow()

    init {
        viewModelScope.launch {
            competitionRepository.competitions().collect{
                _homeScreenViewState.value=homeScreenMapper.toHomeScreenViewState(it)
            }
        }
    }

    fun toggleFollowed(competitionId: Int) {
        viewModelScope.launch {
            competitionRepository.toggleFollowed(competitionId = competitionId)
        }
    }
}
