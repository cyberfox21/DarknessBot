package www.androiddarknessbot.core

import android.app.Application
import www.androiddarknessbot.core.di.DaggerApplicationComponent

class App : Application() {

    val applicationComponent by lazy { DaggerApplicationComponent.factory().create(this) }

}