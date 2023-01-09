package agency.five.codebase.android.five_project.ui.followed

import agency.five.codebase.android.five_project.data.repository.CompetitionRepository
import agency.five.codebase.android.five_project.ui.followed.mapper.FollowedMapper
import agency.five.codebase.android.five_project.ui.home.HomeScreenListViewState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FollowedScreenViewModel(
    private val competitionRepository: CompetitionRepository,
    private val followedMapper: FollowedMapper
) : ViewModel() {
    private val _favoriteScreenViewState = MutableStateFlow(FollowedViewState())
    val favoriteScreenViewState = _favoriteScreenViewState.asStateFlow()

    init {
        viewModelScope.launch {
            competitionRepository.followed().collect {
                _favoriteScreenViewState.value = followedMapper.toFollowedViewState(it)
            }
        }
    }

    fun toggleFollowed(competitionId: Int) {
        viewModelScope.launch {
            competitionRepository.removeCompetitionFromFollowed(competitionId = competitionId)
        }
    }
}
