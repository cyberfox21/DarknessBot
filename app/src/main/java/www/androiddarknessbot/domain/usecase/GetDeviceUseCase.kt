package www.androiddarknessbot.domain.usecase

import www.androiddarknessbot.domain.entity.BotBluetoothDevice
import www.androiddarknessbot.domain.repository.DeviceListRepositoty

class GetDeviceUseCase(private val repository: DeviceListRepositoty) {
    operator fun invoke(id: Int) : BotBluetoothDevice{
        return repository.getDevice(id)
    }
}