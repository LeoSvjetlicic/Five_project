package agency.five.codebase.android.five_project.data.competitionRepository.di

import agency.five.codebase.android.five_project.data.competitionRepository.CompetitionRepository
import agency.five.codebase.android.five_project.data.competitionRepository.CompetitionRepositoryImpl
import agency.five.codebase.android.five_project.data.database.CompetitionAppDatabase
import agency.five.codebase.android.five_project.data.database.FollowedCompetitionDao
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module


val competitionModule = module {
    single<CompetitionRepository> { CompetitionRepositoryImpl(get(), Dispatchers.IO, get()) }
    fun provideFollowedMovieDao(db: CompetitionAppDatabase): FollowedCompetitionDao = db.getCompetitionDao()
    single {
        provideFollowedMovieDao(get())
    }
}
