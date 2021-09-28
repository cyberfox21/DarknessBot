package www.androiddarknessbot.domain.usecase

import androidx.lifecycle.LiveData
import www.androiddarknessbot.domain.entity.BotBluetoothDevice
import www.androiddarknessbot.domain.repository.DeviceListRepositoty

class GetDeviceListUseCase(private val repository: DeviceListRepositoty) {
    operator fun invoke() : LiveData<List<BotBluetoothDevice>> {
        return repository.getDeviceList()
    }
}