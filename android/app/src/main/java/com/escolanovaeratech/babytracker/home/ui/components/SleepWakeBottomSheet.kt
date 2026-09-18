package com.escolanovaeratech.babytracker.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.escolanovaeratech.babytracker.R
import com.escolanovaeratech.babytracker.theme.BabyTrackerTheme
import com.escolanovaeratech.babytracker.theme.BottomSheetShape
import com.escolanovaeratech.babytracker.theme.PrimaryColor
import com.escolanovaeratech.babytracker.theme.PrimarySoft
import com.escolanovaeratech.babytracker.theme.Spacing
import com.escolanovaeratech.babytracker.theme.SurfaceColor
import java.util.Calendar

private enum class SleepStatus {
    Asleep,
    Awake,
}

private enum class SleepTimeField {
    Start,
    End,
}

@Composable
fun SleepWakeBottomSheet(
    onDismiss: () -> Unit,
    onSave: (
        sleepStatus: String,
        startHour: Int,
        startMinute: Int,
        endHour: Int?,
        endMinute: Int?,
        notes: String,
    ) -> Unit = { _, _, _, _, _, _ -> },
) {
    val now = remember { Calendar.getInstance() }
    var selectedStatus by remember { mutableStateOf(SleepStatus.Asleep) }
    var startHour by remember { mutableIntStateOf(now.get(Calendar.HOUR_OF_DAY)) }
    var startMinute by remember { mutableIntStateOf(now.get(Calendar.MINUTE)) }
    var endHour by remember { mutableStateOf<Int?>(null) }
    var endMinute by remember { mutableStateOf<Int?>(null) }
    var notes by remember { mutableStateOf("") }
    var activeTimeField by remember { mutableStateOf<SleepTimeField?>(null) }

    activeTimeField?.let { field ->
        val initialHour = when (field) {
            SleepTimeField.Start -> startHour
            SleepTimeField.End -> endHour ?: startHour
        }
        val initialMinute = when (field) {
            SleepTimeField.Start -> startMinute
            SleepTimeField.End -> endMinute ?: startMinute
        }

        QuickActionTimePickerDialog(
            title = when (field) {
                SleepTimeField.Start -> stringResource(R.string.sleep_start_time_label)
                SleepTimeField.End -> stringResource(R.string.sleep_end_time_label)
            },
            initialHour = initialHour,
            initialMinute = initialMinute,
            onDismiss = { activeTimeField = null },
            onConfirm = { hour, minute ->
                when (field) {
                    SleepTimeField.Start -> {
                        startHour = hour
                        startMinute = minute
                    }
                    SleepTimeField.End -> {
                        endHour = hour
                        endMinute = minute
                    }
                }
                activeTimeField = null
            },
        )
    }

    QuickActionBottomSheet(
        onDismiss = onDismiss,
        dragHandle = null,
    ) {
        SleepWakeForm(
            selectedStatus = selectedStatus,
            onStatusSelected = { selectedStatus = it },
            startTimeLabel = formatTodayTime(startHour, startMinute),
            endTimeLabel = if (endHour != null && endMinute != null) {
                formatTodayTime(endHour!!, endMinute!!)
            } else {
                null
            },
            onStartTimeClick = { activeTimeField = SleepTimeField.Start },
            onEndTimeClick = { activeTimeField = SleepTimeField.End },
            notes = notes,
            onNotesChange = { if (it.length <= QuickActionNotesMaxLength) notes = it },
            onDismiss = onDismiss,
            onSave = {
                onSave(
                    selectedStatus.name,
                    startHour,
                    startMinute,
                    endHour,
                    endMinute,
                    notes,
                )
                onDismiss()
            },
        )
    }
}

@Composable
private fun SleepWakeForm(
    selectedStatus: SleepStatus,
    onStatusSelected: (SleepStatus) -> Unit,
    startTimeLabel: String,
    endTimeLabel: String?,
    onStartTimeClick: () -> Unit,
    onEndTimeClick: () -> Unit,
    notes: String,
    onNotesChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier,
) {
    QuickActionSheetContent(modifier = modifier) {
        QuickActionSheetHeader(
            title = stringResource(R.string.add_sleep_mode),
            subtitle = stringResource(
                R.string.sleep_wake_subtitle,
                stringResource(R.string.emma_rose).substringBefore(" "),
            ),
            icon = Icons.Outlined.DarkMode,
            accent = PrimaryColor,
            accentSoft = PrimarySoft,
            onDismiss = onDismiss,
        )

        QuickActionSectionLabel(text = stringResource(R.string.sleep_status_label))
        Spacer(modifier = Modifier.height(Spacing.xs))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Spacing.xs),
        ) {
            QuickActionOptionCard(
                label = stringResource(R.string.sleep_state_asleep),
                icon = Icons.Outlined.DarkMode,
                accent = PrimaryColor,
                selectedBackground = PrimarySoft,
                selected = selectedStatus == SleepStatus.Asleep,
                onClick = { onStatusSelected(SleepStatus.Asleep) },
                modifier = Modifier.weight(1f),
            )
            QuickActionOptionCard(
                label = stringResource(R.string.sleep_state_awake),
                icon = Icons.Outlined.WbSunny,
                accent = PrimaryColor,
                selectedBackground = PrimarySoft,
                selected = selectedStatus == SleepStatus.Awake,
                onClick = { onStatusSelected(SleepStatus.Awake) },
                modifier = Modifier.weight(1f),
            )
        }

        Spacer(modifier = Modifier.height(Spacing.xl))

        QuickActionSectionLabel(text = stringResource(R.string.sleep_start_time_label))
        Spacer(modifier = Modifier.height(Spacing.xs))
        QuickActionPickerField(
            label = startTimeLabel,
            leadingIcon = Icons.Outlined.AccessTime,
            onClick = onStartTimeClick,
        )

        Spacer(modifier = Modifier.height(Spacing.xl))

        QuickActionSectionLabel(text = stringResource(R.string.sleep_end_time_label))
        Spacer(modifier = Modifier.height(Spacing.xs))
        QuickActionPickerField(
            label = endTimeLabel ?: stringResource(R.string.sleep_end_time_hint),
            leadingIcon = Icons.Outlined.AccessTime,
            onClick = onEndTimeClick,
            placeholder = endTimeLabel == null,
        )

        Spacer(modifier = Modifier.height(Spacing.xl))

        QuickActionSectionLabel(text = stringResource(R.string.sleep_notes_optional_label))
        Spacer(modifier = Modifier.height(Spacing.xs))
        QuickActionNotesField(
            notes = notes,
            onNotesChange = onNotesChange,
            placeholder = stringResource(R.string.sleep_notes_hint),
            accent = PrimaryColor,
            counterText = stringResource(
                R.string.sleep_notes_counter,
                notes.length,
                QuickActionNotesMaxLength,
            ),
        )

        Spacer(modifier = Modifier.height(Spacing.xl))

        QuickActionSaveButton(
            text = stringResource(R.string.save),
            color = PrimaryColor,
            onClick = onSave,
        )
    }
}

@Preview(showBackground = true, name = "Sleep Wake Bottom Sheet")
@Composable
private fun SleepWakeBottomSheetPreview() {
    BabyTrackerTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = BottomSheetShape,
            color = SurfaceColor,
        ) {
            SleepWakeForm(
                selectedStatus = SleepStatus.Asleep,
                onStatusSelected = {},
                startTimeLabel = "Today, 8:30 PM",
                endTimeLabel = null,
                onStartTimeClick = {},
                onEndTimeClick = {},
                notes = "",
                onNotesChange = {},
                onDismiss = {},
                onSave = {},
            )
        }
    }
}
