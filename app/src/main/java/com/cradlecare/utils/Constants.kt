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
    const val ONBOARDING = "onboarding"
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
    const val PREGNANCY_MONTH = "pregnancyMonth"
    const val USER_PREGNANCY_MONTH = "userPregnancyMonth"
    const val BLOOD_GROUP = "bloodGroup"
    const val USER_BLOOD_GROUP = "userBloodGroup"
    const val SUBSCRIPTION = "subscription"
    const val IS_SUBSCRIPTION_ACTIVATED = "isSubscriptionActivated"
}

object StripeConstants{
    const val PUBLISHABLE_KEY = "pk_test_51Oz0DuSBxMXTEBzYrl9laZBHkRpzjGBpwjfssKnmX5Loj1UBgfE1AHvdsTkdaY5XVKWPYBEwKG2LekSKdegPDipj00RAmTbDvy"
    const val CLIENT_SECRET_KEY = "sk_test_51Oz0DuSBxMXTEBzYozvMV6QyfabkAEcwOJWc5x9xPJXMbjdxN4SjfOSfZUaBhcAJiCTEcl3ZinccK8FcUuAdvpSv00XRUIwQt0"
}

object RazorPayConstants{
    const val TEST_KEY_ID = "rzp_test_DziyEguOdZPEa5"
    const val LIVE_KEY_ID = "rzp_live_r5d0zbx80yn3vR"
}

object NavigationConstants{
    val IS_PAYMENT_SUCCESSFUL = "isPaymentSuccessFul"
    val PAYMENT_TRANSACTION_ID = "paymentTransactionId"
}