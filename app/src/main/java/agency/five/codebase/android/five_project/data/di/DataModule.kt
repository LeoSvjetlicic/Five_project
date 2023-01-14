package agency.five.codebase.android.five_project.data.di

import agency.five.codebase.android.five_project.data.competitionRepository.CompetitionRepository
import agency.five.codebase.android.five_project.data.competitionRepository.CompetitionRepositoryImpl
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module

val dataModule = module {
    single<FirebaseFirestore> { FirebaseFirestore.getInstance() }
}
