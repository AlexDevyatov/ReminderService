package com.reminder.service.util

/**
 * Безопасно преобразует Long ID в Int request code для PendingIntent и notification ID.
 * Использует модульную арифметику, чтобы избежать переполнения.
 * Оставляет запас до [MAX_OFFSET] для action-specific request codes (snooze, done).
 */
fun Long.toRequestCode(): Int {
    val max = Int.MAX_VALUE.toLong() - MAX_OFFSET
    return (this % max).toInt()
}

private const val MAX_OFFSET = 30_000L
