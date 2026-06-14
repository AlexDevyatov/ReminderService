package com.reminder.service.data.local

import androidx.room.TypeConverter
import com.reminder.service.domain.model.RepeatInterval

class RepeatIntervalConverter {
    @TypeConverter
    fun fromRepeatInterval(value: RepeatInterval): String = value.name

    @TypeConverter
    fun toRepeatInterval(value: String): RepeatInterval =
        runCatching { RepeatInterval.valueOf(value) }.getOrDefault(RepeatInterval.NONE)
}
