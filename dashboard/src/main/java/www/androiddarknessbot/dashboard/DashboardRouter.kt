package www.androiddarknessbot.dashboard

import android.content.Intent
import www.androiddarknessbot.core.domain.BotBluetoothDevice

/**
 * @author t.shkolnik
 */
interface DashboardRouter {

    fun onSettingsClick(oldFragmentTag: String)

    fun createIntentToDashboardFromNotification(
        device: BotBluetoothDevice?
    ): Intent
}
