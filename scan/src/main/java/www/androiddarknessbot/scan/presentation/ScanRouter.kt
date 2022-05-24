package www.androiddarknessbot.scan.presentation

import www.androiddarknessbot.core.domain.BotBluetoothDevice

/**
 * @author t.shkolnik
 */
interface ScanRouter {

    fun showDashboardInDeviceMode(device: BotBluetoothDevice, oldFragmentTag: String)

    fun showDashboardInDemoMode(oldFragmentTag: String)

    fun showSettings(oldFragmentTag: String)
}
