package com.escolanovaeratech.babytracker.onboarding.local

private val Context.onboardingDataStore by preferencesDataStore(name = "onboarding_prefs")

class OnboardingPreferences(private val context: Context) {

    private object Keys{
        val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
    }

    val onboardingCompleted: Flow<Boolean> =
        context.onboardingDataStore.data.map { prefs ->
            prefs[Keys.ONBOARDING_COMPLETED] ?: false
        }

    suspend fun setOnboardingCompleted() {
        context.onboardingDataStore.edit { prefs ->
            prefs[Keys.ONBOARDING_COMPLETED] = true
        }
    }
}