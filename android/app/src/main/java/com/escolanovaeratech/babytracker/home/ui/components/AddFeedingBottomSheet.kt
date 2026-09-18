package com.escolanovaeratech.babytracker.home.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.LocalDrink
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.escolanovaeratech.babytracker.R
import com.escolanovaeratech.babytracker.theme.AccentBlue
import com.escolanovaeratech.babytracker.theme.AccentBlueSoft
import com.escolanovaeratech.babytracker.theme.BabyTrackerTheme
import com.escolanovaeratech.babytracker.theme.BottomSheetShape
import com.escolanovaeratech.babytracker.theme.Spacing
import com.escolanovaeratech.babytracker.theme.SurfaceColor
import java.util.Calendar

@Composable
fun AddFeedingBottomSheet(
    onDismiss: () -> Unit,
    onSave: (
        hour: Int,
        minute: Int,
        amountMl: String,
        notes: String,
    ) -> Unit = { _, _, _, _ -> },
) {
    val now = remember { Calendar.getInstance() }
    var hour by remember { mutableIntStateOf(now.get(Calendar.HOUR_OF_DAY)) }
    var minute by remember { mutableIntStateOf(now.get(Calendar.MINUTE)) }
    var amountMl by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var showTimePicker by remember { mutableStateOf(false) }

    if (showTimePicker) {
        QuickActionTimePickerDialog(
            title = stringResource(R.string.feeding_time_label),
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
        AddFeedingForm(
            timeLabel = formatTodayTime(hour, minute),
            onTimeClick = { showTimePicker = true },
            amountMl = amountMl,
            onAmountChange = { amountMl = digitsOnly(it) },
            notes = notes,
            onNotesChange = { if (it.length <= QuickActionNotesMaxLength) notes = it },
            onDismiss = onDismiss,
            onSave = {
                onSave(hour, minute, amountMl, notes)
                onDismiss()
            },
        )
    }
}

@Composable
private fun AddFeedingForm(
    timeLabel: String,
    onTimeClick: () -> Unit,
    amountMl: String,
    onAmountChange: (String) -> Unit,
    notes: String,
    onNotesChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier,
) {
    QuickActionSheetContent(modifier = modifier) {
        QuickActionSheetHeader(
            title = stringResource(R.string.add_feeding),
            subtitle = stringResource(
                R.string.add_feeding_subtitle,
                stringResource(R.string.emma_rose).substringBefore(" "),
            ),
            icon = Icons.Outlined.LocalDrink,
            accent = AccentBlue,
            accentSoft = AccentBlueSoft,
            onDismiss = onDismiss,
        )

        QuickActionSectionLabel(text = stringResource(R.string.feeding_time_label))
        Spacer(modifier = Modifier.height(Spacing.xs))
        QuickActionPickerField(
            label = timeLabel,
            leadingIcon = Icons.Outlined.AccessTime,
            onClick = onTimeClick,
        )

        Spacer(modifier = Modifier.height(Spacing.xl))

        QuickActionSectionLabel(text = stringResource(R.string.feeding_amount_label))
        Spacer(modifier = Modifier.height(Spacing.xs))
        QuickActionTextField(
            value = amountMl,
            onValueChange = onAmountChange,
            placeholder = stringResource(R.string.feeding_amount_hint),
            accent = AccentBlue,
            leadingIcon = Icons.Outlined.WaterDrop,
            keyboardType = KeyboardType.Number,
        )

        Spacer(modifier = Modifier.height(Spacing.xl))

        QuickActionSectionLabel(text = stringResource(R.string.feeding_notes_optional_label))
        Spacer(modifier = Modifier.height(Spacing.xs))
        QuickActionNotesField(
            notes = notes,
            onNotesChange = onNotesChange,
            placeholder = stringResource(R.string.feeding_notes_hint),
            accent = AccentBlue,
            counterText = stringResource(
                R.string.feeding_notes_counter,
                notes.length,
                QuickActionNotesMaxLength,
            ),
        )

        Spacer(modifier = Modifier.height(Spacing.xl))

        QuickActionSaveButton(
            text = stringResource(R.string.save),
            color = AccentBlue,
            onClick = onSave,
        )
    }
}

@Preview(showBackground = true, name = "Add Feeding Bottom Sheet")
@Composable
private fun AddFeedingBottomSheetPreview() {
    BabyTrackerTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = BottomSheetShape,
            color = SurfaceColor,
        ) {
            AddFeedingForm(
                timeLabel = "Today, 2:15 PM",
                onTimeClick = {},
                amountMl = "120",
                onAmountChange = {},
                notes = "",
                onNotesChange = {},
                onDismiss = {},
                onSave = {},
            )
        }
    }
}
