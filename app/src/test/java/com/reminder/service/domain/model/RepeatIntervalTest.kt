package com.reminder.service.domain.model

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RepeatIntervalTest {

    @Test
    fun `NONE returns null`() {
        assertThat(RepeatInterval.NONE.nextTrigger(1_000L)).isNull()
    }

    @Test
    fun `DAILY adds one day in millis`() {
        val now = 1_000L
        assertThat(RepeatInterval.DAILY.nextTrigger(now)).isEqualTo(now + RepeatInterval.MILLIS_PER_DAY)
        assertThat(RepeatInterval.MILLIS_PER_DAY).isEqualTo(86_400_000L)
    }

    @Test
    fun `WEEKLY adds seven days in millis`() {
        val now = 5_000L
        assertThat(RepeatInterval.WEEKLY.nextTrigger(now))
            .isEqualTo(now + 7L * RepeatInterval.MILLIS_PER_DAY)
    }
}
