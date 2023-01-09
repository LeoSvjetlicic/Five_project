package agency.five.codebase.android.five_project.ui.teamdetails.di

import agency.five.codebase.android.five_project.ui.followed.mapper.FollowedMapper
import agency.five.codebase.android.five_project.ui.followed.mapper.FollowedMapperImpl
import agency.five.codebase.android.five_project.ui.teamdetails.TeamDetailsViewModel
import agency.five.codebase.android.five_project.ui.teamdetails.mapper.TeamDetailsMapper
import agency.five.codebase.android.five_project.ui.teamdetails.mapper.TeamDetailsMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val teamDetailsModule = module {
    viewModel {
        TeamDetailsViewModel(
            competitionRepository = get(),
            mapper = get(),
            teamId = it.get()
        )
    }
    single<TeamDetailsMapper> { TeamDetailsMapperImpl() }
}
