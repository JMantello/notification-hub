package com.jmantello.notificationhub.ui.listeners

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.jmantello.notificationhub.data.NotificationRepository
import com.jmantello.notificationhub.data.room.NotificationDatabase
import com.jmantello.notificationhub.data.room.NotificationEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@AndroidEntryPoint
class NotificationListener : NotificationListenerService() {

    @Inject lateinit var notificationRepository: NotificationRepository

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
            notificationRepository.insert(notificationEntity)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceJob.cancel() // Clean up the job
    }
}