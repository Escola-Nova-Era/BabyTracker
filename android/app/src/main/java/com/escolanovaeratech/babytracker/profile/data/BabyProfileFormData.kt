package com.escolanovaeratech.babytracker.profile.data

import androidx.annotation.StringRes
import com.escolanovaeratech.babytracker.R


/* Rascunho dos dados coletados no form pós-onboarding.
* Decidir onde vai ser salvo (Room? Backend? ou os dois)
*
* */

data class BabyProfileFormData(
    val name: String = "",
    val dateOfBirthMillis: Long? = null,
    val gender: BabyGender? = null
)

enum class BabyGender(@get:StringRes val labelRes: Int){
    GIRL(R.string.onboarding_form_gender_girl),
    BOY(R.string.onboarding_form_gender_boy),
    NOT_SPECIFIED(R.string.onboarding_form_gender_not_specified)

}