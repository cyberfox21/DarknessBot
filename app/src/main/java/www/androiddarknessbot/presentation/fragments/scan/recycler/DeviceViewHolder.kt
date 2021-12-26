package www.androiddarknessbot.presentation.fragments.scan.recycler

import androidx.recyclerview.widget.RecyclerView
import www.androiddarknessbot.R
import www.androiddarknessbot.databinding.ItemDeviceBinding
import www.androiddarknessbot.domain.entity.BotBluetoothDevice
import www.androiddarknessbot.presentation.fragments.scan.OnBtnConnectClickListener

class DeviceViewHolder(
    private val binding: ItemDeviceBinding,
    private val onBtnConnectClickListener: OnBtnConnectClickListener
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: BotBluetoothDevice) {
        with(binding) {
            ivDevice.setImageResource(getImageResource(item.rssi))
            tvDeviceTitle.text =
                item.device.name ?: root.context.getString(R.string.unnamed)
            tvDeviceDescription.text = item.device.address
            btnConnectDevice.setOnClickListener {
                onBtnConnectClickListener.onBtnConnectClick(item)
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