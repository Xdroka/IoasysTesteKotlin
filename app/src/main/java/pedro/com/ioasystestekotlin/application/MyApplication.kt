package pedro.com.ioasystestekotlin.application

import android.app.Application
import org.koin.android.ext.android.startKoin
import pedro.com.ioasystestekotlin.application.di.appModule
import pedro.com.ioasystestekotlin.application.di.dataModule
import pedro.com.ioasystestekotlin.application.di.domainModule
import pedro.com.ioasystestekotlin.application.di.presentationModule

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this,
                listOf(appModule, dataModule, domainModule, presentationModule)
        )
    }

}