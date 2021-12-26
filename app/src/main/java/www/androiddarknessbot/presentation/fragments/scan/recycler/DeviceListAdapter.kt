package www.androiddarknessbot.presentation.fragments.scan.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import www.androiddarknessbot.databinding.ItemDeviceBinding
import www.androiddarknessbot.domain.entity.BotBluetoothDevice
import www.androiddarknessbot.presentation.fragments.scan.OnBtnConnectClickListener

class DeviceListAdapter(private val onBtnConnectClickListener: OnBtnConnectClickListener) :
    ListAdapter<BotBluetoothDevice, DeviceViewHolder>(DeviceDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder =
        DeviceViewHolder(
            ItemDeviceBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onBtnConnectClickListener
        )

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) =
        holder.bind(currentList[position])

}