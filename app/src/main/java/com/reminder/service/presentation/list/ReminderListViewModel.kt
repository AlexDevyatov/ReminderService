package com.reminder.service.presentation.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reminder.service.domain.model.Reminder
import com.reminder.service.domain.usecase.DeleteReminderUseCase
import com.reminder.service.domain.usecase.ObserveRemindersUseCase
import com.reminder.service.domain.usecase.ToggleReminderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class ReminderListUiState(
    val reminders: List<Reminder> = emptyList(),
    val isLoading: Boolean = true,
    val hasError: Boolean = false,
)

@HiltViewModel
class ReminderListViewModel @Inject constructor(
    observeUseCase: ObserveRemindersUseCase,
    private val toggleUseCase: ToggleReminderUseCase,
    private val deleteUseCase: DeleteReminderUseCase,
) : ViewModel() {

    private val _hasError = MutableStateFlow(false)

    val uiState: StateFlow<ReminderListUiState> = combine(
        observeUseCase(),
        _hasError,
    ) { reminders, hasError ->
        ReminderListUiState(
            reminders = reminders,
            isLoading = false,
            hasError = hasError,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ReminderListUiState(),
    )

    fun toggle(id: Long, enabled: Boolean) = viewModelScope.launch {
        try {
            toggleUseCase(id, enabled)
        } catch (e: Exception) {
            Log.e(TAG, "Failed to toggle reminder $id", e)
            _hasError.value = true
        }
    }

    fun delete(id: Long) = viewModelScope.launch {
        try {
            deleteUseCase(id)
        } catch (e: Exception) {
            Log.e(TAG, "Failed to delete reminder $id", e)
            _hasError.value = true
        }
    }

    fun clearError() {
        _hasError.value = false
    }

    companion object {
        private const val TAG = "ReminderListViewModel"
    }
}
