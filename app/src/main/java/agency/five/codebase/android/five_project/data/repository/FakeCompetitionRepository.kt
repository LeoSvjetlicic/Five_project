package agency.five.codebase.android.five_project.data.repository

import agency.five.codebase.android.five_project.mock.FollowedDBMock
import agency.five.codebase.android.five_project.mock.Mock
import agency.five.codebase.android.five_project.model.Competition
import agency.five.codebase.android.five_project.model.CompetitionDetails
import agency.five.codebase.android.five_project.model.TeamDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*

class FakeCompetitionRepository(
    private val ioDispatcher: CoroutineDispatcher
) : CompetitionRepository {
    private val fakeCompetitions = Mock.getCompetitionsList().toMutableList()
    private val competitions: Flow<List<Competition>> = FollowedDBMock.followedIds
        .mapLatest { followedIds ->
            fakeCompetitions.map { competition ->
                competition.copy(isFollowed = followedIds.contains(competition.id))
            }
        }
        .flowOn(ioDispatcher)

    override fun competitions(): Flow<List<Competition>> = competitions

    override fun competitionDetails(competitionId: Int): Flow<CompetitionDetails> =
        FollowedDBMock.followedIds
            .mapLatest { followedIds ->
                val competitionDetails = Mock.getCompetitionDetails(competitionId)
                val tempCompetition = competitionDetails.competition.copy(
                    isFollowed = followedIds.contains(competitionDetails.competition.id)
                )
                tempCompetition.copy(isFollowed = followedIds.contains(tempCompetition.id))
                competitionDetails.copy(competition = tempCompetition)
            }.flowOn(ioDispatcher)

    override fun followed(): Flow<List<Competition>> =
        competitions.map { it.filter { fakeCompetitions -> fakeCompetitions.isFollowed } }

    override fun teamDetails(teamId: Int): Flow<TeamDetails> = flow {
        Mock.getTeamDetails(teamId)
    }
    override suspend fun addCompetitionToFollowed(competitionId: Int) {
        FollowedDBMock.insert(competitionId)
    }

    override suspend fun removeCompetitionFromFollowed(competitionId: Int) {
        FollowedDBMock.delete(competitionId)
    }

    override suspend fun toggleFollowed(competitionId: Int) {
        if (FollowedDBMock.followedIds.value.contains(competitionId)) {
            removeCompetitionFromFollowed(competitionId)
        } else {
            addCompetitionToFollowed(competitionId)
        }
    }
}
