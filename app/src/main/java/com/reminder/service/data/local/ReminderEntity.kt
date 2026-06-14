package com.reminder.service.data.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.reminder.service.domain.model.Reminder
import com.reminder.service.domain.model.RepeatInterval

@Entity(
    tableName = "reminders",
    indices = [Index("trigger_at")],
)
data class ReminderEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val title: String,
    val note: String,
    @androidx.room.ColumnInfo(name = "trigger_at") val triggerAtMillis: Long,
    val enabled: Boolean,
    val repeat: RepeatInterval,
)

fun ReminderEntity.toDomain(): Reminder = Reminder(
    id = id,
    title = title,
    note = note,
    triggerAtMillis = triggerAtMillis,
    enabled = enabled,
    repeat = repeat,
)

fun Reminder.toEntity(): ReminderEntity = ReminderEntity(
    id = id,
    title = title,
    note = note,
    triggerAtMillis = triggerAtMillis,
    enabled = enabled,
    repeat = repeat,
)
