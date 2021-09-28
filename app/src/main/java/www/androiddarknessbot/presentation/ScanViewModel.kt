package www.androiddarknessbot.presentation

import androidx.lifecycle.ViewModel
import www.androiddarknessbot.data.DeviceListRepositoryImpl
import www.androiddarknessbot.domain.entity.BotBluetoothDevice
import www.androiddarknessbot.domain.usecase.AddDeviceUseCase
import www.androiddarknessbot.domain.usecase.GetDeviceListUseCase
import www.androiddarknessbot.domain.usecase.GetDeviceUseCase

class ScanViewModel : ViewModel() {

    private val repository = DeviceListRepositoryImpl()

    private val getDeviceListUseCase = GetDeviceListUseCase(repository)
    private val getDeviceUseCase = GetDeviceUseCase(repository)
    private val addDeviceUseCase = AddDeviceUseCase(repository)

    val deviceListLD = getDeviceListUseCase()

    fun getDevice(id: Int) = getDeviceUseCase(id)

    fun addDevice(device: BotBluetoothDevice) = addDeviceUseCase(device)

}