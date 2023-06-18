package com.example.dptermproject.todo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.dptermproject.R

// 알림 채널을 생성하는 함수
private fun createNotificationChannel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channelId = "my_channel_id"
        val channelName = "My Channel"
        val channelDescription = "This is my notification channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT

        val channel = NotificationChannel(channelId, channelName, importance).apply {
            description = channelDescription
            enableLights(true)
            lightColor = Color.RED
        }

        val notificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

// 알림을 보내는 함수
fun sendNotification(context: Context, title: String, message: String) {
    val channelId = "my_channel_id"
    val notificationId = 1

    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.ic_palette)
        .setContentTitle(title)
        .setContentText(message)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setAutoCancel(true)

    val notificationManager =
        context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

    //알림 채널 생성
    createNotificationChannel(context)

    //알림 보냄
    notificationManager.notify(notificationId, builder.build())
}
