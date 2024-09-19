package com.jmantello.notificationhub.data

class NotificationRepository(private val notificationDao: NotificationDao) {
    suspend fun insert(notificationEntity: NotificationEntity) = notificationDao.insert(notificationEntity)

    suspend fun getAllNotifications() = notificationDao.getAllNotifications()
}