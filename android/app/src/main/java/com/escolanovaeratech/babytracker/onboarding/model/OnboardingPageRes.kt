package com.escolanovaeratech.babytracker.onboarding.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class OnboardingPageRes(
    @DrawableRes val imageRes: Int,
    @StringRes val titleRes: Int,
    @StringRes val descriptionRes: Int
)