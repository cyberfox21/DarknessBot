package www.androiddarknessbot.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import www.androiddarknessbot.domain.entity.BotBluetoothDevice

class DeviceListAdapter : ListAdapter<BotBluetoothDevice, DeviceViewHolder>(DeviceDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}