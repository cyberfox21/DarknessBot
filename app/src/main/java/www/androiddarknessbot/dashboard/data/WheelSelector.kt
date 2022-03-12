package www.androiddarknessbot.dashboard.data

import android.bluetooth.BluetoothGattService
import www.androiddarknessbot.dashboard.domain.adapter.BaseAdapter


interface WheelSelector {
    val adapters: List<BaseAdapter>
    fun getAdapter(
        services: Map<String, BluetoothGattService>,
        //advertisementData : Map<String, Any>?,
        bluetoothName: String?
    ): BaseAdapter?
}