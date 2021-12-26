package www.androiddarknessbot.presentation.fragments.scan

import www.androiddarknessbot.domain.entity.BotBluetoothDevice

interface OnBtnConnectClickListener {
    fun onBtnConnectClick(device: BotBluetoothDevice)
}