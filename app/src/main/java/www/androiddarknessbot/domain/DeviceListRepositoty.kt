package www.androiddarknessbot.domain

import androidx.lifecycle.MutableLiveData

interface DeviceListRepositoty {

    fun getBluetoothDeviceList() : MutableLiveData<List<BotBluetoothDevice>>

    fun getDevice(id : Int) : BotBluetoothDevice

    fun addDevice(device: BotBluetoothDevice)

}