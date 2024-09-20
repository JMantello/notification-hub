package com.jmantello.notificationhub.di

import android.content.Context
import androidx.room.Room
import com.jmantello.notificationhub.data.room.NotificationDao
import com.jmantello.notificationhub.data.room.NotificationDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NotificationDatabase {
        return Room.databaseBuilder(
            context,
            NotificationDatabase::class.java,
            "notification_database"
        ).build()
    }

    @Provides
    fun provideNotificationDao(database: NotificationDatabase): NotificationDao {
        return database.notificationDao()
    }
}