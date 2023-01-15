package agency.five.codebase.android.five_project.data.competitionRepository

import agency.five.codebase.android.five_project.data.database.DbFollowedCompetition
import agency.five.codebase.android.five_project.data.database.FollowedCompetitionDao
import agency.five.codebase.android.five_project.data.firebase.CompetitionService
import agency.five.codebase.android.five_project.data.teamRepository.TeamRepository
import agency.five.codebase.android.five_project.model.Competition
import agency.five.codebase.android.five_project.model.CompetitionDetails
import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking


@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class CompetitionRepositoryImpl(
    private val competitionService: CompetitionService,
    private val bgDispatcher: CoroutineDispatcher,
    private val competitionDao: FollowedCompetitionDao,
    private val teamRepository: TeamRepository
) : CompetitionRepository {
    private val competitions: Flow<List<Competition>> = flow {
        emit(competitionService.fetchCompetitions())
    }.flatMapLatest { competitions ->
        competitionDao.followed().map { followed ->
            competitions.map { competition ->
                competition.toCompetition(isFollowed = followed.any { it.id == competition.id })
            }
        }
    }.shareIn(
        scope = CoroutineScope(bgDispatcher),
        started = SharingStarted.WhileSubscribed(1000L),
        replay = 1
    )
    private val followed = competitionDao.followed().map { competitions ->
        competitions.map {
            Competition(
                id = it.id,
                imageUrl = it.imageUrl,
                name = it.name,
                isFollowed = true
            )
        }
    }.shareIn(
        scope = CoroutineScope(bgDispatcher),
        started = SharingStarted.WhileSubscribed(1000L),
        replay = 1
    )

    override fun competitions(): Flow<List<Competition>> {
        return competitions
    }

    override fun competitionDetails(competitionId: Int): Flow<CompetitionDetails> = flow {
        val teams = teamRepository.getTeams()
        val details = CompetitionDetails(
            competition = findCompetition(competitionId),
            teams = teams.first().filter { it.league == findCompetition(competitionId).name }
                .sortedBy { it.position }
        )
        emit(details)
    }.shareIn(
        scope = CoroutineScope(bgDispatcher),
        started = SharingStarted.WhileSubscribed(1000L),
        replay = 1
    )


    override fun followed(): Flow<List<Competition>> = followed

    override suspend fun addCompetitionToFollowed(competitionId: Int) {
        val tempCompetition = findCompetition(competitionId)
        tempCompetition.imageUrl.let {
            competitionDao.insertCompetition(
                DbFollowedCompetition(
                    tempCompetition.id,
                    tempCompetition.imageUrl,
                    tempCompetition.name
                )
            )
        }
    }

    override suspend fun removeCompetitionFromFollowed(competitionId: Int) {
        competitionDao.deleteCompetition(competitionId)
    }

    override suspend fun toggleFollowed(competitionId: Int) {
        runBlocking(bgDispatcher) {
            val followedCompetitions = followed.first()
            if (followedCompetitions.any { it.id == competitionId }) {
                removeCompetitionFromFollowed(competitionId)
            } else {
                addCompetitionToFollowed(competitionId)
            }
        }
    }

    private suspend fun findCompetition(competitionId: Int): Competition {
        lateinit var competiton: Competition
        val competitionList = competitions.first()
        competitionList.forEach {
            if (it.id == competitionId) {
                competiton = it
                return competiton
            }
        }
        return competiton
    }
}
