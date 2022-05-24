package www.androiddarknessbot.scan.presentation.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import www.androiddarknessbot.scan.databinding.ItemDeviceBinding
import www.androiddarknessbot.scan.presentation.ScanRouter

/**
 * @author t.shkolnik
 */
class DeviceListAdapter(private val scanRouter: ScanRouter) :
    ListAdapter<www.androiddarknessbot.core.domain.BotBluetoothDevice, DeviceViewHolder>(DeviceDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder =
        DeviceViewHolder(
            ItemDeviceBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            scanRouter
        )

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) =
        holder.bind(currentList[position])

}
