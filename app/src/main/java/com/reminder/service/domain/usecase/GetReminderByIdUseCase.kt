package com.reminder.service.domain.usecase

import com.reminder.service.domain.model.Reminder
import com.reminder.service.domain.repository.ReminderRepository
import javax.inject.Inject

/** Возвращает напоминание по id либо null. */
class GetReminderByIdUseCase @Inject constructor(
    private val repository: ReminderRepository,
) {
    suspend operator fun invoke(id: Long): Reminder? = repository.getById(id)
}
