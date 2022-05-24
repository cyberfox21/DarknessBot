package www.androiddarknessbot.core.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import www.androiddarknessbot.core.di.annotation.AppScope
import www.androiddarknessbot.core.di.module.BluetoothServiceModule
import www.androiddarknessbot.core.di.module.MainModule
import www.androiddarknessbot.core.di.module.NavigationModule
import www.androiddarknessbot.core.di.module.SubcomponentsModule
import javax.inject.Singleton

/**
 * @author t.shkolnik
 */
@Singleton
@Component(
    modules = [
        BluetoothServiceModule::class,
        SubcomponentsModule::class
    ]
)
interface ApplicationComponent {

    @AppScope
    @Component.Factory
    interface ApplicationComponentFactory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun mainComponentFactory(): MainComponent.Factory
}
