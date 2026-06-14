package com.reminder.service.domain.usecase

import com.reminder.service.domain.model.Reminder
import com.reminder.service.domain.repository.ReminderRepository
import com.reminder.service.domain.repository.ReminderScheduler
import javax.inject.Inject

/**
 * Создаёт напоминание и планирует его будильник.
 */
class CreateReminderUseCase @Inject constructor(
    private val repository: ReminderRepository,
    private val scheduler: ReminderScheduler,
) {
    suspend operator fun invoke(reminder: Reminder): Long {
        require(reminder.title.isNotBlank()) { "Title must not be blank" }
        require(reminder.triggerAtMillis > 0L) { "Trigger time must be positive" }
        val id = repository.create(reminder)
        val saved = reminder.copy(id = id)
        if (saved.enabled) {
            scheduler.schedule(saved)
        }
        return id
    }
}
