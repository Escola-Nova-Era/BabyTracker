package com.escolanovaeratech.babytracker.home.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.escolanovaeratech.babytracker.R
import com.escolanovaeratech.babytracker.data.local.BabyTrackerDatabase
import com.escolanovaeratech.babytracker.data.local.EventDao
import com.escolanovaeratech.babytracker.data.local.EventEntity
import com.escolanovaeratech.babytracker.data.local.EventType
import com.escolanovaeratech.babytracker.util.calculateDurationMinutes
import com.escolanovaeratech.babytracker.util.getTimestampForToday
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

sealed interface HomeUiEvent {
    data class ShowSnackbar(val messageResId: Int) : HomeUiEvent
}
// --- EVENTOS DE ENTRADA DA UI ---
sealed class BabyTrackerEvent {
    data class SaveFeeding(
        val hour: Int,
        val minute: Int,
        val amountMl: String,
        val notes: String
    ) : BabyTrackerEvent()
    data class SaveDiaper(
        val diaperType: String,
        val hour: Int,
        val minute: Int,
        val notes: String
    ) : BabyTrackerEvent()
    data class SaveSleep(
        val sleepStatus: String,
        val startHour: Int,
        val startMinute: Int,
        val endHour: Int?,
        val endMinute: Int?,
        val notes: String
    ) : BabyTrackerEvent()
    data class SaveBath(
        val hour: Int,
        val minute: Int,
        val durationMinutes: Int?,
        val waterTemperature: String?,
        val notes: String
    ) : BabyTrackerEvent()
}

/**
 * ViewModel responsável pelo gerenciamento de ações da HomeScreen,
 * persistindo diretamente no EventDao do Room.
 */
class HomeViewModel(
    private val eventDao: EventDao,
    private val context: Context
) : ViewModel() {

    fun onEvent(event: BabyTrackerEvent) {
        when (event) {
            is BabyTrackerEvent.SaveFeeding -> saveFeeding(event.hour, event.minute, event.amountMl, event.notes)
            is BabyTrackerEvent.SaveDiaper -> saveDiaper(event.diaperType, event.hour, event.minute, event.notes)
            is BabyTrackerEvent.SaveSleep -> saveSleep(event.sleepStatus, event.startHour, event.startMinute, event.endHour, event.endMinute, event.notes)
            is BabyTrackerEvent.SaveBath -> saveBath(event.hour, event.minute, event.durationMinutes, event.waterTemperature, event.notes)
        }
    }

    val uiEvent: SharedFlow<HomeUiEvent>
        field = MutableSharedFlow()

    private fun saveFeeding(hour: Int, minute: Int, amountMl: String, notes: String) {
        viewModelScope.launch {
            val timestamp = getTimestampForToday(hour, minute)
            val parsedAmount = amountMl.toIntOrNull()
            val event = EventEntity(
                type = EventType.FEEDING,
                title = context.getString(R.string.bottle_feeding),
                timestamp = timestamp,
                amountMl = parsedAmount,
                notes = notes.trim()
            )
            eventDao.insert(event)
            uiEvent.emit(HomeUiEvent.ShowSnackbar(R.string.event_saved_success))
        }
    }

    private fun saveDiaper(diaperType: String, hour: Int, minute: Int, notes: String) {
        viewModelScope.launch {
            val timestamp = getTimestampForToday(hour, minute)
            val cleanNotes = notes.trim()
            val event = EventEntity(
                type = EventType.DIAPER,
                title = diaperType,
                timestamp = timestamp,
                notes = cleanNotes
            )
            eventDao.insert(event)
            uiEvent.emit(HomeUiEvent.ShowSnackbar(R.string.event_saved_success))
        }
    }

    private fun saveSleep(
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
            val isAsleep = sleepStatus.equals("Asleep", ignoreCase = true)
            val title = if (isAsleep) {
                context.getString(R.string.fell_asleep)
            } else {
                context.getString(R.string.woke_up)
            }

            val event = EventEntity(
                type = EventType.SLEEP,
                title = title,
                timestamp = startTimestamp,
                durationMinutes = durationMinutes,
                notes = notes.trim()
            )
            eventDao.insert(event)
            uiEvent.emit(HomeUiEvent.ShowSnackbar(R.string.event_saved_success))
        }
    }

    private fun saveBath(
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
                else -> cleanNotes
            }

            val event = EventEntity(
                type = EventType.BATH,
                title = context.getString(R.string.bath_time),
                timestamp = timestamp,
                durationMinutes = durationMinutes,
                notes = combinedNotes
            )
            eventDao.insert(event)
            uiEvent.emit(HomeUiEvent.ShowSnackbar(R.string.event_saved_success))
        }
    }

    companion object {
        fun provideFactory(context: Context): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    val db = BabyTrackerDatabase.getInstance(context)
                    return HomeViewModel(
                        eventDao = db.eventDao(),
                        context = context.applicationContext
                    ) as T
                }
            }
    }
}