package com.jmantello.notificationhub.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.util.Date

class NotificationViewModel(private val notificationRepository: NotificationRepository) : ViewModel() {

    // Expose Flow to the UI
    val notifications: Flow<List<NotificationEntity>> = flow {
        emit(notificationRepository.getAllNotifications())
    }.flowOn(Dispatchers.IO) // Ensure the Flow runs on a background thread

    init {
        viewModelScope.launch { mockNotificationsIfEmpty() }
    }

    private suspend fun mockNotificationsIfEmpty() {
        val currentNotifications = notificationRepository.getAllNotifications()
        if (currentNotifications.isEmpty()) {
            val mockNotifications = listOf(
                NotificationEntity(1, title = "Mock title", text = "Mock text", createdAt = Date().toString()),
                NotificationEntity(2, title = "Mock title", text = "Mock text", createdAt = Date().toString()),
                NotificationEntity(3, title = "Mock title", text = "Mock text", createdAt = Date().toString()),
            )
            mockNotifications.forEach { notificationRepository.insert(it) }
        }
    }

    fun insertNotification(notification: NotificationEntity) {
        viewModelScope.launch {
            notificationRepository.insert(notification)
        }
    }
}