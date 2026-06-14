package com.reminder.service.data.repository

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.reminder.service.data.local.ReminderDatabase
import com.reminder.service.domain.model.Reminder
import com.reminder.service.domain.model.RepeatInterval
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ReminderRepositoryImplTest {

    private lateinit var db: ReminderDatabase
    private lateinit var repository: ReminderRepositoryImpl

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ReminderDatabase::class.java,
        ).allowMainThreadQueries().build()
        repository = ReminderRepositoryImpl(db.reminderDao())
    }

    @After
    fun tearDown() = db.close()

    @Test
    fun `create assigns id and can be retrieved`() = runTest {
        val id = repository.create(Reminder(title = "Buy milk", triggerAtMillis = 1_000L))
        assertThat(id).isGreaterThan(0L)

        val loaded = repository.getById(id)
        assertThat(loaded?.title).isEqualTo("Buy milk")
    }

    @Test
    fun `observeAll returns items sorted by trigger time`() = runTest {
        repository.create(Reminder(title = "Late", triggerAtMillis = 3_000L))
        repository.create(Reminder(title = "Early", triggerAtMillis = 1_000L))
        repository.create(Reminder(title = "Mid", triggerAtMillis = 2_000L))

        val all = repository.observeAll().first()
        assertThat(all.map { it.title }).containsExactly("Early", "Mid", "Late").inOrder()
    }

    @Test
    fun `update changes fields`() = runTest {
        val id = repository.create(Reminder(title = "Old", triggerAtMillis = 1_000L, note = ""))
        repository.update(
            Reminder(id = id, title = "New", triggerAtMillis = 5_000L, note = "note", repeat = RepeatInterval.DAILY),
        )
        val loaded = repository.getById(id)!!
        assertThat(loaded.title).isEqualTo("New")
        assertThat(loaded.note).isEqualTo("note")
        assertThat(loaded.triggerAtMillis).isEqualTo(5_000L)
        assertThat(loaded.repeat).isEqualTo(RepeatInterval.DAILY)
    }

    @Test
    fun `delete removes item`() = runTest {
        val id = repository.create(Reminder(title = "Tmp", triggerAtMillis = 1_000L))
        repository.delete(id)
        assertThat(repository.getById(id)).isNull()
    }

    @Test
    fun `setEnabled toggles flag`() = runTest {
        val id = repository.create(Reminder(title = "T", triggerAtMillis = 1_000L, enabled = true))
        repository.setEnabled(id, false)
        assertThat(repository.getById(id)?.enabled).isFalse()
    }
}
