package www.androiddarknessbot.scan.presentation

/**
 * @author t.shkolnik
 */
sealed class ScanScreenState {
    object Empty : ScanScreenState()
    data class Scanning(val devices: List<www.androiddarknessbot.core.domain.BotBluetoothDevice>) : ScanScreenState()
    data class Result(val devices: List<www.androiddarknessbot.core.domain.BotBluetoothDevice>) : ScanScreenState()
}
