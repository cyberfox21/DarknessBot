package www.androiddarknessbot.core.di

import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.FragmentManager
import dagger.BindsInstance
import dagger.Subcomponent
import www.androiddarknessbot.core.di.annotation.MainScope
import www.androiddarknessbot.core.di.module.FragmentsModule
import www.androiddarknessbot.core.di.module.MainModule
import www.androiddarknessbot.core.di.module.NavigationModule

/**
 * @author t.shkolnik
 */
@MainScope
@Subcomponent(
    modules = [
        MainModule::class,
        FragmentsModule::class,
        NavigationModule::class
    ]
)
interface MainComponent {

    val fragmentFactory: FragmentFactory

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance fragmentManager: FragmentManager): MainComponent
    }
}
