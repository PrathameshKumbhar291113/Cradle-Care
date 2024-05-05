package com.cradlecare.utils

import android.app.Activity
import android.content.Context
import android.util.Patterns
import android.view.Window
import android.view.inputmethod.InputMethodManager
import com.cradlecare.R
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.Date
import java.util.Locale


fun Activity.hideKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(this.currentFocus?.rootView?.windowToken, 0)
}

fun isValidEmail(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun isValidPhoneNumber(phoneNumber: String): Boolean {
    return Patterns.PHONE.matcher(phoneNumber).matches()
}

fun daysUntilDueDate(expectedDateStr: String): Int {
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    return try {
        val expectedDate = LocalDate.parse(expectedDateStr, formatter)
        val currentDate = LocalDate.now()

        val daysLeft = ChronoUnit.DAYS.between(currentDate, expectedDate).toInt()
        if (daysLeft >= 0) daysLeft else 0
    } catch (e: Exception) {
        println("Invalid date format. Please provide the date in the format dd-MM-yyyy.")
        0
    }
}

fun appStatusBarColor(context: Context, window: Window) {
    window.statusBarColor = context.resources.getColor(R.color.rose_fam_400)
}

fun getMonthlySubscriptionValidityDates(): Pair<String, String> {
    val today = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    val todayDate = dateFormat.format(today.time)

    val nextMonth = Calendar.getInstance()
    nextMonth.add(Calendar.MONTH, 1)
    val nextMonthDate = dateFormat.format(nextMonth.time)

    return Pair(todayDate, nextMonthDate)
}

data class PregnancyTips(
    var titleTip: String,
    var tipDescription: String
)

fun calculateAge(dateOfBirthStr: String): Int {

    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    try {

        val dateOfBirth: Date = dateFormat.parse(dateOfBirthStr) ?: return -1

        val currentDate = Calendar.getInstance()
        val dob = Calendar.getInstance().apply { time = dateOfBirth }
        var age = currentDate.get(Calendar.YEAR) - dob.get(Calendar.YEAR)
        if (currentDate.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--
        }

        return age
    } catch (e: Exception) {
        e.printStackTrace()
        return -1
    }
}

fun getInitials(fullName: String): String {
    val parts = fullName.trim().split("\\s+".toRegex())
    var initials = ""

    for (part in parts) {
        initials += part[0].toUpperCase() + " "
    }
    return initials.trim()
}

fun formatPhoneNumber(phoneNumber: String): String {
    // Check if the phone number starts with "+"
    if (phoneNumber.startsWith("+")) {
        // Extract the country code (e.g., "+91")
        val countryCode = phoneNumber.substring(0, 3)

        // Extract the rest of the number (excluding the country code)
        val restNumber = phoneNumber.substring(3)

        // Concatenate the formatted phone number with a space
        return "$countryCode $restNumber"
    }

    // Return the original phone number if it doesn't start with "+"
    return phoneNumber
}
