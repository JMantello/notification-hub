package com.jmantello.notificationhub.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NotificationDao {
    @Insert
    suspend fun insert(notification: NotificationEntity)

    @Query("SELECT * FROM notifications")
    suspend fun getAllNotifications(): List<NotificationEntity>
}
