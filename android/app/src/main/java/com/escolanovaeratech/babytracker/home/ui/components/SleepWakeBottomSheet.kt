package com.escolanovaeratech.babytracker.home.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.escolanovaeratech.babytracker.R
import com.escolanovaeratech.babytracker.theme.AppTypography
import com.escolanovaeratech.babytracker.theme.BabyTrackerTheme
import com.escolanovaeratech.babytracker.theme.DividerColor
import com.escolanovaeratech.babytracker.theme.PrimaryColor
import com.escolanovaeratech.babytracker.theme.SurfaceColor
import com.escolanovaeratech.babytracker.theme.TextHint
import com.escolanovaeratech.babytracker.theme.TextOnPrimary
import com.escolanovaeratech.babytracker.theme.TextPrimary
import com.escolanovaeratech.babytracker.theme.TextSecondary
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

private const val NotesMaxLength = 100

private val SleepPurple = PrimaryColor
private val SleepPurpleSoft = Color(0xFFF3E8FF)

enum class SleepStatus {
    Asleep,
    Awake,
}

private enum class SleepTimeField {
    Start,
    End,
}

@OptIn(ExperimentalMaterial3Api::class)
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

        SleepTimePickerDialog(
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
            startTimeLabel = formatSleepTime(startHour, startMinute),
            endTimeLabel = if (endHour != null && endMinute != null) {
                formatSleepTime(endHour!!, endMinute!!)
            } else {
                null
            },
            onStartTimeClick = { activeTimeField = SleepTimeField.Start },
            onEndTimeClick = { activeTimeField = SleepTimeField.End },
            notes = notes,
            onNotesChange = { if (it.length <= NotesMaxLength) notes = it },
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
fun SleepWakeForm(
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
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(bottom = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                onClick = onDismiss,
                modifier = Modifier.align(Alignment.TopEnd),
            ) {
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = stringResource(R.string.close),
                    tint = TextSecondary,
                )
            }
        }

        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(SleepPurpleSoft),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.Outlined.DarkMode,
                contentDescription = null,
                tint = SleepPurple,
                modifier = Modifier.size(32.dp),
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.add_sleep_mode),
            style = AppTypography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = TextPrimary,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stringResource(
                R.string.sleep_wake_subtitle,
                stringResource(R.string.emma_rose).substringBefore(" "),
            ),
            style = AppTypography.bodyMedium,
            color = TextSecondary,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(28.dp))

        SleepSectionLabel(text = stringResource(R.string.sleep_status_label))

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            SleepStatusOption(
                label = stringResource(R.string.sleep_state_asleep),
                icon = Icons.Outlined.DarkMode,
                selected = selectedStatus == SleepStatus.Asleep,
                onClick = { onStatusSelected(SleepStatus.Asleep) },
                modifier = Modifier.weight(1f),
            )
            SleepStatusOption(
                label = stringResource(R.string.sleep_state_awake),
                icon = Icons.Outlined.WbSunny,
                selected = selectedStatus == SleepStatus.Awake,
                onClick = { onStatusSelected(SleepStatus.Awake) },
                modifier = Modifier.weight(1f),
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        SleepSectionLabel(text = stringResource(R.string.sleep_start_time_label))

        Spacer(modifier = Modifier.height(10.dp))

        SleepTimeField(
            label = startTimeLabel,
            placeholder = false,
            onClick = onStartTimeClick,
        )

        Spacer(modifier = Modifier.height(24.dp))

        SleepSectionLabel(text = stringResource(R.string.sleep_end_time_label))

        Spacer(modifier = Modifier.height(10.dp))

        SleepTimeField(
            label = endTimeLabel ?: stringResource(R.string.sleep_end_time_hint),
            placeholder = endTimeLabel == null,
            onClick = onEndTimeClick,
        )

        Spacer(modifier = Modifier.height(24.dp))

        SleepSectionLabel(text = stringResource(R.string.sleep_notes_optional_label))

        Spacer(modifier = Modifier.height(10.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = notes,
                onValueChange = onNotesChange,
                placeholder = {
                    Text(
                        text = stringResource(R.string.sleep_notes_hint),
                        color = TextHint,
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                shape = RoundedCornerShape(14.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = DividerColor,
                    focusedBorderColor = SleepPurple,
                    unfocusedContainerColor = SurfaceColor,
                    focusedContainerColor = SurfaceColor,
                ),
                minLines = 4,
            )
            Text(
                text = stringResource(R.string.sleep_notes_counter, notes.length, NotesMaxLength),
                style = AppTypography.labelSmall,
                color = TextHint,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 14.dp, bottom = 12.dp),
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onSave,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = SleepPurple,
                contentColor = TextOnPrimary,
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
        ) {
            Text(
                text = stringResource(R.string.save),
                style = AppTypography.titleMedium,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
private fun SleepSectionLabel(text: String) {
    Text(
        text = text,
        modifier = Modifier.fillMaxWidth(),
        style = AppTypography.labelMedium,
        fontWeight = FontWeight.SemiBold,
        color = TextSecondary,
        letterSpacing = 0.8.sp,
    )
}

@Composable
private fun SleepStatusOption(
    label: String,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val background = if (selected) SleepPurpleSoft else SurfaceColor
    val borderColor = if (selected) SleepPurple.copy(alpha = 0.55f) else DividerColor
    val contentColor = if (selected) SleepPurple else TextSecondary

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .border(BorderStroke(1.5.dp, borderColor), RoundedCornerShape(14.dp))
            .background(background)
            .clickable(onClick = onClick)
            .padding(vertical = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = contentColor,
            modifier = Modifier.size(26.dp),
        )
        Text(
            text = label,
            style = AppTypography.titleSmall,
            fontWeight = FontWeight.SemiBold,
            color = contentColor,
        )
    }
}

@Composable
private fun SleepTimeField(
    label: String,
    placeholder: Boolean,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .border(1.dp, DividerColor, RoundedCornerShape(14.dp))
            .background(SurfaceColor)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Outlined.AccessTime,
            contentDescription = null,
            tint = TextSecondary,
            modifier = Modifier.size(22.dp),
        )
        Text(
            text = label,
            style = AppTypography.bodyLarge,
            color = if (placeholder) TextHint else TextPrimary,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp),
        )
        Icon(
            imageVector = Icons.Outlined.KeyboardArrowDown,
            contentDescription = null,
            tint = TextSecondary,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SleepTimePickerDialog(
    initialHour: Int,
    initialMinute: Int,
    onDismiss: () -> Unit,
    onConfirm: (hour: Int, minute: Int) -> Unit,
) {
    val state = rememberTimePickerState(
        initialHour = initialHour,
        initialMinute = initialMinute,
        is24Hour = false,
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = { onConfirm(state.hour, state.minute) }) {
                Text(stringResource(R.string.save))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.close))
            }
        },
        title = {
            Text(stringResource(R.string.sleep_start_time_label))
        },
        text = {
            TimePicker(state = state)
        },
        containerColor = SurfaceColor,
    )
}

private fun formatSleepTime(hour: Int, minute: Int): String {
    val calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, hour)
        set(Calendar.MINUTE, minute)
    }
    val time = SimpleDateFormat("h:mm a", Locale.getDefault()).format(calendar.time)
    return "Today, $time"
}

@Preview(showBackground = true, name = "Sleep Wake Bottom Sheet")
@Composable
private fun SleepWakeBottomSheetPreview() {
    BabyTrackerTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
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

