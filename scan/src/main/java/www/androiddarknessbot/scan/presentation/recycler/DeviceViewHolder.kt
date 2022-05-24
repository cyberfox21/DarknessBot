package www.androiddarknessbot.scan.presentation.recycler

import androidx.recyclerview.widget.RecyclerView
import www.androiddarknessbot.scan.R
import www.androiddarknessbot.scan.databinding.ItemDeviceBinding
import www.androiddarknessbot.scan.presentation.ScanFragment
import www.androiddarknessbot.scan.presentation.ScanRouter

/**
 * @author t.shkolnik
 */
class DeviceViewHolder(
    private val binding: ItemDeviceBinding,
    private val scanRouter: ScanRouter
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: www.androiddarknessbot.core.domain.BotBluetoothDevice) {
        with(binding) {
            ivDevice.setImageResource(getImageResource(item.rssi))
            tvDeviceTitle.text =
                item.device.name ?: root.context.getString(R.string.unnamed)
            tvDeviceDescription.text = item.device.address
            btnConnectDevice.setOnClickListener {
                scanRouter.showDashboardInDeviceMode(
                    device = item,
                    oldFragmentTag = ScanFragment.TAG
                )
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
