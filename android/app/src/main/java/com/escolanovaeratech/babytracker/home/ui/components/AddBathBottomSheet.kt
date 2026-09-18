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
import androidx.compose.material.icons.outlined.AcUnit
import androidx.compose.material.icons.outlined.Bathtub
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material.icons.outlined.WaterDrop
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
import com.escolanovaeratech.babytracker.theme.AccentBlue
import com.escolanovaeratech.babytracker.theme.AccentOrange
import com.escolanovaeratech.babytracker.theme.AppTypography
import com.escolanovaeratech.babytracker.theme.BabyTrackerTheme
import com.escolanovaeratech.babytracker.theme.DividerColor
import com.escolanovaeratech.babytracker.theme.SurfaceColor
import com.escolanovaeratech.babytracker.theme.TextHint
import com.escolanovaeratech.babytracker.theme.TextOnPrimary
import com.escolanovaeratech.babytracker.theme.TextPrimary
import com.escolanovaeratech.babytracker.theme.TextSecondary
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

private const val NotesMaxLength = 100

private val BathOrange = AccentOrange
private val BathOrangeSoft = Color(0xFFFFF0E6)
private val CoolBlue = AccentBlue
private val CoolBlueSoft = Color(0xFFE3F2FD)
private val LukewarmGray = Color(0xFF78909C)
private val LukewarmGraySoft = Color(0xFFEEF2F4)

private val DurationOptions = listOf(5, 10, 15, 20, 30, 45, 60)

enum class BathTemperature {
    Warm,
    Lukewarm,
    Cool,
}

@OptIn(ExperimentalMaterial3Api::class)
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
        BathTimePickerDialog(
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
        BathDurationPickerDialog(
            selectedMinutes = durationMinutes,
            onDismiss = { showDurationPicker = false },
            onConfirm = { selected ->
                durationMinutes = selected
                showDurationPicker = false
            },
            onClear = {
                durationMinutes = null
                showDurationPicker = false
            },
        )
    }

    QuickActionBottomSheet(
        onDismiss = onDismiss,
        dragHandle = null,
    ) {
        AddBathForm(
            timeLabel = formatBathTime(hour, minute),
            onTimeClick = { showTimePicker = true },
            durationLabel = durationMinutes?.let {
                stringResource(R.string.bath_duration_value, it)
            },
            onDurationClick = { showDurationPicker = true },
            selectedTemperature = selectedTemperature,
            onTemperatureSelected = { selectedTemperature = it },
            notes = notes,
            onNotesChange = { if (it.length <= NotesMaxLength) notes = it },
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
fun AddBathForm(
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
                .background(BathOrangeSoft),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.Outlined.Bathtub,
                contentDescription = null,
                tint = BathOrange,
                modifier = Modifier.size(32.dp),
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.add_bath),
            style = AppTypography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = TextPrimary,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stringResource(
                R.string.add_bath_subtitle,
                stringResource(R.string.emma_rose).substringBefore(" "),
            ),
            style = AppTypography.bodyMedium,
            color = TextSecondary,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(28.dp))

        BathSectionLabel(text = stringResource(R.string.bath_time_label))

        Spacer(modifier = Modifier.height(10.dp))

        BathPickerField(
            label = timeLabel,
            placeholder = false,
            leadingIcon = Icons.Outlined.AccessTime,
            onClick = onTimeClick,
        )

        Spacer(modifier = Modifier.height(24.dp))

        BathSectionLabel(text = stringResource(R.string.bath_duration_label))

        Spacer(modifier = Modifier.height(10.dp))

        BathPickerField(
            label = durationLabel ?: stringResource(R.string.bath_duration_hint),
            placeholder = durationLabel == null,
            leadingIcon = Icons.Outlined.Timer,
            onClick = onDurationClick,
        )

        Spacer(modifier = Modifier.height(24.dp))

        BathSectionLabel(text = stringResource(R.string.bath_temp_label))

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            BathTemperatureOption(
                label = stringResource(R.string.bath_temp_warm),
                icon = Icons.Outlined.WbSunny,
                accent = BathOrange,
                selectedBackground = BathOrangeSoft,
                selected = selectedTemperature == BathTemperature.Warm,
                onClick = { onTemperatureSelected(BathTemperature.Warm) },
                modifier = Modifier.weight(1f),
            )
            BathTemperatureOption(
                label = stringResource(R.string.bath_temp_lukewarm),
                icon = Icons.Outlined.WaterDrop,
                accent = LukewarmGray,
                selectedBackground = LukewarmGraySoft,
                selected = selectedTemperature == BathTemperature.Lukewarm,
                onClick = { onTemperatureSelected(BathTemperature.Lukewarm) },
                modifier = Modifier.weight(1f),
            )
            BathTemperatureOption(
                label = stringResource(R.string.bath_temp_cool),
                icon = Icons.Outlined.AcUnit,
                accent = CoolBlue,
                selectedBackground = CoolBlueSoft,
                selected = selectedTemperature == BathTemperature.Cool,
                onClick = { onTemperatureSelected(BathTemperature.Cool) },
                modifier = Modifier.weight(1f),
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        BathSectionLabel(text = stringResource(R.string.bath_notes_optional_label))

        Spacer(modifier = Modifier.height(10.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = notes,
                onValueChange = onNotesChange,
                placeholder = {
                    Text(
                        text = stringResource(R.string.bath_notes_hint),
                        color = TextHint,
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                shape = RoundedCornerShape(14.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = DividerColor,
                    focusedBorderColor = BathOrange,
                    unfocusedContainerColor = SurfaceColor,
                    focusedContainerColor = SurfaceColor,
                ),
                minLines = 4,
            )
            Text(
                text = stringResource(R.string.bath_notes_counter, notes.length, NotesMaxLength),
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
                containerColor = BathOrange,
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
private fun BathSectionLabel(text: String) {
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
private fun BathPickerField(
    label: String,
    placeholder: Boolean,
    leadingIcon: ImageVector,
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
            imageVector = leadingIcon,
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

@Composable
private fun BathTemperatureOption(
    label: String,
    icon: ImageVector,
    accent: Color,
    selectedBackground: Color,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val background = if (selected) selectedBackground else SurfaceColor
    val borderColor = if (selected) accent.copy(alpha = 0.55f) else DividerColor
    val contentColor = if (selected) accent else TextSecondary

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .border(BorderStroke(1.5.dp, borderColor), RoundedCornerShape(14.dp))
            .background(background)
            .clickable(onClick = onClick)
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (selected) accent else accent.copy(alpha = 0.85f),
            modifier = Modifier.size(24.dp),
        )
        Text(
            text = label,
            style = AppTypography.labelLarge,
            fontWeight = FontWeight.SemiBold,
            color = contentColor,
            textAlign = TextAlign.Center,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BathTimePickerDialog(
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
            Text(stringResource(R.string.bath_time_label))
        },
        text = {
            TimePicker(state = state)
        },
        containerColor = SurfaceColor,
    )
}

@Composable
private fun BathDurationPickerDialog(
    selectedMinutes: Int?,
    onDismiss: () -> Unit,
    onConfirm: (Int) -> Unit,
    onClear: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.close))
            }
        },
        dismissButton = {
            TextButton(onClick = onClear) {
                Text(stringResource(R.string.bath_duration_clear))
            }
        },
        title = {
            Text(stringResource(R.string.bath_duration_label))
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                DurationOptions.chunked(4).forEach { row ->
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        row.forEach { minutes ->
                            val selected = minutes == selectedMinutes
                            TextButton(
                                onClick = { onConfirm(minutes) },
                                colors = ButtonDefaults.textButtonColors(
                                    contentColor = if (selected) BathOrange else TextPrimary,
                                ),
                            ) {
                                Text(
                                    text = stringResource(R.string.bath_duration_value, minutes),
                                    fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                                )
                            }
                        }
                    }
                }
            }
        },
        containerColor = SurfaceColor,
    )
}

private fun formatBathTime(hour: Int, minute: Int): String {
    val calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, hour)
        set(Calendar.MINUTE, minute)
    }
    val time = SimpleDateFormat("h:mm a", Locale.getDefault()).format(calendar.time)
    return "Today, $time"
}

@Preview(showBackground = true, name = "Add Bath Bottom Sheet")
@Composable
private fun AddBathBottomSheetPreview() {
    BabyTrackerTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
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

