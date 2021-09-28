package www.androiddarknessbot.domain.usecase

import androidx.lifecycle.LiveData
import www.androiddarknessbot.domain.entity.BotBluetoothDevice
import www.androiddarknessbot.domain.repository.DeviceListRepositoty



class AddDeviceUseCase(private val repository: DeviceListRepositoty) {
    operator fun invoke(botDevice: BotBluetoothDevice) {
        repository.addDevice(botDevice)
    }
}