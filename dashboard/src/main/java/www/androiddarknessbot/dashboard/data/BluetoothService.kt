package www.androiddarknessbot.dashboard.data

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LifecycleService
import www.androiddarknessbot.dashboard.NotificationHelper
import www.androiddarknessbot.dashboard.di.annotation.ServiceScope
import javax.inject.Inject

/**
 * @author t.shkolnik
 */
@ServiceScope
class BluetoothService @Inject constructor(
    private val notificationHelper: NotificationHelper
) : LifecycleService() {

    override fun onCreate() {
        super.onCreate()
        notificationHelper.notificationChannel
        startForeground(NOTIFICATION_ID, notificationHelper.notification)
    }


    override fun onDestroy() {
        // cancel notification
        super.onDestroy()
    }

    companion object {
        const val NOTIFICATION_ID = 3
        const val NOTIFICATION_CHANNEL_NAME = "bluetooth_service_name"
        const val NOTIFICATION_CHANNEL_ID = "bluetooth_service_id"

        const val ACTION_START_SERVICE = "ACTION_START_OR_RESUME_SERVICE"
        const val ACTION_STOP_SERVICE = "ACTION_STOP_SERVICE"

        fun newIntent(context: Context, action: String): Intent =
            Intent(context, BluetoothService::class.java).apply { this.action = action }
    }

}
