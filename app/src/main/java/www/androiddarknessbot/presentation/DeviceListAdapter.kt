package www.androiddarknessbot.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import www.androiddarknessbot.R
import www.androiddarknessbot.databinding.ItemDeviceBinding
import www.androiddarknessbot.domain.entity.BotBluetoothDevice

class DeviceListAdapter : ListAdapter<BotBluetoothDevice, DeviceViewHolder>(DeviceDiffCallback()) {

    interface OnBtnConnectClickListener{
        fun onBtnClick()
    }

    val onBtnConnectClickListener: OnBtnConnectClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val binding = ItemDeviceBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DeviceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val item = currentList[position]
        with(holder.binding){
            ivDevice.setImageResource(getImageResource(item.rssi))
            tvDeviceTitle.text = item.device.name ?: root.context.getString(R.string.unnamed)
//            tvDeviceDescription.text = ...
            btnConnectDevice.setOnClickListener {
                onBtnConnectClickListener?.onBtnClick()
            }
        }
    }

    private fun getImageResource(rssi: Int) = when (rssi) {
            in -100..-67 -> R.drawable.bluetooth_low
            in -66..-53 -> R.drawable.bluetooth_middle
            in -52..0 -> R.drawable.bluetooth_high
            else -> R.drawable.bluetooth_zero
        }
}