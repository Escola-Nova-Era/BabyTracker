package com.escolanovaeratech.babytracker.onboarding.ui

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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
fun PersonalInfoFormScreen(
    onContinueClick: (BabyProfileFormData) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var formData by remember { mutableStateOf(BabyProfileFormData()) }
    var showDatePicker by remember { mutableStateOf(false) }
    //var showGenderPicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val isContinueEnabled = formData.name.isNotBlank()

    // Telas baixas (ex.: 360x640dp) recebem avatar e espaçamentos menores
    val isShortScreen = LocalConfiguration.current.screenHeightDp < 700
    val avatarSize = if (isShortScreen) 72.dp else 96.dp
    val sectionSpacing = if (isShortScreen) Spacing.md else Spacing.xl

    val continueGradient = Brush.horizontalGradient(
        listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.tertiary)
    )
    val continueBackground = if (isContinueEnabled) {
        continueGradient
    } else {
        SolidColor(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f))
    }
    val continueContentColor = if (isContinueEnabled) {
        Color.White
    } else {
        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(OnboardingGradients.forPage(3))
            .padding(horizontal = Spacing.xl),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(Spacing.md))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surface)
                        .clickable(onClick = onBackClick),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = stringResource(R.string.onboarding_form_back_button_description),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }

                Spacer(modifier = Modifier.weight(1f))
                OnboardingPageIndicator(numberOfPages = 4, currentPage = 3)

                /*
                Espaçador invisível do mesmo tamanho do botão, pra manter o indicador centralizado em relação à tela, não só entre os dois lados do botão.
                * */
                Spacer(modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.size(40.dp))
            }
            Spacer(modifier = Modifier.height(sectionSpacing))

            Box(
                modifier = Modifier
                    .size(avatarSize)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surface),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.onboarding_routine),
                    contentDescription = null,
                    modifier = Modifier.size(avatarSize * 2 / 3)
                )
            }

            Spacer(Modifier.height(sectionSpacing))

            Text(
                text = stringResource(R.string.onboarding_form_title),
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(Spacing.lg))

            Text(
                text = stringResource(R.string.onboarding_form_subtitle),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(sectionSpacing))

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

            Spacer(Modifier.height(Spacing.lg))

            //Gênero
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(Spacing.xs)
            ) {
                Text(
                    text = stringResource(R.string.onboarding_form_gender_label).uppercase(),
                    style = MaterialTheme.typography.labelLarge
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(Spacing.sm)
                ) {
                    BabyGender.entries.forEach { option ->
                        GenderOptionChip(
                            option = option,
                            isSelected = formData.gender == option,
                            onClick = { formData = formData.copy(gender = option) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(Spacing.md))

        // Botão de continuar
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(ComponentSize.primaryButtonHeight)
                .clip(RoundedCornerShape(18.dp))
                .background(continueBackground)
                .clickable(enabled = isContinueEnabled) { onContinueClick(formData) }
        ) {
            Text(
                text = stringResource(R.string.onboarding_form_continue_button),
                color = continueContentColor,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.width(Spacing.lg))
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                contentDescription = null,
                tint = continueContentColor
            )
        }

        // Indicador de página abaixo do botao de continuar
        Spacer(Modifier.height(Spacing.md))
        OnboardingPageIndicator(numberOfPages = 4, currentPage = 3)
        Spacer(Modifier.height(Spacing.xxl))

        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton(onClick = {
                        formData =
                            formData.copy(dateOfBirthMillis = datePickerState.selectedDateMillis)
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

}

@Preview(showBackground = true, name = "Página de formulário de perfil")
@Composable
private fun PersonalInfoFormScreenPreview() {
    BabyTrackerTheme {
        PersonalInfoFormScreen(
            onContinueClick = {},
            onBackClick = {}
        )
    }
}


private data class GenderOptionStyle(
    val emoji: String,
    val selectedContainer: Color,
    val selectedContent: Color
)


@Composable
private fun genderOptionStyle(option: BabyGender): GenderOptionStyle = when (option) {
    BabyGender.GIRL -> GenderOptionStyle(
        emoji = "👧",
        selectedContainer = colorResource(R.color.tag_violet_bg),
        selectedContent = colorResource(R.color.tag_violet_text)
    )

    BabyGender.BOY -> GenderOptionStyle(
        emoji = "👦",
        selectedContainer = colorResource(R.color.tag_blue_bg),
        selectedContent = colorResource(R.color.tag_blue_text)
    )

    BabyGender.NOT_SPECIFIED -> GenderOptionStyle(
        emoji = "🎁",
        selectedContainer = colorResource(R.color.tag_yellow_bg),
        selectedContent = colorResource(R.color.tag_yellow_text)
    )
}

@Composable
private fun GenderOptionChip(
    option: BabyGender,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val style = genderOptionStyle(option)

    // Emoji em cima do texto, para o rótulo usar toda a largura do chip em telas estreitas
    Column(
        modifier = modifier
            .heightIn(min = 64.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(if (isSelected) style.selectedContainer else MaterialTheme.colorScheme.surface)
            .clickable(onClick = onClick)
            .padding(horizontal = Spacing.xs, vertical = Spacing.xs),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = style.emoji)
        Spacer(Modifier.height(Spacing.xxs))
        Text(
            text = stringResource(option.labelRes),
            style = MaterialTheme.typography.labelLarge,
            color = if (isSelected) style.selectedContent else MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}


@Composable
fun FormField(
    label: String,
    icon: ImageVector,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    trailingLabel: String? = null,
    content: @Composable RowScope.() -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(Spacing.xs)

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = label.uppercase(),
                style = MaterialTheme.typography.labelLarge
            )
            if (trailingLabel != null) {
                Text(
                    text = trailingLabel,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
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

