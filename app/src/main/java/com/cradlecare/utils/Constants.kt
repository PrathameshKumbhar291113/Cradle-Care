package com.resq360.utils

import android.text.InputFilter

object Constants {
    const val API_BASE_URL=""
    const val GET_AGENCIES="/getAgencies"
}

object InputFilters {
    val emailFilter = InputFilter { source, _, _, _, _, _ ->
        source?.filter { char -> char.isLetterOrDigit() || char =='@' || char == '.' }
    }
}

object BundleConstants{
    const val verificationId = "verficationId"

    const val IS_LOGIN_COMPLETED = "isLoginCompleted"
    const val LOGIN = "login"
    const val IS_ONBOARDING_COMPLETED = "isOnboardingCompleted"
    const val USER_FULL_NAME = "userFullName"
    const val NAME = "name"
    const val DOB = "dob"
    const val USER_DOB = "userDob"
    const val PINCODE = "pincode"
    const val USER_PINCODE = "userPincode"
    const val ADHAR_NUM = "adharNum"
    const val USER_ADHAR_NUM = "userAdharNum"
    const val PAN_NUM = "panNum"
    const val USER_PAN_NUM = "userPanNum"
}