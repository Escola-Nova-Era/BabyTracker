package com.escolanovaeratech.babytracker.insights.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.escolanovaeratech.babytracker.R
import com.escolanovaeratech.babytracker.data.local.BabyTrackerDatabase
import com.escolanovaeratech.babytracker.data.local.EventEntity
import com.escolanovaeratech.babytracker.data.local.EventDao
import com.escolanovaeratech.babytracker.insights.domain.GetWeeklyInsightsUseCase
import com.escolanovaeratech.babytracker.insights.ui.components.ChangingData
import com.escolanovaeratech.babytracker.insights.ui.components.FeedingData
import com.escolanovaeratech.babytracker.insights.ui.components.SleepData
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class InsightsUiState(
    val feedingDataList: List<FeedingData> = emptyList(),
    val sleepingDataList: List<SleepData> = emptyList(),
    val changingDataList: List<ChangingData> = emptyList(),
    val averageMl: Float = 0f,
    val averageHours: Float = 0f,
    val averageChanges: Float = 0f
)

/**
 * ViewModel responsável pelo estado da tela de Insights.
 * Delega o processamento e agregação de regras de negócio para o [GetWeeklyInsightsUseCase].
 */
class InsightsViewModel(
    private val eventDao: EventDao,
    private val getWeeklyInsightsUseCase: GetWeeklyInsightsUseCase = GetWeeklyInsightsUseCase(),
    private val context: Context? = null
) : ViewModel() {

    private val defaultWeekDays = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")

    val weekDays: List<String>
        get() = context?.let {
            listOf(
                it.getString(R.string.weekday_mon),
                it.getString(R.string.weekday_tue),
                it.getString(R.string.weekday_wed),
                it.getString(R.string.weekday_thu),
                it.getString(R.string.weekday_fri),
                it.getString(R.string.weekday_sat),
                it.getString(R.string.weekday_sun)
            )
        } ?: defaultWeekDays

    val uiState: StateFlow<InsightsUiState> = eventDao.observeAll()
        .map { events -> processEvents(events) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = processEvents(emptyList())
        )

    internal fun processEvents(events: List<EventEntity>): InsightsUiState {
        return getWeeklyInsightsUseCase(events, weekDays)
    }

    companion object {
        fun provideFactory(context: Context): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    val db = BabyTrackerDatabase.getInstance(context)
                    return InsightsViewModel(
                        eventDao = db.eventDao(),
                        context = context.applicationContext
                    ) as T
                }
            }
    }
}
