package www.androiddarknessbot.dashboard.domain.adapter

import android.bluetooth.BluetoothGattService
import androidx.lifecycle.LiveData
import www.androiddarknessbot.dashboard.domain.entity.Characteristic

abstract class BaseAdapter {

    abstract fun getCharacteristics(): LiveData<List<Characteristic>>

    /** Is compatible with services. Must inherit **/
    open fun isCompatible(
        services: Map<String, BluetoothGattService>,
        //advertisementData : Map<String, Any>?,
        bluetoothName: String?
    ): Boolean {
        return false
    }
}