package com.reminder.service.domain.model

/** Интервал повтора напоминания. */
enum class RepeatInterval {
    NONE,
    DAILY,
    WEEKLY;

    companion object {
        /** Миллисекунды в одном дне. */
        const val MILLIS_PER_DAY = 24L * 60L * 60L * 1000L
    }
}

/**
 * Вычисляет следующее время срабатывания для повторяющегося напоминания,
 * отталкиваясь от [fromMillis] (обычно текущего времени).
 *
 * @return новое время срабатывания либо null, если напоминание разовое ([RepeatInterval.NONE]).
 */
fun RepeatInterval.nextTrigger(fromMillis: Long): Long? = when (this) {
    RepeatInterval.NONE -> null
    RepeatInterval.DAILY -> fromMillis + RepeatInterval.MILLIS_PER_DAY
    RepeatInterval.WEEKLY -> fromMillis + 7L * RepeatInterval.MILLIS_PER_DAY
}
