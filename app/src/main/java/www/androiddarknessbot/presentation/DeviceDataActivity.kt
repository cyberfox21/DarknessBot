package www.androiddarknessbot.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import www.androiddarknessbot.R
import www.androiddarknessbot.domain.entity.BotBluetoothDevice

class DeviceDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_data)
    }

    companion object{

        private const val DEVICE = "device"

        fun createIntent(context: Context, device: BotBluetoothDevice) : Intent {
            return Intent(context, DeviceDataActivity::class.java)
                .putExtra(DEVICE, device)
        }
    }
}