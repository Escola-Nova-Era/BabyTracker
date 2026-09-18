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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.escolanovaeratech.babytracker.R
import com.escolanovaeratech.babytracker.theme.AppTypography
import com.escolanovaeratech.babytracker.theme.BottomSheetShape
import com.escolanovaeratech.babytracker.theme.CardShapeLarge
import com.escolanovaeratech.babytracker.theme.ComponentSize
import com.escolanovaeratech.babytracker.theme.DividerColor
import com.escolanovaeratech.babytracker.theme.IconSize
import com.escolanovaeratech.babytracker.theme.SelectionBorderAlpha
import com.escolanovaeratech.babytracker.theme.SheetFieldShape
import com.escolanovaeratech.babytracker.theme.Spacing
import com.escolanovaeratech.babytracker.theme.SurfaceColor
import com.escolanovaeratech.babytracker.theme.TextHint
import com.escolanovaeratech.babytracker.theme.TextOnPrimary
import com.escolanovaeratech.babytracker.theme.TextPrimary
import com.escolanovaeratech.babytracker.theme.TextSecondary
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

const val QuickActionNotesMaxLength = 100

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuickActionBottomSheet(
    onDismiss: () -> Unit,
    dragHandle: @Composable (() -> Unit)? = { BottomSheetDefaults.DragHandle() },
    content: @Composable () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = SurfaceColor,
        shape = BottomSheetShape,
        dragHandle = dragHandle,
    ) {
        content()
    }
}

@Composable
fun QuickActionSheetContent(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Spacing.xl)
            .padding(bottom = Spacing.xxl),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        content()
    }
}

@Composable
fun QuickActionSheetHeader(
    title: String,
    subtitle: String,
    icon: ImageVector,
    accent: Color,
    accentSoft: Color,
    onDismiss: () -> Unit,
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
            .size(ComponentSize.headerIconCircle)
            .clip(CircleShape)
            .background(accentSoft),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = accent,
            modifier = Modifier.size(IconSize.xxl),
        )
    }

    Spacer(modifier = Modifier.height(Spacing.md))

    Text(
        text = title,
        style = AppTypography.headlineSmall,
        fontWeight = FontWeight.Bold,
        color = TextPrimary,
        textAlign = TextAlign.Center,
    )

    Spacer(modifier = Modifier.height(Spacing.xxs))

    Text(
        text = subtitle,
        style = AppTypography.bodyMedium,
        color = TextSecondary,
        textAlign = TextAlign.Center,
    )

    Spacer(modifier = Modifier.height(Spacing.xl + Spacing.xxs))
}

@Composable
fun QuickActionSectionLabel(text: String) {
    Text(
        text = text,
        modifier = Modifier.fillMaxWidth(),
        style = AppTypography.labelMedium,
        fontWeight = FontWeight.SemiBold,
        color = TextSecondary,
    )
}

@Composable
fun QuickActionPickerField(
    label: String,
    leadingIcon: ImageVector,
    onClick: () -> Unit,
    placeholder: Boolean = false,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(SheetFieldShape)
            .border(ComponentSize.borderThin, DividerColor, SheetFieldShape)
            .background(SurfaceColor)
            .clickable(onClick = onClick)
            .padding(horizontal = Spacing.md, vertical = Spacing.md),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = leadingIcon,
            contentDescription = null,
            tint = TextSecondary,
            modifier = Modifier.size(IconSize.md),
        )
        Text(
            text = label,
            style = AppTypography.bodyLarge,
            color = if (placeholder) TextHint else TextPrimary,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = Spacing.sm),
        )
        Icon(
            imageVector = Icons.Outlined.KeyboardArrowDown,
            contentDescription = null,
            tint = TextSecondary,
        )
    }
}

@Composable
fun QuickActionTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    accent: Color,
    leadingIcon: ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    singleLine: Boolean = true,
    minLines: Int = 1,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = TextHint) },
        leadingIcon = leadingIcon?.let {
            {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    tint = TextSecondary,
                )
            }
        },
        modifier = modifier.fillMaxWidth(),
        shape = SheetFieldShape,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = DividerColor,
            focusedBorderColor = accent,
            unfocusedContainerColor = SurfaceColor,
            focusedContainerColor = SurfaceColor,
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = singleLine,
        minLines = minLines,
    )
}

@Composable
fun QuickActionNotesField(
    notes: String,
    onNotesChange: (String) -> Unit,
    placeholder: String,
    accent: Color,
    counterText: String,
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        QuickActionTextField(
            value = notes,
            onValueChange = onNotesChange,
            placeholder = placeholder,
            accent = accent,
            singleLine = false,
            minLines = 4,
            modifier = Modifier.height(ComponentSize.notesFieldMinHeight),
        )
        Text(
            text = counterText,
            style = AppTypography.labelSmall,
            color = TextHint,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = Spacing.sm, bottom = Spacing.sm),
        )
    }
}

@Composable
fun QuickActionOptionCard(
    label: String,
    icon: ImageVector,
    accent: Color,
    selectedBackground: Color,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val background = if (selected) selectedBackground else SurfaceColor
    val borderColor = if (selected) accent.copy(alpha = SelectionBorderAlpha) else DividerColor
    val contentColor = if (selected) accent else TextSecondary

    Column(
        modifier = modifier
            .clip(SheetFieldShape)
            .border(BorderStroke(ComponentSize.borderSelected, borderColor), SheetFieldShape)
            .background(background)
            .clickable(onClick = onClick)
            .padding(vertical = Spacing.md),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Spacing.xs),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (selected) accent else contentColor,
            modifier = Modifier.size(IconSize.lg),
        )
        Text(
            text = label,
            style = AppTypography.titleSmall,
            fontWeight = FontWeight.SemiBold,
            color = contentColor,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun QuickActionSaveButton(
    text: String,
    color: Color,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(ComponentSize.primaryButtonHeight),
        shape = CardShapeLarge,
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = TextOnPrimary,
        ),
    ) {
        Text(
            text = text,
            style = AppTypography.titleMedium,
            fontWeight = FontWeight.Bold,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuickActionTimePickerDialog(
    title: String,
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
        title = { Text(title) },
        text = { TimePicker(state = state) },
        containerColor = SurfaceColor,
    )
}

@Composable
fun QuickActionDurationPickerDialog(
    title: String,
    options: List<Int>,
    selectedMinutes: Int?,
    accent: Color,
    valueLabel: @Composable (Int) -> String,
    onDismiss: () -> Unit,
    onConfirm: (Int) -> Unit,
    onClear: () -> Unit,
    clearLabel: String,
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
                Text(clearLabel)
            }
        },
        title = { Text(title) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(Spacing.xs)) {
                options.chunked(4).forEach { row ->
                    Row(horizontalArrangement = Arrangement.spacedBy(Spacing.xs)) {
                        row.forEach { minutes ->
                            val selected = minutes == selectedMinutes
                            TextButton(
                                onClick = { onConfirm(minutes) },
                                colors = ButtonDefaults.textButtonColors(
                                    contentColor = if (selected) accent else TextPrimary,
                                ),
                            ) {
                                Text(
                                    text = valueLabel(minutes),
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

fun formatTodayTime(hour: Int, minute: Int): String {
    val calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, hour)
        set(Calendar.MINUTE, minute)
    }
    val time = SimpleDateFormat("h:mm a", Locale.getDefault()).format(calendar.time)
    return "Today, $time"
}

fun digitsOnly(value: String): String = value.filter { it.isDigit() }
