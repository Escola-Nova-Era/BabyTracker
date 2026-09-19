package com.escolanovaeratech.babytracker.insights.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.escolanovaeratech.babytracker.data.local.BabyTrackerDatabase
import com.escolanovaeratech.babytracker.data.local.EventEntity
import com.escolanovaeratech.babytracker.data.local.EventType
import com.escolanovaeratech.babytracker.data.repository.EventRepository
import com.escolanovaeratech.babytracker.insights.ui.components.ChangingData
import com.escolanovaeratech.babytracker.insights.ui.components.FeedingData
import com.escolanovaeratech.babytracker.insights.ui.components.SleepData
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.util.Calendar

data class InsightsUiState(
    val feedingDataList: List<FeedingData> = emptyList(),
    val sleepingDataList: List<SleepData> = emptyList(),
    val changingDataList: List<ChangingData> = emptyList(),
    val averageMl: Float = 0f,
    val averageHours: Float = 0f,
    val averageChanges: Float = 0f
)

/**
 * ViewModel responsável pela agregação e processamento de métricas semanais
 * da tela de Insights a partir dos eventos registrados no banco de dados Room.
 */
class InsightsViewModel(
    private val repository: EventRepository
) : ViewModel() {

    private val weekDays = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")

    val uiState: StateFlow<InsightsUiState> = repository.observeAllEvents()
        .map { events -> processEvents(events) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = processEvents(emptyList())
        )

    internal fun processEvents(events: List<EventEntity>): InsightsUiState {
        val feedingMap = weekDays.associateWith { 0f }.toMutableMap()
        val sleepMinutesMap = weekDays.associateWith { 0 }.toMutableMap()
        val diaperMap = weekDays.associateWith { 0 }.toMutableMap()

        for (event in events) {
            val day = getDayOfWeekAbbreviation(event.timestamp)
            when (event.type) {
                EventType.FEEDING -> {
                    val amount = event.amountMl ?: 0
                    feedingMap[day] = (feedingMap[day] ?: 0f) + amount.toFloat()
                }
                EventType.SLEEP -> {
                    val duration = event.durationMinutes ?: 0
                    sleepMinutesMap[day] = (sleepMinutesMap[day] ?: 0) + duration
                }
                EventType.DIAPER -> {
                    diaperMap[day] = (diaperMap[day] ?: 0) + 1
                }
                else -> { /* Outros tipos não entram nos 3 gráficos principais */ }
            }
        }

        val feedingList = weekDays.map { day ->
            FeedingData(dayOfWeek = day, amountMl = feedingMap[day] ?: 0f)
        }

        val sleepingList = weekDays.map { day ->
            val minutes = sleepMinutesMap[day] ?: 0
            val hours = minutes / 60f
            SleepData(dayOfWeek = day, hours = hours)
        }

        val changingList = weekDays.map { day ->
            ChangingData(dayOfWeek = day, count = diaperMap[day] ?: 0)
        }

        val activeFeeding = feedingList.map { it.amountMl }.filter { it > 0f }
        val avgMl = if (activeFeeding.isNotEmpty()) activeFeeding.average().toFloat() else 0f

        val activeSleep = sleepingList.map { it.hours }.filter { it > 0f }
        val avgHours = if (activeSleep.isNotEmpty()) activeSleep.average().toFloat() else 0f

        val activeDiapers = changingList.map { it.count }.filter { it > 0 }
        val avgChanges = if (activeDiapers.isNotEmpty()) activeDiapers.average().toFloat() else 0f

        return InsightsUiState(
            feedingDataList = feedingList,
            sleepingDataList = sleepingList,
            changingDataList = changingList,
            averageMl = avgMl,
            averageHours = avgHours,
            averageChanges = avgChanges
        )
    }

    private fun getDayOfWeekAbbreviation(timestamp: Long): String {
        val calendar = Calendar.getInstance().apply { timeInMillis = timestamp }
        return when (calendar.get(Calendar.DAY_OF_WEEK)) {
            Calendar.MONDAY -> "Mon"
            Calendar.TUESDAY -> "Tue"
            Calendar.WEDNESDAY -> "Wed"
            Calendar.THURSDAY -> "Thu"
            Calendar.FRIDAY -> "Fri"
            Calendar.SATURDAY -> "Sat"
            Calendar.SUNDAY -> "Sun"
            else -> "Mon"
        }
    }

    companion object {
        fun provideFactory(context: Context): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    val db = BabyTrackerDatabase.getInstance(context)
                    val repository = EventRepository(db.eventDao())
                    return InsightsViewModel(repository) as T
                }
            }
    }
}
