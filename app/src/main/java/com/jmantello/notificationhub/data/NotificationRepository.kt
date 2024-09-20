package com.jmantello.notificationhub.data

import com.jmantello.notificationhub.data.room.NotificationDao
import com.jmantello.notificationhub.data.room.NotificationEntity
import javax.inject.Inject

class NotificationRepository @Inject constructor(
    private val notificationDao: NotificationDao
) {
    suspend fun insert(notificationEntity: NotificationEntity) = notificationDao.insert(notificationEntity)

    suspend fun getAllNotifications() = notificationDao.getAllNotifications()
}