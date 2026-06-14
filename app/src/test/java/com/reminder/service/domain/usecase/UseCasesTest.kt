package com.reminder.service.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.reminder.service.domain.model.Reminder
import com.reminder.service.domain.model.RepeatInterval
import com.reminder.service.domain.repository.ReminderRepository
import com.reminder.service.domain.repository.ReminderScheduler
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CreateReminderUseCaseTest {

    private val repository = mockk<ReminderRepository>(relaxed = true)
    private val scheduler = mockk<ReminderScheduler>(relaxed = true)
    private val useCase = CreateReminderUseCase(repository, scheduler)

    @Test
    fun `creates reminder in repository and returns id`() = runTest {
        val reminder = Reminder(title = "Test", triggerAtMillis = 1_000L)
        coEvery { repository.create(reminder) } returns 42L

        val id = useCase(reminder)

        assertThat(id).isEqualTo(42L)
        coVerify { repository.create(reminder) }
    }

    @Test
    fun `schedules alarm when reminder enabled`() = runTest {
        val reminder = Reminder(title = "Test", triggerAtMillis = 1_000L, enabled = true)
        coEvery { repository.create(any()) } returns 1L

        useCase(reminder)

        coVerify { scheduler.schedule(reminder.copy(id = 1L)) }
    }

    @Test
    fun `does not schedule alarm when reminder disabled`() = runTest {
        val reminder = Reminder(title = "Test", triggerAtMillis = 1_000L, enabled = false)
        coEvery { repository.create(any()) } returns 1L

        useCase(reminder)

        coVerify(exactly = 0) { scheduler.schedule(any()) }
    }

    @Test(expected = IllegalArgumentException::class)
    fun `throws when title blank`() = runTest {
        useCase(Reminder(title = "   ", triggerAtMillis = 1_000L))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `throws when trigger time not positive`() = runTest {
        useCase(Reminder(title = "Test", triggerAtMillis = 0L))
    }
}

class UpdateReminderUseCaseTest {

    private val repository = mockk<ReminderRepository>(relaxed = true)
    private val scheduler = mockk<ReminderScheduler>(relaxed = true)
    private val useCase = UpdateReminderUseCase(repository, scheduler)

    @Test
    fun `cancels old alarm then updates then schedules new when enabled`() = runTest {
        val reminder = Reminder(id = 5L, title = "X", triggerAtMillis = 2_000L, enabled = true)

        useCase(reminder)

        coVerifyOrder {
            scheduler.cancel(5L)
            repository.update(reminder)
            scheduler.schedule(reminder)
        }
    }

    @Test
    fun `does not schedule when disabled`() = runTest {
        val reminder = Reminder(id = 5L, title = "X", triggerAtMillis = 2_000L, enabled = false)

        useCase(reminder)

        coVerify { scheduler.cancel(5L) }
        coVerify(exactly = 0) { scheduler.schedule(any()) }
    }

    @Test(expected = IllegalArgumentException::class)
    fun `throws when id is zero`() = runTest {
        useCase(Reminder(id = 0L, title = "X", triggerAtMillis = 2_000L))
    }
}

class DeleteReminderUseCaseTest {

    private val repository = mockk<ReminderRepository>(relaxed = true)
    private val scheduler = mockk<ReminderScheduler>(relaxed = true)
    private val useCase = DeleteReminderUseCase(repository, scheduler)

    @Test
    fun `cancels alarm and deletes from repository`() = runTest {
        useCase(7L)

        coVerifyOrder {
            scheduler.cancel(7L)
            repository.delete(7L)
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun `throws when id not positive`() = runTest {
        useCase(0L)
    }
}

class ToggleReminderUseCaseTest {

    private val repository = mockk<ReminderRepository>(relaxed = true)
    private val scheduler = mockk<ReminderScheduler>(relaxed = true)
    private val useCase = ToggleReminderUseCase(repository, scheduler)

    @Test
    fun `enabling sets enabled true and schedules`() = runTest {
        coEvery { repository.getById(3L) } returns Reminder(id = 3L, title = "T", triggerAtMillis = 1_000L)

        useCase(3L, enabled = true)

        coVerify { repository.setEnabled(3L, true) }
        coVerify { scheduler.schedule(any()) }
    }

    @Test
    fun `disabling cancels alarm`() = runTest {
        useCase(3L, enabled = false)

        coVerify { repository.setEnabled(3L, false) }
        coVerify { scheduler.cancel(3L) }
        coVerify(exactly = 0) { scheduler.schedule(any()) }
    }

    @Test
    fun `toggle flips current enabled state`() = runTest {
        coEvery { repository.getById(3L) } returns Reminder(id = 3L, title = "T", triggerAtMillis = 1_000L, enabled = true)

        useCase.toggle(3L)

        coVerify { repository.setEnabled(3L, false) }
        coVerify { scheduler.cancel(3L) }
    }
}

class SnoozeReminderUseCaseTest {

    private val repository = mockk<ReminderRepository>(relaxed = true)
    private val scheduler = mockk<ReminderScheduler>(relaxed = true)
    private val useCase = SnoozeReminderUseCase(repository, scheduler)

    @Test
    fun `updates trigger time to now plus minutes and reschedules`() = runTest {
        val reminder = Reminder(id = 9L, title = "T", triggerAtMillis = 100L)
        val slot = slot<Reminder>()

        val result = useCase(reminder, minutes = 5L, now = 1_000L)

        coVerify { repository.update(capture(slot)) }
        assertThat(slot.captured.triggerAtMillis).isEqualTo(1_000L + 5 * 60 * 1000L)
        coVerifyOrder { scheduler.cancel(9L); scheduler.schedule(any()) }
        assertThat(result.triggerAtMillis).isEqualTo(1_000L + 5 * 60 * 1000L)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `throws when minutes not positive`() = runTest {
        useCase(Reminder(id = 9L, title = "T", triggerAtMillis = 1L), minutes = 0L)
    }
}

class ObserveRemindersUseCaseTest {

    @Test
    fun `delegates to repository observeAll`() = runTest {
        val data = listOf(Reminder(title = "A", triggerAtMillis = 1L))
        val repository = mockk<ReminderRepository> {
            every { observeAll() } returns flowOf(data)
        }
        val useCase = ObserveRemindersUseCase(repository)

        val result = useCase().first()

        assertThat(result).isEqualTo(data)
    }
}
