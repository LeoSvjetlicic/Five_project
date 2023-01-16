package agency.five.codebase.android.five_project

import agency.five.codebase.android.five_project.data.competitionRepository.di.competitionModule
import agency.five.codebase.android.five_project.data.di.dataModule
import agency.five.codebase.android.five_project.data.di.databaseModule
import agency.five.codebase.android.five_project.data.di.firebaseModule
import agency.five.codebase.android.five_project.data.teamRepository.di.teamModule
import agency.five.codebase.android.five_project.ui.competitiondetails.di.competitionDetailsModule
import agency.five.codebase.android.five_project.ui.followed.di.followedModule
import agency.five.codebase.android.five_project.ui.home.di.homeModule
import agency.five.codebase.android.five_project.ui.teamdetails.di.teamDetailsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import android.app.Application
import android.util.Log

class MainActivity : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@MainActivity)
            modules(
                dataModule,
                followedModule,
                competitionDetailsModule,
                homeModule,
                teamDetailsModule,
                teamModule,
                competitionModule,
                databaseModule,
                firebaseModule
            )
            Log.d("CompetitionApp", "App started")
        }
    }
}
