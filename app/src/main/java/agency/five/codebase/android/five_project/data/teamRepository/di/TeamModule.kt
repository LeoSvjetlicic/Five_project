package agency.five.codebase.android.five_project.data.teamRepository.di

import agency.five.codebase.android.five_project.data.teamRepository.TeamRepository
import agency.five.codebase.android.five_project.data.teamRepository.TeamRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val teamModule = module {
    single<TeamRepository> { TeamRepositoryImpl(get(),Dispatchers.IO) }
}
