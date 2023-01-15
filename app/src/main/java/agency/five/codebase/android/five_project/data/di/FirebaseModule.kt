package agency.five.codebase.android.five_project.data.di

import agency.five.codebase.android.five_project.data.firebase.CompetitionService
import agency.five.codebase.android.five_project.data.firebase.CompetitionServiceImpl
import org.koin.dsl.module

val firebaseModule=module{
    single <CompetitionService>{ CompetitionServiceImpl(get()) }
}
