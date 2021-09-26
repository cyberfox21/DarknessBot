package www.androiddarknessbot.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import www.androiddarknessbot.domain.entity.BotBluetoothDevice
import www.androiddarknessbot.domain.repository.DeviceListRepositoty

class DeviceListRepositoryImpl : DeviceListRepositoty {

    private var _deviceListLD = MutableLiveData<List<BotBluetoothDevice>>()
    val deviceListLD: LiveData<List<BotBluetoothDevice>>
        get() = _deviceListLD

    private var deviceList =
        sortedSetOf<BotBluetoothDevice>(comparator = { // чтобы не повторялись
                o1, o2 ->
            (o1.id).compareTo(o2.id)
        })

    override fun getBluetoothDeviceList(): LiveData<List<BotBluetoothDevice>> {
        return deviceListLD
    }

    override fun getDevice(id: Int): BotBluetoothDevice {
        val device = deviceList.find { it.id == id }
        if (device == null) throw RuntimeException("Can't get device with id $id")
        else return device
    }

    override fun addDevice(device: BotBluetoothDevice) {
        deviceList.add(device)
        updateLD()
    }

    private fun updateLD() {
        _deviceListLD.value = deviceList.toList()
    }
}