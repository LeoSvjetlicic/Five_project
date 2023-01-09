package agency.five.codebase.android.five_project.mock

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object FollowedDBMock {

    private val _followedIds = MutableStateFlow(setOf<Int>())
    val followedIds: StateFlow<Set<Int>> = _followedIds.asStateFlow()

    fun insert(competitionId: Int) {
        _followedIds.update { it + competitionId }
    }

    fun delete(competitionId: Int) {
        _followedIds.update { it - competitionId }
    }
}
