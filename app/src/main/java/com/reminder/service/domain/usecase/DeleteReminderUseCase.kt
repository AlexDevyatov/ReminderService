package com.reminder.service.domain.usecase

import com.reminder.service.domain.repository.ReminderRepository
import com.reminder.service.domain.repository.ReminderScheduler
import javax.inject.Inject

/** Удаляет напоминание и отменяет его будильник. */
class DeleteReminderUseCase @Inject constructor(
    private val repository: ReminderRepository,
    private val scheduler: ReminderScheduler,
) {
    suspend operator fun invoke(id: Long) {
        require(id > 0L) { "Id must be positive" }
        scheduler.cancel(id)
        repository.delete(id)
    }
}
