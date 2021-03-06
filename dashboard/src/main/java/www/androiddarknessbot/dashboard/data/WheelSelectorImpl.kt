package www.androiddarknessbot.dashboard.data

import android.bluetooth.BluetoothGattService
import android.util.Log
import www.androiddarknessbot.dashboard.data.adapter.InmotionAdapter
import www.androiddarknessbot.dashboard.domain.adapter.BaseAdapter
import javax.inject.Inject

/**
 * @author t.shkolnik
 */
class WheelSelectorImpl @Inject constructor(
    private val inmotionAdapter: InmotionAdapter
) : WheelSelector {

    override val adapters = arrayListOf<BaseAdapter>(
        inmotionAdapter
    )

    override fun getAdapter(
        services: Map<String, BluetoothGattService>,
        //advertisementData : Map<String, Any>?,
        bluetoothName: String?
    ): BaseAdapter? {
        for (adapter in adapters) {
            if (adapter.isCompatible(
                    services,
                    // advertisementData,
                    bluetoothName
                )
            ) {
                Log.d("CHECKER", "Adapter $adapter found")
                return adapter
            }
        }
        Log.d("CHECKER", "No adapters found")
        return null
    }
}
