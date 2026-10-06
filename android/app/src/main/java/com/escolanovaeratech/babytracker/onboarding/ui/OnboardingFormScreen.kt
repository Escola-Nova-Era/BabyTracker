package com.escolanovaeratech.babytracker.onboarding.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.ChildCare
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Wc
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.escolanovaeratech.babytracker.R
import com.escolanovaeratech.babytracker.onboarding.ui.components.OnboardingGradients
import com.escolanovaeratech.babytracker.onboarding.ui.components.OnboardingPageIndicator
import com.escolanovaeratech.babytracker.profile.data.BabyGender
import com.escolanovaeratech.babytracker.profile.data.BabyProfileFormData
import com.escolanovaeratech.babytracker.theme.BabyTrackerTheme
import com.escolanovaeratech.babytracker.theme.ComponentSize
import com.escolanovaeratech.babytracker.theme.Spacing
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun OnboardingFormScreen(
    onContinueClick: (BabyProfileFormData) -> Unit,
    modifier: Modifier = Modifier
) {
    var formData by remember { mutableStateOf(BabyProfileFormData()) }
    var showDatePicker by remember { mutableStateOf(false) }
    var showGenderPicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val isContinueEnabled = formData.name.isNotBlank()


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(OnboardingGradients.forPage(3))
            .verticalScroll(rememberScrollState())
            .padding(horizontal = Spacing.xl),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(Spacing.xxl))

        Icon(
            imageVector = Icons.Outlined.ChildCare,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(48.dp)
        )

        Spacer(Modifier.height(Spacing.sm))

        Text(
            text = stringResource(R.string.onboarding_form_title),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(Spacing.xxs))

        Text(
            text = stringResource(R.string.onboarding_form_subtitle),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(Spacing.xl))

        // Nome do bebê
        FormField(
            label = stringResource(R.string.onboarding_form_name_label),
            icon = Icons.Outlined.Person
        ) {
            val nameInteractionSource = remember { MutableInteractionSource() }
            BasicTextField(
                value = formData.name,
                onValueChange = { formData = formData.copy(name = it) },
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onSurface
                ),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                interactionSource = nameInteractionSource,
                modifier = Modifier.fillMaxWidth()
            ) { innerTextField ->
                TextFieldDefaults.DecorationBox(
                    value = formData.name,
                    innerTextField = innerTextField,
                    enabled = true,
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = nameInteractionSource,
                    placeholder = { Text(stringResource(R.string.onboarding_form_name_placeholder)) },
                    contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(start = 0.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    )
                )
            }
        }

        // Data de nascimento
        FormField(
            label = stringResource(R.string.onboarding_form_birthdate_label),
            icon = Icons.Outlined.CalendarMonth,
            modifier = Modifier.padding(top = Spacing.lg),
            onClick = { showDatePicker = true }
        ) {
            Text(
                text = formData.dateOfBirthMillis?.let(::formatDate)
                    ?: stringResource(R.string.onboarding_form_birthdate_placeholder),
                color = if (formData.dateOfBirthMillis != null) {
                    MaterialTheme.colorScheme.onSurface
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                }
            )
        }

        //Gênero
        Box(modifier = Modifier.fillMaxWidth()){
            FormField(
                label = stringResource(R.string.onboarding_form_gender_label),
                icon = Icons.Outlined.Wc,
                modifier = Modifier.padding(top = Spacing.lg),
                onClick = { showGenderPicker = true }
            ) {
                Text(
                    text = formData.gender?.let { stringResource(it.labelRes) }
                        ?:stringResource(R.string.onboarding_form_gender_placeholder),
                    color = if (formData.gender != null) {
                        MaterialTheme.colorScheme.onSurface
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    },

                    modifier = Modifier.weight(1f)
                )

                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowDown,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )


            }

            DropdownMenu (
                expanded = showGenderPicker,
                onDismissRequest = { showGenderPicker = false }

            ){
                BabyGender.entries.forEach {  option ->
                    DropdownMenuItem(
                        text = { Text(stringResource(option.labelRes)) },
                        onClick = {
                            formData = formData.copy(gender = option)
                            showGenderPicker = false
                        }
                    )
                }

            }
        }

        Spacer(Modifier.height(Spacing.xxl))


        val continueGradient = Brush.horizontalGradient(
            listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.tertiary)
        )

        val isContinueEnabled = true

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(ComponentSize.primaryButtonHeight)
                .clip(RoundedCornerShape(18.dp))
                .background(
                    if (isContinueEnabled) continueGradient
                    else Brush.horizontalGradient(
                        listOf(MaterialTheme.colorScheme.surfaceVariant, MaterialTheme.colorScheme.surfaceVariant)
                    )
                )
                .clickable(enabled = isContinueEnabled) { onContinueClick(formData) }
        ) {
            Text(
                text = stringResource(R.string.onboarding_form_continue_button),
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )
        }

        Spacer(Modifier.height(Spacing.lg))

        OnboardingPageIndicator(numberOfPages = 4, currentPage = 3)

        Spacer(Modifier.height(Spacing.lg))

    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    formData = formData.copy(dateOfBirthMillis = datePickerState.selectedDateMillis)
                    showDatePicker = false
                }) {
                    Text(text = stringResource(R.string.onboarding_form_action_confirm))

                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text(text = stringResource(R.string.onboarding_form_action_cancel))
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun OnboardingFormScreenPreview() {
    BabyTrackerTheme {
        OnboardingFormScreen(onContinueClick = {})
    }
}

@Composable
fun FormField(
    label: String,
    icon: ImageVector,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(Spacing.xs)

    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,

        )
        val shellModifier = Modifier
            .fillMaxWidth()
            .height(58.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surface)
            .let { base -> if (onClick != null) base.clickable(onClick = onClick) else base }
            .padding(horizontal = Spacing.md)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Spacing.xs),
            modifier = shellModifier
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            content()
        }
    }
}

fun formatDate(millis: Long): String =
    SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date(millis))
