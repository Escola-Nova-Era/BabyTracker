package com.escolanovaeratech.babytracker.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.BabyChangingStation
import androidx.compose.material.icons.outlined.WaterDrop
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
import com.escolanovaeratech.babytracker.theme.AccentBrown
import com.escolanovaeratech.babytracker.theme.AccentBrownSoft
import com.escolanovaeratech.babytracker.theme.AccentGreen
import com.escolanovaeratech.babytracker.theme.AccentGreenSoft
import com.escolanovaeratech.babytracker.theme.AccentViolet
import com.escolanovaeratech.babytracker.theme.AccentVioletSoft
import com.escolanovaeratech.babytracker.theme.BabyTrackerTheme
import com.escolanovaeratech.babytracker.theme.BottomSheetShape
import com.escolanovaeratech.babytracker.theme.Spacing
import com.escolanovaeratech.babytracker.theme.SurfaceColor
import java.util.Calendar

private enum class DiaperResult {
    Pee,
    Poop,
    Mixed,
}

@Composable
fun AddDiaperBottomSheet(
    onDismiss: () -> Unit,
    onSave: (diaperType: String, hour: Int, minute: Int, notes: String) -> Unit = { _, _, _, _ -> },
) {
    val now = remember { Calendar.getInstance() }
    var selectedResult by remember { mutableStateOf(DiaperResult.Pee) }
    var hour by remember { mutableIntStateOf(now.get(Calendar.HOUR_OF_DAY)) }
    var minute by remember { mutableIntStateOf(now.get(Calendar.MINUTE)) }
    var notes by remember { mutableStateOf("") }
    var showTimePicker by remember { mutableStateOf(false) }

    if (showTimePicker) {
        QuickActionTimePickerDialog(
            title = stringResource(R.string.diaper_time_label),
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

    QuickActionBottomSheet(
        onDismiss = onDismiss,
        dragHandle = null,
    ) {
        AddDiaperForm(
            selectedResult = selectedResult,
            onResultSelected = { selectedResult = it },
            timeLabel = formatTodayTime(hour, minute),
            onTimeClick = { showTimePicker = true },
            notes = notes,
            onNotesChange = { if (it.length <= QuickActionNotesMaxLength) notes = it },
            onDismiss = onDismiss,
            onSave = {
                onSave(selectedResult.name, hour, minute, notes)
                onDismiss()
            },
        )
    }
}

@Composable
private fun AddDiaperForm(
    selectedResult: DiaperResult,
    onResultSelected: (DiaperResult) -> Unit,
    timeLabel: String,
    onTimeClick: () -> Unit,
    notes: String,
    onNotesChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier,
) {
    QuickActionSheetContent(modifier = modifier) {
        QuickActionSheetHeader(
            title = stringResource(R.string.add_diaper_change),
            subtitle = stringResource(
                R.string.add_diaper_subtitle,
                stringResource(R.string.emma_rose).substringBefore(" "),
            ),
            icon = Icons.Outlined.BabyChangingStation,
            accent = AccentGreen,
            accentSoft = AccentGreenSoft,
            onDismiss = onDismiss,
        )

        QuickActionSectionLabel(text = stringResource(R.string.diaper_result_label))
        Spacer(modifier = Modifier.height(Spacing.xs))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Spacing.xs),
        ) {
            QuickActionOptionCard(
                label = stringResource(R.string.diaper_type_pee),
                icon = Icons.Outlined.WaterDrop,
                accent = AccentBlue,
                selectedBackground = AccentBlueSoft,
                selected = selectedResult == DiaperResult.Pee,
                onClick = { onResultSelected(DiaperResult.Pee) },
                modifier = Modifier.weight(1f),
            )
            QuickActionOptionCard(
                label = stringResource(R.string.diaper_type_poop),
                icon = Icons.Filled.Circle,
                accent = AccentBrown,
                selectedBackground = AccentBrownSoft,
                selected = selectedResult == DiaperResult.Poop,
                onClick = { onResultSelected(DiaperResult.Poop) },
                modifier = Modifier.weight(1f),
            )
            QuickActionOptionCard(
                label = stringResource(R.string.diaper_type_mixed),
                icon = Icons.Outlined.WaterDrop,
                accent = AccentViolet,
                selectedBackground = AccentVioletSoft,
                selected = selectedResult == DiaperResult.Mixed,
                onClick = { onResultSelected(DiaperResult.Mixed) },
                modifier = Modifier.weight(1f),
            )
        }

        Spacer(modifier = Modifier.height(Spacing.xl))

        QuickActionSectionLabel(text = stringResource(R.string.diaper_time_label))
        Spacer(modifier = Modifier.height(Spacing.xs))
        QuickActionPickerField(
            label = timeLabel,
            leadingIcon = Icons.Outlined.AccessTime,
            onClick = onTimeClick,
        )

        Spacer(modifier = Modifier.height(Spacing.xl))

        QuickActionSectionLabel(text = stringResource(R.string.diaper_notes_optional_label))
        Spacer(modifier = Modifier.height(Spacing.xs))
        QuickActionNotesField(
            notes = notes,
            onNotesChange = onNotesChange,
            placeholder = stringResource(R.string.diaper_notes_hint),
            accent = AccentGreen,
            counterText = stringResource(
                R.string.diaper_notes_counter,
                notes.length,
                QuickActionNotesMaxLength,
            ),
        )

        Spacer(modifier = Modifier.height(Spacing.xl))

        QuickActionSaveButton(
            text = stringResource(R.string.save),
            color = AccentGreen,
            onClick = onSave,
        )
    }
}

@Preview(showBackground = true, name = "Add Diaper Bottom Sheet")
@Composable
private fun AddDiaperBottomSheetPreview() {
    BabyTrackerTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = BottomSheetShape,
            color = SurfaceColor,
        ) {
            AddDiaperForm(
                selectedResult = DiaperResult.Pee,
                onResultSelected = {},
                timeLabel = "Today, 1:45 PM",
                onTimeClick = {},
                notes = "",
                onNotesChange = {},
                onDismiss = {},
                onSave = {},
            )
        }
    }
}
