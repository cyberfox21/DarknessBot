package www.androiddarknessbot.domain

import androidx.lifecycle.LiveData

interface DeviceListRepositoty {

    fun getBluetoothDeviceList(): LiveData<List<BotBluetoothDevice>>

    fun getDevice(id: Int): BotBluetoothDevice

    fun addDevice(device: BotBluetoothDevice)

}