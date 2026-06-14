package com.reminder.service.scheduling

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.reminder.service.di.ReceiverEntryPoint
import com.reminder.service.domain.model.nextTrigger
import com.reminder.service.domain.model.RepeatInterval
import com.reminder.service.domain.usecase.SnoozeReminderUseCase
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

/**
 * Принимает будильники и action'ы уведомлений:
 *  - [ACTION_FIRE] — время напоминания наступило: показать уведомление и
 *    при повторе перепланировать следующий будильник.
 *  - [ACTION_SNOOZE] — отложить на [SnoozeReminderUseCase.DEFAULT_SNOOZE_MINUTES].
 *  - [ACTION_DONE] — пометить как сработавшее (для разовых — отмена будильника).
 */
class ReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action !in HANDLED_ACTIONS) return
        val id = intent.getLongExtra(EXTRA_REMINDER_ID, -1L)
        if (id <= 0L) return

        val entry = EntryPointAccessors.fromApplication(context, ReceiverEntryPoint::class.java)
        val pendingResult = goAsync()
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

        scope.launch {
            try {
                when (intent.action) {
                    ACTION_FIRE -> handleFire(entry, id)
                    ACTION_SNOOZE -> handleSnooze(entry, id)
                    ACTION_DONE -> handleDone(entry, id)
                }
            } finally {
                pendingResult.finish()
                scope.cancel()
            }
        }
    }

    private suspend fun handleFire(entry: ReceiverEntryPoint, id: Long) {
        val reminder = entry.getByIdUseCase()(id) ?: return
        entry.notificationHelper().showReminder(id, reminder.title, reminder.note)

        // Планируем следующий повтор, если задан интервал повтора.
        // Продвигаем время до будущего, чтобы избежать шторма уведомлений
        // при задержке доставки (Doze, выключенное устройство).
        var nextTime = reminder.repeat.nextTrigger(reminder.triggerAtMillis)
        val now = System.currentTimeMillis()
        while (nextTime != null && nextTime <= now) {
            nextTime = reminder.repeat.nextTrigger(nextTime)
        }
        if (nextTime != null) {
            val updated = reminder.copy(triggerAtMillis = nextTime)
            // UseCase обновляет БД и перепланирует будильник.
            entry.updateUseCase()(updated)
        }
    }

    private suspend fun handleSnooze(entry: ReceiverEntryPoint, id: Long) {
        val reminder = entry.getByIdUseCase()(id) ?: return
        entry.notificationHelper().cancel(id)
        entry.snoozeUseCase()(reminder)
    }

    private suspend fun handleDone(entry: ReceiverEntryPoint, id: Long) {
        entry.notificationHelper().cancel(id)
        // Для разовых — удаляем будильник. Повторяющие остаются в БД.
        val reminder = entry.getByIdUseCase()(id)
        if (reminder != null && reminder.repeat == RepeatInterval.NONE) {
            entry.scheduler().cancel(id)
        }
    }

    companion object {
        const val ACTION_FIRE = "com.reminder.service.action.FIRE"
        const val ACTION_SNOOZE = "com.reminder.service.action.SNOOZE"
        const val ACTION_DONE = "com.reminder.service.action.DONE"
        const val EXTRA_REMINDER_ID = "extra_reminder_id"

        private val HANDLED_ACTIONS = setOf(ACTION_FIRE, ACTION_SNOOZE, ACTION_DONE)
    }
}
