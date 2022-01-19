package www.androiddarknessbot.scan.presentation

import www.androiddarknessbot.scan.domain.entity.BotBluetoothDevice

interface OnBtnConnectClickListener {
    fun onBtnConnectClick(device: BotBluetoothDevice)
}