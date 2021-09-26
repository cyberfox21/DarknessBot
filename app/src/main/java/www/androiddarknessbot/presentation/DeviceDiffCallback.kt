package www.androiddarknessbot.presentation

import androidx.recyclerview.widget.DiffUtil
import www.androiddarknessbot.domain.entity.BotBluetoothDevice

class DeviceDiffCallback : DiffUtil.ItemCallback<BotBluetoothDevice>() {
    override fun areItemsTheSame(old: BotBluetoothDevice, new: BotBluetoothDevice): Boolean {
        return old.id == new.id
    }

    override fun areContentsTheSame(old: BotBluetoothDevice, new: BotBluetoothDevice): Boolean {
        return old == new
    }
}