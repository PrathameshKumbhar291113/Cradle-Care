package com.cradlecare.onboarding_module.presentation.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cradlecare.databinding.FragmentPregnancyDetailsBinding
import com.cradlecare.home_module.presentation.HomeActivity
import com.resq360.utils.BundleConstants
import dagger.hilt.android.AndroidEntryPoint
import splitties.fragments.start

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
        binding.btnSubmit.setOnClickListener {
            val sharePrefPregnancyMonth : SharedPreferences = requireContext().getSharedPreferences(
                BundleConstants.PREGNANCY_MONTH, Context.MODE_PRIVATE)
            var editorPregnancyMonth : SharedPreferences.Editor = sharePrefPregnancyMonth.edit()
            editorPregnancyMonth.putString(
                BundleConstants.USER_PREGNANCY_MONTH,
                binding.userPregnancyMonthValue.text?.trim().toString())
            editorPregnancyMonth.apply()

            val sharePrefBloodGroup : SharedPreferences = requireContext().getSharedPreferences(
                BundleConstants.BLOOD_GROUP, Context.MODE_PRIVATE)
            var editorBloodGroup : SharedPreferences.Editor = sharePrefBloodGroup.edit()
            editorBloodGroup.putString(
                BundleConstants.USER_BLOOD_GROUP,
                binding.userBloodGroupValue.text?.trim().toString())
            editorBloodGroup.apply()

            val sharePrefOnboarding : SharedPreferences = requireContext().getSharedPreferences(
                BundleConstants.ONBOARDING, Context.MODE_PRIVATE)
            var editorOnboarding : SharedPreferences.Editor = sharePrefOnboarding.edit()
            editorOnboarding.putBoolean(
                BundleConstants.IS_ONBOARDING_COMPLETED, true
            )
            editorOnboarding.apply()

            start<HomeActivity>()
        }
    }
}