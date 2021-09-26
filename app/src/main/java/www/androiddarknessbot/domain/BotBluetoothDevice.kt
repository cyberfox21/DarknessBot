package www.androiddarknessbot.domain

import android.bluetooth.BluetoothDevice
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class BotBluetoothDevice(
    val id: Int,
    val device: BluetoothDevice,
    val rssi: Int
) : Parcelable
