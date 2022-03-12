package www.androiddarknessbot.core.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import www.androiddarknessbot.core.di.module.BluetoothServiceModule

@Component(modules = [BluetoothServiceModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface ApplicationComponentFactory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

}