package www.androiddarknessbot.dashboard

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import www.androiddarknessbot.dashboard.data.BluetoothService
import javax.inject.Inject

/**
 * @author t.shkolnik
 */
class NotificationHelper @Inject constructor(
    private val dashboardRouter: DashboardRouter,
    private val bluetoothService: BluetoothService
) {

    val notification: Notification = createNotification()
    val notificationChannel = createNotificationChannel()

    // send Device to open
    val intent =
        dashboardRouter.createIntentToDashboardFromNotification(device = null)

    private val pendingIntent: PendingIntent =
        PendingIntent.getActivity(bluetoothService, 0, intent, PendingIntent.FLAG_IMMUTABLE)

    private fun createNotification() =
        NotificationCompat.Builder(bluetoothService, NOTIFICATION_CHANNEL_ID)
            .setContentTitle("DarknessBot")
            .setContentText("Connected to wheel. Exchanging data...")
            .setContentIntent(pendingIntent)
            .build()


    private fun createNotificationChannel() {
        val notificationManager =
            bluetoothService.getSystemService(LifecycleService.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    companion object {
        const val NOTIFICATION_ID = 3
        const val NOTIFICATION_CHANNEL_NAME = "bluetooth_service_name"
        const val NOTIFICATION_CHANNEL_ID = "bluetooth_service_id"
    }

}
