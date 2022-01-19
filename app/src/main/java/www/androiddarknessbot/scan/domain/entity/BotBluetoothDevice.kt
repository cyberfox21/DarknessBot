package www.androiddarknessbot.scan.domain.entity

import android.bluetooth.BluetoothDevice
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BotBluetoothDevice(
    val id: Int = UNDEFINED_ID,
    val device: BluetoothDevice,
    val rssi: Int
) : Parcelable {
    companion object {
        private const val UNDEFINED_ID = -1
    }
}
