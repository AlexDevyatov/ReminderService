package com.reminder.service.domain.repository

import com.reminder.service.domain.model.Reminder

/**
 * Порт планировщика системных будильников.
 * Абстракция над AlarmManager для тестируемости.
 */
interface ReminderScheduler {

    /** Планирует точный будильник для [reminder]. */
    fun schedule(reminder: Reminder)

    /** Отменяет запланированный будильник для [reminderId]. */
    fun cancel(reminderId: Long)
}
