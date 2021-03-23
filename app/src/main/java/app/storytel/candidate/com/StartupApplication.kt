package app.storytel.candidate.com

import android.app.Application
import com.storytel.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class StartupApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        //Init Koin injections
        startKoin {
            androidContext(this@StartupApplication)
            modules(
                    arrayListOf(
                            networkModule,
                            appModule
                    )
            )
        }
    }
}