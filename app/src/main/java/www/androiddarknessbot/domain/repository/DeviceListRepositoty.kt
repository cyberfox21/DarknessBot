package www.androiddarknessbot.domain.repository

import androidx.lifecycle.LiveData
import www.androiddarknessbot.domain.entity.BotBluetoothDevice

interface DeviceListRepositoty {

    fun getBluetoothDeviceList(): LiveData<List<BotBluetoothDevice>>

    fun getDevice(id: Int): BotBluetoothDevice

    fun addDevice(device: BotBluetoothDevice)

}