package com.jmantello.notificationhub.listeners

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.jmantello.notificationhub.data.NotificationDatabase
import com.jmantello.notificationhub.data.NotificationEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.Date

class NotificationListener : NotificationListenerService() {
    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.IO + serviceJob)

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        val notification = sbn?.notification
        val extras = notification?.extras
        val title = extras?.getString("android.title")
        val text = extras?.getCharSequence("android.text").toString()

        val notificationEntity = NotificationEntity(
            100,
            title ?: "No title",
            text,
            Date().toString()
        )

        serviceScope.launch {
            NotificationDatabase.getInstance(applicationContext).notificationDao()
                .insert(notificationEntity)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceJob.cancel() // Clean up the job
    }
}