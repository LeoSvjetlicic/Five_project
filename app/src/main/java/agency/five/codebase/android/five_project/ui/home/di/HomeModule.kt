package agency.five.codebase.android.five_project.ui.home.di

import agency.five.codebase.android.five_project.ui.home.HomeViewModel
import agency.five.codebase.android.five_project.ui.home.mapper.HomeScreenMapper
import agency.five.codebase.android.five_project.ui.home.mapper.HomeScreenMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel {
        HomeViewModel(
            competitionRepository = get(),
            homeScreenMapper = get()
        )
    }
    single<HomeScreenMapper> { HomeScreenMapperImpl() }
}
