package com.jmantello.notificationhub.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notifications")
data class NotificationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val text: String,
    val createdAt: String
)