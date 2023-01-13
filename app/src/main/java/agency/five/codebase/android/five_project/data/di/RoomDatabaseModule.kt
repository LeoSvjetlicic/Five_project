package agency.five.codebase.android.five_project.data.di

import agency.five.codebase.android.five_project.data.database.CompetitionAppDatabase
import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

private const val ADD_DATABASE_ROOM_NAME="app_database.db"
val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            CompetitionAppDatabase::class.java,
            ADD_DATABASE_ROOM_NAME,
        ).build()
    }
}
