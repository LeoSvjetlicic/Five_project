package agency.five.codebase.android.five_project.ui.followed.di

import agency.five.codebase.android.five_project.ui.followed.FollowedScreenViewModel
import agency.five.codebase.android.five_project.ui.followed.mapper.FollowedMapper
import agency.five.codebase.android.five_project.ui.followed.mapper.FollowedMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val followedModule = module {
    viewModel {
        FollowedScreenViewModel(
            competitionRepository = get(),
            followedMapper = get()
        )
    }
    single<FollowedMapper> { FollowedMapperImpl() }
}
