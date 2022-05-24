package www.androiddarknessbot.core.di.module

import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import www.androiddarknessbot.core.di.annotation.FragmentKey
import www.androiddarknessbot.dashboard.presentation.DashboardFragment
import www.androiddarknessbot.scan.presentation.ScanFragment
import www.androiddarknessbot.settings.presentation.SettingsFragment

/**
 * @author t.shkolnik
 */
@Module
abstract class FragmentsModule {

    @Binds
    @IntoMap
    @FragmentKey(ScanFragment::class)
    abstract fun scanFragment(listFragment: ScanFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(DashboardFragment::class)
    abstract fun dashboardFragment(detailFragment: DashboardFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(SettingsFragment::class)
    abstract fun settingsFragment(detailFragment: DashboardFragment): Fragment
}
