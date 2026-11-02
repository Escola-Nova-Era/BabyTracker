package com.escolanovaeratech.babytracker.onboarding.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.escolanovaeratech.babytracker.R
import com.escolanovaeratech.babytracker.onboarding.local.OnboardingPreferences
import com.escolanovaeratech.babytracker.onboarding.model.OnboardingPageRes
import kotlinx.coroutines.launch

class OnboardingViewModel(application: Application) : AndroidViewModel(application) {

    private val preferences = OnboardingPreferences(application)

    val pages = listOf(
        OnboardingPageRes(
            imageRes = R.drawable.onboarding_welcome,
            titleRes = R.string.onboarding_page1_title,
            descriptionRes = R.string.onboarding_page1_description
        ),
        OnboardingPageRes(
            imageRes = R.drawable.onboarding_routine,
            titleRes = R.string.onboarding_page2_title,
            descriptionRes = R.string.onboarding_page2_description
        ),
        OnboardingPageRes(
            imageRes = R.drawable.onboarding_insights,
            titleRes = R.string.onboarding_page3_title,
            descriptionRes = R.string.onboarding_page3_description
        )
    )

    fun completeOnboarding(onFinished: () -> Unit) {
        viewModelScope.launch {
            preferences.setOnboardingCompleted()
            onFinished()
        }
    }
}