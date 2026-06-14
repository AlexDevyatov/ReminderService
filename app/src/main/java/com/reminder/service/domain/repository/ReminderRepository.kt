package com.reminder.service.domain.repository

import com.reminder.service.domain.model.Reminder
import kotlinx.coroutines.flow.Flow

/** Контракт репозитория напоминаний (доменный слой). */
interface ReminderRepository {

    /** Поток всех напоминаний, отсортированных по времени срабатывания. */
    fun observeAll(): Flow<List<Reminder>>

    /** Возвращает напоминание по id либо null. */
    suspend fun getById(id: Long): Reminder?

    /** Создаёт напоминание, возвращает его id. */
    suspend fun create(reminder: Reminder): Long

    /** Обновляет существующее напоминание. */
    suspend fun update(reminder: Reminder)

    /** Удаляет напоминание по id. */
    suspend fun delete(id: Long)

    /** Переключает флаг [Reminder.enabled]. Возвращает новое состояние. */
    suspend fun setEnabled(id: Long, enabled: Boolean)
}
