package pedro.com.ioasystestekotlin.application

import android.app.Application
import org.koin.android.ext.android.startKoin
import pedro.com.ioasystestekotlin.di.appModule
import pedro.com.ioasystestekotlin.di.domainModule
import pedro.com.ioasystestekotlin.di.presentationModule

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, domainModule, presentationModule))
    }

}