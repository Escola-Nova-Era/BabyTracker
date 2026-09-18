package com.escolanovaeratech.babytracker.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.AcUnit
import androidx.compose.material.icons.outlined.Bathtub
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material.icons.outlined.WaterDrop
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
import com.escolanovaeratech.babytracker.theme.AccentBlue
import com.escolanovaeratech.babytracker.theme.AccentBlueSoft
import com.escolanovaeratech.babytracker.theme.AccentOrange
import com.escolanovaeratech.babytracker.theme.AccentOrangeSoft
import com.escolanovaeratech.babytracker.theme.BabyTrackerTheme
import com.escolanovaeratech.babytracker.theme.BottomSheetShape
import com.escolanovaeratech.babytracker.theme.Spacing
import com.escolanovaeratech.babytracker.theme.SurfaceColor
import com.escolanovaeratech.babytracker.theme.TextSecondary
import com.escolanovaeratech.babytracker.theme.TextSecondarySoft
import java.util.Calendar

private val DurationOptions = listOf(5, 10, 15, 20, 30, 45, 60)

private enum class BathTemperature {
    Warm,
    Lukewarm,
    Cool,
}

@Composable
fun AddBathBottomSheet(
    onDismiss: () -> Unit,
    onSave: (
        hour: Int,
        minute: Int,
        durationMinutes: Int?,
        waterTemperature: String?,
        notes: String,
    ) -> Unit = { _, _, _, _, _ -> },
) {
    val now = remember { Calendar.getInstance() }
    var hour by remember { mutableIntStateOf(now.get(Calendar.HOUR_OF_DAY)) }
    var minute by remember { mutableIntStateOf(now.get(Calendar.MINUTE)) }
    var durationMinutes by remember { mutableStateOf<Int?>(20) }
    var selectedTemperature by remember { mutableStateOf<BathTemperature?>(BathTemperature.Warm) }
    var notes by remember { mutableStateOf("") }
    var showTimePicker by remember { mutableStateOf(false) }
    var showDurationPicker by remember { mutableStateOf(false) }

    if (showTimePicker) {
        QuickActionTimePickerDialog(
            title = stringResource(R.string.bath_time_label),
            initialHour = hour,
            initialMinute = minute,
            onDismiss = { showTimePicker = false },
            onConfirm = { selectedHour, selectedMinute ->
                hour = selectedHour
                minute = selectedMinute
                showTimePicker = false
            },
        )
    }

    if (showDurationPicker) {
        QuickActionDurationPickerDialog(
            title = stringResource(R.string.bath_duration_label),
            options = DurationOptions,
            selectedMinutes = durationMinutes,
            accent = AccentOrange,
            valueLabel = { stringResource(R.string.bath_duration_value, it) },
            onDismiss = { showDurationPicker = false },
            onConfirm = { selected ->
                durationMinutes = selected
                showDurationPicker = false
            },
            onClear = {
                durationMinutes = null
                showDurationPicker = false
            },
            clearLabel = stringResource(R.string.bath_duration_clear),
        )
    }

    QuickActionBottomSheet(
        onDismiss = onDismiss,
        dragHandle = null,
    ) {
        AddBathForm(
            timeLabel = formatTodayTime(hour, minute),
            onTimeClick = { showTimePicker = true },
            durationLabel = durationMinutes?.let {
                stringResource(R.string.bath_duration_value, it)
            },
            onDurationClick = { showDurationPicker = true },
            selectedTemperature = selectedTemperature,
            onTemperatureSelected = { selectedTemperature = it },
            notes = notes,
            onNotesChange = { if (it.length <= QuickActionNotesMaxLength) notes = it },
            onDismiss = onDismiss,
            onSave = {
                onSave(
                    hour,
                    minute,
                    durationMinutes,
                    selectedTemperature?.name,
                    notes,
                )
                onDismiss()
            },
        )
    }
}

@Composable
private fun AddBathForm(
    timeLabel: String,
    onTimeClick: () -> Unit,
    durationLabel: String?,
    onDurationClick: () -> Unit,
    selectedTemperature: BathTemperature?,
    onTemperatureSelected: (BathTemperature) -> Unit,
    notes: String,
    onNotesChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier,
) {
    QuickActionSheetContent(modifier = modifier) {
        QuickActionSheetHeader(
            title = stringResource(R.string.add_bath),
            subtitle = stringResource(
                R.string.add_bath_subtitle,
                stringResource(R.string.emma_rose).substringBefore(" "),
            ),
            icon = Icons.Outlined.Bathtub,
            accent = AccentOrange,
            accentSoft = AccentOrangeSoft,
            onDismiss = onDismiss,
        )

        QuickActionSectionLabel(text = stringResource(R.string.bath_time_label))
        Spacer(modifier = Modifier.height(Spacing.xs))
        QuickActionPickerField(
            label = timeLabel,
            leadingIcon = Icons.Outlined.AccessTime,
            onClick = onTimeClick,
        )

        Spacer(modifier = Modifier.height(Spacing.xl))

        QuickActionSectionLabel(text = stringResource(R.string.bath_duration_label))
        Spacer(modifier = Modifier.height(Spacing.xs))
        QuickActionPickerField(
            label = durationLabel ?: stringResource(R.string.bath_duration_hint),
            leadingIcon = Icons.Outlined.Timer,
            onClick = onDurationClick,
            placeholder = durationLabel == null,
        )

        Spacer(modifier = Modifier.height(Spacing.xl))

        QuickActionSectionLabel(text = stringResource(R.string.bath_temp_label))
        Spacer(modifier = Modifier.height(Spacing.xs))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Spacing.xs),
        ) {
            QuickActionOptionCard(
                label = stringResource(R.string.bath_temp_warm),
                icon = Icons.Outlined.WbSunny,
                accent = AccentOrange,
                selectedBackground = AccentOrangeSoft,
                selected = selectedTemperature == BathTemperature.Warm,
                onClick = { onTemperatureSelected(BathTemperature.Warm) },
                modifier = Modifier.weight(1f),
            )
            QuickActionOptionCard(
                label = stringResource(R.string.bath_temp_lukewarm),
                icon = Icons.Outlined.WaterDrop,
                accent = TextSecondary,
                selectedBackground = TextSecondarySoft,
                selected = selectedTemperature == BathTemperature.Lukewarm,
                onClick = { onTemperatureSelected(BathTemperature.Lukewarm) },
                modifier = Modifier.weight(1f),
            )
            QuickActionOptionCard(
                label = stringResource(R.string.bath_temp_cool),
                icon = Icons.Outlined.AcUnit,
                accent = AccentBlue,
                selectedBackground = AccentBlueSoft,
                selected = selectedTemperature == BathTemperature.Cool,
                onClick = { onTemperatureSelected(BathTemperature.Cool) },
                modifier = Modifier.weight(1f),
            )
        }

        Spacer(modifier = Modifier.height(Spacing.xl))

        QuickActionSectionLabel(text = stringResource(R.string.bath_notes_optional_label))
        Spacer(modifier = Modifier.height(Spacing.xs))
        QuickActionNotesField(
            notes = notes,
            onNotesChange = onNotesChange,
            placeholder = stringResource(R.string.bath_notes_hint),
            accent = AccentOrange,
            counterText = stringResource(
                R.string.bath_notes_counter,
                notes.length,
                QuickActionNotesMaxLength,
            ),
        )

        Spacer(modifier = Modifier.height(Spacing.xl))

        QuickActionSaveButton(
            text = stringResource(R.string.save),
            color = AccentOrange,
            onClick = onSave,
        )
    }
}

@Preview(showBackground = true, name = "Add Bath Bottom Sheet")
@Composable
private fun AddBathBottomSheetPreview() {
    BabyTrackerTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = BottomSheetShape,
            color = SurfaceColor,
        ) {
            AddBathForm(
                timeLabel = "Today, 7:30 PM",
                onTimeClick = {},
                durationLabel = "20 min",
                onDurationClick = {},
                selectedTemperature = BathTemperature.Warm,
                onTemperatureSelected = {},
                notes = "",
                onNotesChange = {},
                onDismiss = {},
                onSave = {},
            )
        }
    }
}
