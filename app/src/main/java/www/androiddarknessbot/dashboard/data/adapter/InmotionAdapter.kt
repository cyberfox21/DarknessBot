package www.androiddarknessbot.dashboard.data.adapter

import android.bluetooth.BluetoothGattService
import androidx.lifecycle.LiveData
import www.androiddarknessbot.dashboard.data.BluetoothService
import www.androiddarknessbot.dashboard.domain.adapter.BaseAdapter
import www.androiddarknessbot.dashboard.domain.entity.Characteristic

class InmotionAdapter() : BaseAdapter() {

    private val bluetoothService: BluetoothService = BluetoothService()

    override fun getCharacteristics(): LiveData<List<Characteristic>> {
        TODO("Not yet implemented")
    }

    override fun isCompatible(
        services: Map<String, BluetoothGattService>,
        bluetoothName: String?
    ): Boolean {
        return services.containsKey(WRITE_CHARACTERISTIC)
                && services.containsKey(READ_CHARACTERISTIC)
    }

    companion object {
        const val WRITE_CHARACTERISTIC = "ffe9"
        const val READ_CHARACTERISTIC = "ffe4"
    }

}