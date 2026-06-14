package com.reminder.service.scheduling

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.reminder.service.domain.model.Reminder
import com.reminder.service.domain.repository.ReminderScheduler
import com.reminder.service.util.toRequestCode
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Реализация планировщика поверх системного [AlarmManager].
 *
 * Будильники планируются через [AlarmManager.setExactAndAllowWhileIdle],
 * чтобы срабатывать даже в Doze-режиме. Никаких облачных календарей —
 * только локальное время устройства.
 */
@Singleton
class AlarmSchedulerImpl @Inject constructor(
    private val alarmManager: AlarmManager,
    @ApplicationContext private val context: Context,
) : ReminderScheduler {

    override fun schedule(reminder: Reminder) {
        val triggerAt = if (reminder.triggerAtMillis <= System.currentTimeMillis()) {
            // Если время уже прошло — немедленно.
            System.currentTimeMillis()
        } else {
            reminder.triggerAtMillis
        }
        val pendingIntent = buildPendingIntent(reminder.id)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (alarmManager.canScheduleExactAlarms()) {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP, triggerAt, pendingIntent,
                )
            } else {
                // Фолбэк: неточный будильник, лучше чем ничего.
                alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAt, pendingIntent)
            }
        } else {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP, triggerAt, pendingIntent,
            )
        }
    }

    override fun cancel(reminderId: Long) {
        alarmManager.cancel(buildPendingIntent(reminderId))
    }

    private fun buildPendingIntent(reminderId: Long): PendingIntent {
        val intent = Intent(context, ReminderReceiver::class.java).apply {
            action = ReminderReceiver.ACTION_FIRE
            putExtra(ReminderReceiver.EXTRA_REMINDER_ID, reminderId)
        }
        val flags = PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        return PendingIntent.getBroadcast(
            context, reminderId.toRequestCode(), intent, flags,
        )
    }
}
