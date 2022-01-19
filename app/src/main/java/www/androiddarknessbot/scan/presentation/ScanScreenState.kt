package www.androiddarknessbot.scan.presentation

import www.androiddarknessbot.scan.domain.entity.BotBluetoothDevice

sealed class ScanScreenState {
    object Empty : ScanScreenState()
    data class Scanning(val devices: List<BotBluetoothDevice>) : ScanScreenState()
    data class Result(val devices: List<BotBluetoothDevice>) : ScanScreenState()
}