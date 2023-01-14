package agency.five.codebase.android.five_project.ui.competitiondetails.di

import agency.five.codebase.android.five_project.ui.competitiondetails.CompetitionDetailsViewModel
import agency.five.codebase.android.five_project.ui.competitiondetails.mapper.CompetitionDetailsMapper
import agency.five.codebase.android.five_project.ui.competitiondetails.mapper.CompetitionDetailsMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val competitionDetailsModule = module {
    viewModel {
        CompetitionDetailsViewModel(
            competitionRepository = get(),
            competitionDetailsMapper = get(),
            competitionId = it.get()
        )
    }
    single<CompetitionDetailsMapper> { CompetitionDetailsMapperImpl() }
}
