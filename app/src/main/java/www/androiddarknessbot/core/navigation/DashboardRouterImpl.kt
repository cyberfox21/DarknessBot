package www.androiddarknessbot.core.navigation

import android.content.Context
import android.content.Intent
import www.androiddarknessbot.core.NavigationHolderImpl
import www.androiddarknessbot.core.domain.BotBluetoothDevice
import www.androiddarknessbot.core.presentation.MainActivity
import www.androiddarknessbot.dashboard.DashboardRouter
import www.androiddarknessbot.settings.presentation.SettingsFragment
import javax.inject.Inject

/**
 * @author t.shkolnik
 */
class DashboardRouterImpl @Inject constructor(
    private val context: Context,
    private val navigationHolder: NavigationHolderImpl,
) : DashboardRouter {
    override fun onSettingsClick(oldFragmentTag: String) {
        navigationHolder.addFragment(
            SettingsFragment.newInstance(),
            oldFragmentTag,
            SettingsFragment.TAG
        )
    }

    override fun createIntentToDashboardFromNotification(
        device: BotBluetoothDevice?
    ): Intent {
        return MainActivity.newIntentFromNotification(context, device?.device)
    }
}
