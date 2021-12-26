package www.androiddarknessbot.presentation.fragments.scan

import www.androiddarknessbot.domain.entity.BotBluetoothDevice

sealed class ScanScreenState {
    object Empty : ScanScreenState()
    data class Scanning(val devices: List<BotBluetoothDevice>) : ScanScreenState()
    data class Result(val devices: List<BotBluetoothDevice>) : ScanScreenState()
}