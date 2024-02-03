package com.cradlecare.onboarding_module.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cradlecare.R
import com.cradlecare.databinding.FragmentDobBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.resq360.utils.BundleConstants
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DobFragment : Fragment() {
    private lateinit var binding: FragmentDobBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDobBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    private fun setupUi() {

        binding.userDobValue.setOnClickListener {
            showDatePicker()
        }

        binding.btnNext.setOnClickListener {
            if (!binding.userDobValue.text.isNullOrBlank() || !binding.userDobValue.text.isNullOrBlank()) {
                val sharePrefDob : SharedPreferences = requireContext().getSharedPreferences(
                    BundleConstants.DOB, Context.MODE_PRIVATE)
                var editorDob : SharedPreferences.Editor = sharePrefDob.edit()
                editorDob.putString(
                    BundleConstants.USER_DOB,
                    binding.userDobValue.text?.trim().toString())
                editorDob.apply()

                findNavController().navigate(R.id.addressFragment)
            }
        }
    }

    private fun showDatePicker() {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select Date of Birth")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .setTheme(R.style.ThemeMaterialCalendar)
            .build()


        datePicker.show(childFragmentManager, "DATE_PICKER")
        datePicker.isCancelable = false

        datePicker.addOnPositiveButtonClickListener { selectedDate ->
            // Convert the selected date to a formatted string
            val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
            val formattedDate = dateFormat.format(Date(selectedDate))

            // Set the formatted date to the TextView
            binding.userDobValue.setText(formattedDate.toString())
        }

        datePicker.addOnNegativeButtonClickListener {
            datePicker.dismiss()
        }
    }
}