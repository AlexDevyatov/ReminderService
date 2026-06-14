package com.reminder.service.domain.usecase

import com.reminder.service.domain.model.Reminder
import com.reminder.service.domain.repository.ReminderRepository
import com.reminder.service.domain.repository.ReminderScheduler
import javax.inject.Inject

/**
 * Обновляет напоминание: отменяет старый будильник и планирует новый.
 */
class UpdateReminderUseCase @Inject constructor(
    private val repository: ReminderRepository,
    private val scheduler: ReminderScheduler,
) {
    suspend operator fun invoke(reminder: Reminder) {
        require(reminder.id > 0L) { "Reminder id must be set for update" }
        require(reminder.title.isNotBlank()) { "Title must not be blank" }
        scheduler.cancel(reminder.id)
        repository.update(reminder)
        if (reminder.enabled) {
            scheduler.schedule(reminder)
        }
    }
}
