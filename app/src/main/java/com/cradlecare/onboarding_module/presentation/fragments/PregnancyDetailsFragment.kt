package com.cradlecare.onboarding_module.presentation.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.cradlecare.R
import com.cradlecare.databinding.FragmentPregnancyDetailsBinding
import com.cradlecare.home_module.presentation.HomeActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.resq360.utils.BundleConstants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import splitties.fragments.start
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@AndroidEntryPoint
class PregnancyDetailsFragment : Fragment() {
    private lateinit var binding: FragmentPregnancyDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPregnancyDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    private fun setupUi() {

        binding.userPregnancyMonthValue.setOnClickListener {
            showDatePicker()
        }

        binding.btnSubmit.setOnClickListener {
            val sharePrefPregnancyMonth: SharedPreferences = requireContext().getSharedPreferences(
                BundleConstants.PREGNANCY_MONTH, Context.MODE_PRIVATE
            )
            var editorPregnancyMonth: SharedPreferences.Editor = sharePrefPregnancyMonth.edit()
            editorPregnancyMonth.putString(
                BundleConstants.USER_PREGNANCY_MONTH,
                binding.userPregnancyMonthValue.text?.trim().toString()
            )
            editorPregnancyMonth.apply()

            val sharePrefBloodGroup: SharedPreferences = requireContext().getSharedPreferences(
                BundleConstants.BLOOD_GROUP, Context.MODE_PRIVATE
            )
            var editorBloodGroup: SharedPreferences.Editor = sharePrefBloodGroup.edit()
            editorBloodGroup.putString(
                BundleConstants.USER_BLOOD_GROUP,
                binding.userBloodGroupValue.text?.trim().toString()
            )
            editorBloodGroup.apply()

            val sharePrefOnboarding: SharedPreferences = requireContext().getSharedPreferences(
                BundleConstants.ONBOARDING, Context.MODE_PRIVATE
            )
            var editorOnboarding: SharedPreferences.Editor = sharePrefOnboarding.edit()
            editorOnboarding.putBoolean(
                BundleConstants.IS_ONBOARDING_COMPLETED, true
            )
            editorOnboarding.apply()

            lifecycleScope.launch {
                delay(500)
                requireActivity().finish()
                start<HomeActivity>()
            }
        }
    }

    private fun showDatePicker() {
        lifecycleScope.launch {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date of Birth")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .setTheme(R.style.ThemeMaterialCalendar)
                .build()


            datePicker.show(childFragmentManager, "DATE_PICKER")
            datePicker.isCancelable = false

            datePicker.addOnPositiveButtonClickListener { selectedDate ->
                val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(Date(selectedDate))

                binding.userPregnancyMonthValue.setText(formattedDate.toString())
            }

            datePicker.addOnNegativeButtonClickListener {
                datePicker.dismiss()
            }
        }
    }
}