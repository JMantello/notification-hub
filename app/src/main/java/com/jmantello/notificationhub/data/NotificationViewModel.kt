package com.jmantello.notificationhub.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class NotificationViewModel(private val notificationRepository: NotificationRepository) : ViewModel() {

    // Expose Flow to the UI
    val notifications: Flow<List<NotificationEntity>> = flow {
        emit(notificationRepository.getAllNotifications())
    }.flowOn(Dispatchers.IO) // Ensure the Flow runs on a background thread

    fun insertNotification(notification: NotificationEntity) {
        viewModelScope.launch {
            notificationRepository.insert(notification)
        }
    }
}