package www.androiddarknessbot.core

import android.app.Application
import www.androiddarknessbot.core.di.AppComponentProvider
import www.androiddarknessbot.core.di.DaggerApplicationComponent

/**
 * @author t.shkolnik
 */
class App : Application(), AppComponentProvider {

    override val applicationComponent by lazy { DaggerApplicationComponent.factory().create(this) }

}
