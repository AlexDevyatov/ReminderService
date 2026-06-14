package com.reminder.service.presentation.editor

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reminder.service.domain.model.Reminder
import com.reminder.service.domain.model.RepeatInterval
import com.reminder.service.domain.usecase.CreateReminderUseCase
import com.reminder.service.domain.usecase.GetReminderByIdUseCase
import com.reminder.service.domain.usecase.UpdateReminderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ReminderEditorUiState(
    val id: Long = 0L,
    val title: String = "",
    val note: String = "",
    val hour: Int = 9,
    val minute: Int = 0,
    val repeat: RepeatInterval = RepeatInterval.NONE,
    val isExisting: Boolean = false,
    val isSaved: Boolean = false,
    val isLoaded: Boolean = false,
)

@HiltViewModel
class ReminderEditorViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getUseCase: GetReminderByIdUseCase,
    private val createUseCase: CreateReminderUseCase,
    private val updateUseCase: UpdateReminderUseCase,
) : ViewModel() {

    private val reminderIdArg: Long = savedStateHandle.get<Long>(ARG_REMINDER_ID) ?: 0L

    private val _uiState = MutableStateFlow(ReminderEditorUiState())
    val uiState: StateFlow<ReminderEditorUiState> = _uiState.asStateFlow()

    init {
        if (reminderIdArg > 0L) loadReminder(reminderIdArg)
        else _uiState.update { it.copy(isLoaded = true) }
    }

    private fun loadReminder(id: Long) = viewModelScope.launch {
        getUseCase(id)?.let { r ->
            _uiState.update {
                it.copy(
                    id = r.id,
                    title = r.title,
                    note = r.note,
                    hour = millisToHour(r.triggerAtMillis),
                    minute = millisToMinute(r.triggerAtMillis),
                    repeat = r.repeat,
                    isExisting = true,
                    isLoaded = true,
                )
            }
        } ?: _uiState.update { it.copy(isLoaded = true) }
    }

    fun onTitleChange(v: String) = _uiState.update { it.copy(title = v) }
    fun onNoteChange(v: String) = _uiState.update { it.copy(note = v) }
    fun onTimeChange(hour: Int, minute: Int) =
        _uiState.update { it.copy(hour = hour, minute = minute) }
    fun onRepeatChange(r: RepeatInterval) = _uiState.update { it.copy(repeat = r) }

    fun save() = viewModelScope.launch {
        val state = _uiState.value
        if (state.title.isBlank()) return@launch
        val now = System.currentTimeMillis()
        var triggerAt = combineDateTime(state.hour, state.minute)
        // Если выбранное время уже сегодня прошло и повтора нет — ставим на завтра.
        if (triggerAt <= now && state.repeat == RepeatInterval.NONE) {
            triggerAt += RepeatInterval.MILLIS_PER_DAY
        }
        val reminder = Reminder(
            id = state.id,
            title = state.title.trim(),
            note = state.note.trim(),
            triggerAtMillis = triggerAt,
            enabled = true,
            repeat = state.repeat,
        )
        if (state.isExisting) updateUseCase(reminder) else createUseCase(reminder)
        _uiState.update { it.copy(isSaved = true) }
    }

    companion object {
        const val ARG_REMINDER_ID = "reminderId"

        private fun combineDateTime(hour: Int, minute: Int): Long {
            val cal = java.util.Calendar.getInstance().apply {
                set(java.util.Calendar.HOUR_OF_DAY, hour)
                set(java.util.Calendar.MINUTE, minute)
                set(java.util.Calendar.SECOND, 0)
                set(java.util.Calendar.MILLISECOND, 0)
            }
            return cal.timeInMillis
        }

        private fun millisToHour(millis: Long): Int =
            java.util.Calendar.getInstance().apply { timeInMillis = millis }
                .get(java.util.Calendar.HOUR_OF_DAY)

        private fun millisToMinute(millis: Long): Int =
            java.util.Calendar.getInstance().apply { timeInMillis = millis }
                .get(java.util.Calendar.MINUTE)
    }
}
