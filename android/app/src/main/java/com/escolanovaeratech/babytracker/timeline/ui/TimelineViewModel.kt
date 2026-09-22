package com.escolanovaeratech.babytracker.timeline.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.escolanovaeratech.babytracker.R
import com.escolanovaeratech.babytracker.data.local.BabyTrackerDatabase
import com.escolanovaeratech.babytracker.data.local.EventDao
import com.escolanovaeratech.babytracker.data.local.EventEntity
import com.escolanovaeratech.babytracker.data.local.EventType
import com.escolanovaeratech.babytracker.timeline.data.TimelineItem
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

sealed interface TimelineUiState {
    data object Loading : TimelineUiState
    data object Empty : TimelineUiState
    data class Success(val items: List<TimelineItem>) : TimelineUiState
}

/**
 * ViewModel que observa eventos persistidos no banco de dados Room e os converte
 * dinamicamente em itens da Timeline com estilização e metadados.
 */
class TimelineViewModel(
    private val eventDao: EventDao,
    private val context: Context
) : ViewModel() {

    val uiState: StateFlow<TimelineUiState> = eventDao.observeAll()
        .map { entities ->
            if (entities.isEmpty()) {
                TimelineUiState.Empty
            } else {
                TimelineUiState.Success(entities.map { it.toTimelineItem() })
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = TimelineUiState.Loading
        )

    private fun EventEntity.toTimelineItem(): TimelineItem {
        val timeFormatted = SimpleDateFormat("h:mm a", Locale.getDefault()).format(Date(timestamp))

        return when (type) {
            EventType.FEEDING -> {
                TimelineItem(
                    title = title.ifBlank { context.getString(R.string.bottle_feeding) },
                    time = timeFormatted,
                    icon = "\uD83C\uDF7C", // 🍼
                    bubbleColor = R.color.feed_bubble,
                    dotColor = R.color.feed_dot,
                    metaPrimary = amountMl?.let { "\u2195 $it ml" },
                    metaSecondary = durationMinutes?.let { "\u25F4 $it min" },
                    subtitle = notes?.ifBlank { null }
                )
            }
            EventType.DIAPER -> {
                val tag = when {
                    title.contains("Pee", ignoreCase = true) || notes?.contains("[Pee]") == true -> "Pee"
                    title.contains("Poop", ignoreCase = true) || notes?.contains("[Poop]") == true -> "Poop"
                    title.contains("Mixed", ignoreCase = true) || notes?.contains("[Mixed]") == true -> "Mixed"
                    else -> null
                }
                val (tagBg, tagText) = when (tag) {
                    "Pee" -> Pair(R.color.tag_blue_bg, R.color.tag_blue_text)
                    "Poop" -> Pair(R.color.tag_brown_bg, R.color.tag_brown_text)
                    "Mixed" -> Pair(R.color.tag_violet_bg, R.color.tag_violet_text)
                    else -> Pair(R.color.tag_blue_bg, R.color.tag_blue_text)
                }
                val cleanSubtitle = notes
                    ?.replace("[Pee]", "")
                    ?.replace("[Poop]", "")
                    ?.replace("[Mixed]", "")
                    ?.trim()
                    ?.ifEmpty { null }

                val displayTitle = if (title in listOf("Pee", "Poop", "Mixed")) {
                    context.getString(R.string.diaper_change)
                } else title.ifBlank { context.getString(R.string.diaper_change) }

                TimelineItem(
                    title = displayTitle,
                    time = timeFormatted,
                    icon = "\uD83D\uDC76", // 👶
                    bubbleColor = R.color.diaper_bubble,
                    dotColor = R.color.diaper_dot,
                    tag = tag ?: title.takeIf { it in listOf("Pee", "Poop", "Mixed") },
                    tagBackgroundColor = tagBg,
                    tagTextColor = tagText,
                    subtitle = cleanSubtitle
                )
            }
            EventType.SLEEP -> {
                val isAsleep = title.contains("Asleep", ignoreCase = true)
                val icon = if (isAsleep) "\u263E" else "\u2600" // ☾ or ☀
                val subtitleText = when {
                    durationMinutes != null && durationMinutes > 0 -> {
                        val hours = durationMinutes / 60
                        val mins = durationMinutes % 60
                        val durationFormatted = if (hours > 0) "${hours}h ${mins}min" else "${mins}min"
                        context.getString(R.string.slept_for, durationFormatted)
                    }
                    else -> notes?.ifBlank { null }
                }

                TimelineItem(
                    title = title.ifBlank { context.getString(R.string.sleep) },
                    time = timeFormatted,
                    icon = icon,
                    bubbleColor = R.color.sleep_bubble,
                    dotColor = R.color.sleep_dot,
                    subtitle = subtitleText,
                    metaPrimary = if (durationMinutes != null && !notes.isNullOrBlank()) notes else null
                )
            }
            EventType.BATH -> {
                TimelineItem(
                    title = title.ifBlank { context.getString(R.string.bath_time) },
                    time = timeFormatted,
                    icon = "\uD83D\uDEC1", // 🛁
                    bubbleColor = R.color.bath_bubble,
                    dotColor = R.color.bath_dot,
                    metaPrimary = durationMinutes?.let { "\u25F4 $it min" },
                    subtitle = notes?.ifBlank { null }
                )
            }
            EventType.OTHER -> {
                TimelineItem(
                    title = title.ifBlank { context.getString(R.string.activity) },
                    time = timeFormatted,
                    icon = "✨",
                    bubbleColor = R.color.feed_bubble,
                    dotColor = R.color.feed_dot,
                    subtitle = notes?.ifBlank { null }
                )
            }
        }
    }

    companion object {
        fun provideFactory(context: Context): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    val db = BabyTrackerDatabase.getInstance(context)
                    return TimelineViewModel(
                        eventDao = db.eventDao(),
                        context = context.applicationContext
                    ) as T
                }
            }
    }
}
