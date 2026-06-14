package com.reminder.service.di

import com.reminder.service.domain.usecase.GetReminderByIdUseCase
import com.reminder.service.domain.usecase.SnoozeReminderUseCase
import com.reminder.service.domain.usecase.ObserveRemindersUseCase
import com.reminder.service.domain.usecase.UpdateReminderUseCase
import com.reminder.service.notification.NotificationHelper
import com.reminder.service.scheduling.AlarmSchedulerImpl
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Точка доступа к зависимостям из BroadcastReceiver, которые Hilt
 * не может инъектировать напрямую через @AndroidEntryPoint.
 */
@EntryPoint
@InstallIn(SingletonComponent::class)
interface ReceiverEntryPoint {
    fun notificationHelper(): NotificationHelper
    fun scheduler(): AlarmSchedulerImpl
    fun getByIdUseCase(): GetReminderByIdUseCase
    fun snoozeUseCase(): SnoozeReminderUseCase
    fun updateUseCase(): UpdateReminderUseCase
    fun observeUseCase(): ObserveRemindersUseCase
}
