package com.jmantello.notificationhub.data

import javax.inject.Inject

class NotificationRepository @Inject constructor(
    private val notificationDao: NotificationDao
) {
    suspend fun insert(notificationEntity: NotificationEntity) = notificationDao.insert(notificationEntity)

    suspend fun getAllNotifications() = notificationDao.getAllNotifications()
}