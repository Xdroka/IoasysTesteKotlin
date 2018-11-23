package pedro.com.ioasystestekotlin.application

import android.app.Application
import android.arch.persistence.room.Room
import org.koin.android.ext.android.startKoin
import pedro.com.ioasystestekotlin.application.di.appModule
import pedro.com.ioasystestekotlin.application.di.dataModule
import pedro.com.ioasystestekotlin.application.di.domainModule
import pedro.com.ioasystestekotlin.application.di.presentationModule
import pedro.com.ioasystestekotlin.data.cache.room.AppDataBase

class MyApplication : Application() {
    companion object {
        var database: AppDataBase? = null
        const val DATABASE_NAME = "EnterprisesDb"
        var EMAIL = ""
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
                this,
                AppDataBase::class.java,
                DATABASE_NAME
        ).build()

        startKoin(this,
                listOf(appModule, dataModule, domainModule, presentationModule)
        )
    }

}