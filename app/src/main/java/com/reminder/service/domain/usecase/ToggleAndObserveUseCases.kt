package com.reminder.service.domain.usecase

import com.reminder.service.domain.model.Reminder
import com.reminder.service.domain.repository.ReminderRepository
import com.reminder.service.domain.repository.ReminderScheduler
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

/** Включает/выключает напоминание и соответствующим образом (пере)планирует будильник. */
class ToggleReminderUseCase @Inject constructor(
    private val repository: ReminderRepository,
    private val scheduler: ReminderScheduler,
) {
    suspend operator fun invoke(id: Long, enabled: Boolean) {
        require(id > 0L) { "Id must be positive" }
        repository.setEnabled(id, enabled)
        if (enabled) {
            repository.getById(id)?.let(scheduler::schedule)
        } else {
            scheduler.cancel(id)
        }
    }

    /** Переключает текущее состояние на противоположное. */
    suspend fun toggle(id: Long) {
        val current = repository.getById(id) ?: return
        invoke(id, !current.enabled)
    }
}

/** Возвращает поток всех напоминаний. */
class ObserveRemindersUseCase @Inject constructor(
    private val repository: ReminderRepository,
) {
    operator fun invoke(): Flow<List<Reminder>> = repository.observeAll()
}
