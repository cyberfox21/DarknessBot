package www.androiddarknessbot.scan.presentation.recycler

import androidx.recyclerview.widget.DiffUtil
import www.androiddarknessbot.core.domain.BotBluetoothDevice

/**
 * @author t.shkolnik
 */
class DeviceDiffCallback :
    DiffUtil.ItemCallback<BotBluetoothDevice>() {

    override fun areItemsTheSame(old: BotBluetoothDevice, new: BotBluetoothDevice): Boolean {
        return old.device.address == new.device.address
    }

    override fun areContentsTheSame(old: BotBluetoothDevice, new: BotBluetoothDevice): Boolean {
        return old == new
    }
}
