package com.jmantello.notificationhub.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "notifications")
data class NotificationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val text: String,
    val createdAt: String
)