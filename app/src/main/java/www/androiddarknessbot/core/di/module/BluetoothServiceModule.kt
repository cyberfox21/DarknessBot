package www.androiddarknessbot.core.di.module

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import androidx.core.app.NotificationCompat
import dagger.Module
import dagger.Provides
import www.androiddarknessbot.R
import www.androiddarknessbot.core.di.annotation.ServiceScope
import www.androiddarknessbot.dashboard.data.BluetoothService

@Module
class BluetoothServiceModule {

    @Provides
    @ServiceScope
    fun provideNotificationManager(context: Context) =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    @Provides
    @ServiceScope
    fun provideNotificationBuilder(context: Context, pendingIntent: PendingIntent) =
        NotificationCompat.Builder(context, BluetoothService.NOTIFICATION_CHANNEL_ID)
            .setAutoCancel(false)
            .setOngoing(true)
            .setSmallIcon(R.mipmap.ic_logo_foreground)
            .setContentTitle("DarknessBot")
            .setContentText("Connected to wheel. Exchanging data...")
            .setContentIntent(pendingIntent)


    // нужно запустить фрагмент dashboard

//    @Provides
//    @ServiceScope
//    fun provideDashboardScreenPendingIntent(context: Context) =
//        PendingIntent.getActivity(
//            context,
//            420,
//            Intent(context, MainActivity::class.java).apply {
//                this.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
//            },
//            PendingIntent.FLAG_UPDATE_CURRENT
//        )
}