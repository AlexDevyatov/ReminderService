package com.reminder.service.domain.usecase

import com.reminder.service.domain.model.Reminder
import com.reminder.service.domain.repository.ReminderRepository
import com.reminder.service.domain.repository.ReminderScheduler
import javax.inject.Inject

/**
 * Откладывает напоминание на [minutes] минут: обновляет сохранённое время
 * и перепланирует будильник. Возвращает обновлённое напоминание.
 */
class SnoozeReminderUseCase @Inject constructor(
    private val repository: ReminderRepository,
    private val scheduler: ReminderScheduler,
) {
    suspend operator fun invoke(
        reminder: Reminder,
        minutes: Long = DEFAULT_SNOOZE_MINUTES,
        now: Long = System.currentTimeMillis(),
    ): Reminder {
        require(minutes > 0L) { "Snooze minutes must be positive" }
        require(reminder.id > 0L) { "Reminder id must be set" }
        val newTrigger = now + minutes * 60L * 1000L
        val snoozed = reminder.copy(triggerAtMillis = newTrigger)
        repository.update(snoozed)
        scheduler.cancel(snoozed.id)
        scheduler.schedule(snoozed)
        return snoozed
    }

    companion object {
        const val DEFAULT_SNOOZE_MINUTES = 5L
    }
}
