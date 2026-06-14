package com.reminder.service.scheduling

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.reminder.service.di.ReceiverEntryPoint
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * Перепланирует все включённые напоминания после:
 *  - BOOT_COMPLETED / MY_PACKAGE_REPLACED — будильники сбрасываются;
 *  - TIME_SET / TIMEZONE_CHANGED — пользователь сменил время/часовой пояс.
 */
class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action ?: return
        if (action !in HANDLED_ACTIONS) return

        val entry = EntryPointAccessors.fromApplication(context, ReceiverEntryPoint::class.java)
        val pendingResult = goAsync()
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

        scope.launch {
            try {
                val reminders = entry.observeUseCase().invoke().first()
                reminders.filter { it.enabled }.forEach { entry.scheduler().schedule(it) }
            } finally {
                pendingResult.finish()
                scope.cancel()
            }
        }
    }

    companion object {
        private val HANDLED_ACTIONS = setOf(
            Intent.ACTION_BOOT_COMPLETED,
            Intent.ACTION_MY_PACKAGE_REPLACED,
            Intent.ACTION_TIME_CHANGED,
            Intent.ACTION_TIMEZONE_CHANGED,
        )
    }
}
