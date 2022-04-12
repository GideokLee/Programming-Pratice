package com.ssafy.comp_03.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.ssafy.comp_03.notification.databinding.ActivityPictureNotificationBinding

class PictureNotification : AppCompatActivity() {

    private lateinit var binding: ActivityPictureNotificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPictureNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonNoti.setOnClickListener {
            createNotification()
        }
    }

    fun createNotification() {
        val channelId = "$packageName-$javaClass"
        val title = "Picture Notification"
        val content = "This is Picture notification"

        // Oreo 부터는 Notification Channel을 만들어야 함
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                channelId,
                title,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = this.getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }

        val intent = Intent(this, PictureNotification::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(this, 0,
            intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val style = NotificationCompat.BigPictureStyle()
        style.bigPicture(BitmapFactory.decodeResource(resources, R.drawable.grand))
//        style.bigLargeIcon(null)

        val builder = NotificationCompat.Builder(this, channelId)
        builder.setStyle(style)
        builder.setSmallIcon(R.drawable.ic_baseline_add_comment_24)
//        builder.setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.grand))
        builder.setContentTitle(title)
        builder.setContentText(content)
        builder.priority = NotificationCompat.PRIORITY_DEFAULT
        builder.setAutoCancel(true)
        builder.setContentIntent(pendingIntent)

        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(hashCode(), builder.build())
    }

}