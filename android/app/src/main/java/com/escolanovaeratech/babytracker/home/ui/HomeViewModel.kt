package com.escolanovaeratech.babytracker.home.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.escolanovaeratech.babytracker.R
import com.escolanovaeratech.babytracker.data.local.BabyTrackerDatabase
import com.escolanovaeratech.babytracker.data.local.EventEntity
import com.escolanovaeratech.babytracker.data.local.EventType
import com.escolanovaeratech.babytracker.data.repository.EventRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.Calendar

sealed interface HomeUiEvent {
    data class ShowSnackbar(val messageResId: Int) : HomeUiEvent
}

/**
 * ViewModel responsável pelo gerenciamento de estado e ações da HomeScreen,
 * incluindo o registro de eventos de ações rápidas no banco de dados local.
 */
class HomeViewModel(
    private val repository: EventRepository
) : ViewModel() {

    private val _uiEvent = Channel<HomeUiEvent>()
    val uiEvent: Flow<HomeUiEvent> = _uiEvent.receiveAsFlow()

    fun saveFeeding(hour: Int, minute: Int, amountMl: String, notes: String) {
        viewModelScope.launch {
            val timestamp = getTimestampForToday(hour, minute)
            val parsedAmount = amountMl.toIntOrNull()
            val event = EventEntity(
                type = EventType.FEEDING,
                title = "Bottle Feeding",
                timestamp = timestamp,
                amountMl = parsedAmount,
                notes = notes.trim().ifEmpty { null }
            )
            repository.insertEvent(event)
            _uiEvent.send(HomeUiEvent.ShowSnackbar(R.string.event_saved_success))
        }
    }

    fun saveDiaper(diaperType: String, hour: Int, minute: Int, notes: String) {
        viewModelScope.launch {
            val timestamp = getTimestampForToday(hour, minute)
            val cleanNotes = notes.trim()
            val finalNotes = if (cleanNotes.isNotEmpty()) {
                "[$diaperType] $cleanNotes"
            } else {
                "[$diaperType]"
            }

            val event = EventEntity(
                type = EventType.DIAPER,
                title = "Diaper Change",
                timestamp = timestamp,
                notes = finalNotes
            )
            repository.insertEvent(event)
            _uiEvent.send(HomeUiEvent.ShowSnackbar(R.string.event_saved_success))
        }
    }

    fun saveSleep(
        sleepStatus: String,
        startHour: Int,
        startMinute: Int,
        endHour: Int?,
        endMinute: Int?,
        notes: String
    ) {
        viewModelScope.launch {
            val startTimestamp = getTimestampForToday(startHour, startMinute)
            val durationMinutes = calculateDurationMinutes(startHour, startMinute, endHour, endMinute)
            val title = if (sleepStatus.equals("Asleep", ignoreCase = true)) "Fell Asleep" else "Woke Up"
            val event = EventEntity(
                type = EventType.SLEEP,
                title = title,
                timestamp = startTimestamp,
                durationMinutes = durationMinutes,
                notes = notes.trim().ifEmpty { null }
            )
            repository.insertEvent(event)
            _uiEvent.send(HomeUiEvent.ShowSnackbar(R.string.event_saved_success))
        }
    }

    fun saveBath(
        hour: Int,
        minute: Int,
        durationMinutes: Int?,
        waterTemperature: String?,
        notes: String
    ) {
        viewModelScope.launch {
            val timestamp = getTimestampForToday(hour, minute)
            val cleanNotes = notes.trim()
            val tempPart = waterTemperature?.let { "Temp: $it" }
            val combinedNotes = when {
                tempPart != null && cleanNotes.isNotEmpty() -> "$tempPart • $cleanNotes"
                tempPart != null -> tempPart
                cleanNotes.isNotEmpty() -> cleanNotes
                else -> null
            }

            val event = EventEntity(
                type = EventType.BATH,
                title = "Bath Time",
                timestamp = timestamp,
                durationMinutes = durationMinutes,
                notes = combinedNotes
            )
            repository.insertEvent(event)
            _uiEvent.send(HomeUiEvent.ShowSnackbar(R.string.event_saved_success))
        }
    }

    private fun getTimestampForToday(hour: Int, minute: Int): Long {
        return Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis
    }

    private fun calculateDurationMinutes(
        startHour: Int,
        startMinute: Int,
        endHour: Int?,
        endMinute: Int?
    ): Int? {
        if (endHour == null || endMinute == null) return null
        val startTotalMinutes = startHour * 60 + startMinute
        var endTotalMinutes = endHour * 60 + endMinute
        if (endTotalMinutes < startTotalMinutes) {
            endTotalMinutes += 24 * 60
        }
        return (endTotalMinutes - startTotalMinutes).coerceAtLeast(0)
    }

    companion object {
        fun provideFactory(context: Context): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    val db = BabyTrackerDatabase.getInstance(context)
                    val repository = EventRepository(db.eventDao())
                    return HomeViewModel(repository) as T
                }
            }
    }
}
