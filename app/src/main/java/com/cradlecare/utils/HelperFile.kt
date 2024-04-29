package com.cradlecare.utils

import android.app.Activity
import android.content.Context
import android.util.Patterns
import android.view.Window
import android.view.inputmethod.InputMethodManager
import com.cradlecare.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


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

