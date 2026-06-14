package com.reminder.service.di

import android.content.Context
import androidx.room.Room
import com.reminder.service.data.local.ReminderDatabase
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
    fun provideDatabase(@ApplicationContext context: Context): ReminderDatabase =
        Room.databaseBuilder(context, ReminderDatabase::class.java, "reminders.db")
            .build()

    @Provides
    fun provideReminderDao(db: ReminderDatabase) = db.reminderDao()
}
