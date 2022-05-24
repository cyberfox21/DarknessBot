package www.androiddarknessbot.core.di.module

import androidx.fragment.app.FragmentFactory
import dagger.Binds
import dagger.Module
import www.androiddarknessbot.core.AppFragmentFactory

/**
 * @author t.shkolnik
 */
@Module
abstract class MainModule {

    @Binds
    abstract fun fragmentFactory(fragmentFactory: AppFragmentFactory): FragmentFactory
}
