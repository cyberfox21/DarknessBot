package www.androiddarknessbot.core.di.module

import androidx.fragment.app.FragmentFactory
import dagger.Binds
import dagger.Module
import www.androiddarknessbot.core.NavigationHolderImpl
import www.androiddarknessbot.core.navigation.DashboardRouterImpl
import www.androiddarknessbot.core.navigation.ScanRouterImpl
import www.androiddarknessbot.dashboard.DashboardRouter
import www.androiddarknessbot.navigation.NavigationHolder
import www.androiddarknessbot.scan.presentation.ScanRouter

/**
 * @author t.shkolnik
 */
@Module
interface NavigationModule {

    @Binds
    fun navigationHolder(navigationHolder: NavigationHolderImpl): NavigationHolder

    @Binds
    fun scanRouter(scanRouter: ScanRouterImpl): ScanRouter

    @Binds
    fun dashboardRouter(dashboardRouter: DashboardRouterImpl): DashboardRouter

//    @Binds
//    fun dashboardRouter(scanRouter: ScanRouterImpl): ScanRouter
//
//    @Binds
//    fun settingsRouter(scanRouter: ScanRouterImpl): ScanRouter
}
