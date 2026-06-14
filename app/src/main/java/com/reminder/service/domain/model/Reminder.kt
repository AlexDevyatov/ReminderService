package com.reminder.service.domain.model

/**
 * Доменная модель напоминания.
 *
 * @param id           идентификатор (0 — для нового, ещё не сохранённого).
 * @param title        заголовок напоминания.
 * @param note         текст заметки (может быть пустым).
 * @param triggerAtMillis время срабатывания в epoch millis (локальное время устройства).
 * @param enabled      включено ли напоминание.
 * @param repeat       интервал повтора.
 */
data class Reminder(
    val id: Long = 0L,
    val title: String,
    val note: String = "",
    val triggerAtMillis: Long,
    val enabled: Boolean = true,
    val repeat: RepeatInterval = RepeatInterval.NONE,
)
