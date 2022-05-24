package www.androiddarknessbot.core.navigation

import www.androiddarknessbot.core.NavigationHolderImpl
import www.androiddarknessbot.core.domain.BotBluetoothDevice
import www.androiddarknessbot.dashboard.presentation.DashboardFragment
import www.androiddarknessbot.scan.presentation.ScanRouter
import www.androiddarknessbot.settings.presentation.SettingsFragment
import javax.inject.Inject

/**
 * @author t.shkolnik
 */
class ScanRouterImpl @Inject constructor(
    private val navigationHolder: NavigationHolderImpl,
) : ScanRouter {

    override fun showDashboardInDeviceMode(device: BotBluetoothDevice, oldFragmentTag: String) {
        navigationHolder.addFragment(
            DashboardFragment.newInstanceDevice(device),
            oldFragmentTag,
            DashboardFragment.TAG
        )
    }

    override fun showDashboardInDemoMode(oldFragmentTag: String) {
        navigationHolder.addFragment(
            DashboardFragment.newInstanceDemo(),
            oldFragmentTag,
            DashboardFragment.TAG
        )
    }

    override fun showSettings(oldFragmentTag: String) {
        navigationHolder.addFragment(
            SettingsFragment.newInstance(),
            oldFragmentTag,
            SettingsFragment.TAG
        )
    }
}
