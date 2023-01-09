package agency.five.codebase.android.five_project.data.di

import agency.five.codebase.android.five_project.data.repository.CompetitionRepository
import agency.five.codebase.android.five_project.data.repository.FakeCompetitionRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dataModule = module {
    single<CompetitionRepository> {
        FakeCompetitionRepository(ioDispatcher = Dispatchers.IO)
    }
}
