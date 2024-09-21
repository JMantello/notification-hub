package com.jmantello.notificationhub.constants

import android.app.NotificationManager

object NotificationConstants {
    const val CHANNEL_ID = "notification_listener_channel"
    const val CHANNEL_NAME = "Notification Listener"
    const val CHANNEL_DESCRIPTION = "Listens for system notifications."
    const val CHANNEL_IMPORTANCE = NotificationManager.IMPORTANCE_DEFAULT
}