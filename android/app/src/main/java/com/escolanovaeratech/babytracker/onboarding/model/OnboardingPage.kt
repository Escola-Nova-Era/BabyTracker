package com.escolanovaeratech.babytracker.onboarding.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes


data class OnboardingPage(
    @DrawableRes val imageRes: Int,
    @StringRes val titleRes: Int,
    @StringRes val descriptionRes: Int
)