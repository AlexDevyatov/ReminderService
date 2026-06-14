package com.reminder.service.data.repository

import com.reminder.service.data.local.ReminderDao
import com.reminder.service.data.local.toDomain
import com.reminder.service.data.local.toEntity
import com.reminder.service.domain.model.Reminder
import com.reminder.service.domain.repository.ReminderRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Singleton
class ReminderRepositoryImpl @Inject constructor(
    private val dao: ReminderDao,
) : ReminderRepository {

    override fun observeAll(): Flow<List<Reminder>> =
        dao.observeAll().map { list -> list.map { it.toDomain() } }

    override suspend fun getById(id: Long): Reminder? = dao.getById(id)?.toDomain()

    override suspend fun create(reminder: Reminder): Long = dao.insert(reminder.toEntity())

    override suspend fun update(reminder: Reminder) = dao.update(reminder.toEntity())

    override suspend fun delete(id: Long) = dao.delete(id)

    override suspend fun setEnabled(id: Long, enabled: Boolean) = dao.setEnabled(id, enabled)
}
