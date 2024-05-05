package com.cradlecare.pre_delivery_module.presentation.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.cradlecare.databinding.FragmentSubscriptionSuccessBinding
import com.cradlecare.home_module.presentation.HomeActivity
import com.cradlecare.utils.getMonthlySubscriptionValidityDates
import com.resq360.utils.BundleConstants
import dagger.hilt.android.AndroidEntryPoint
import splitties.fragments.start

@AndroidEntryPoint
class SubscriptionSuccessFragment : Fragment() {
    private lateinit var binding: FragmentSubscriptionSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSubscriptionSuccessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        handleBackPress()
    }

    private fun handleBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun setupUi() {

        val (todayDate, nextMonthDate) = getMonthlySubscriptionValidityDates()
        binding.subscriptionActivatedSubTitle.text = "The Subscription Activated from\n$todayDate to $nextMonthDate"

        binding.btnOkay.setOnClickListener {

            val sharePrefSubscription : SharedPreferences = requireContext().getSharedPreferences(
                BundleConstants.SUBSCRIPTION, Context.MODE_PRIVATE
            )

            var editor : SharedPreferences.Editor = sharePrefSubscription.edit()
            editor.putBoolean(BundleConstants.IS_SUBSCRIPTION_ACTIVATED,true)
            editor.apply()

            start<HomeActivity>()
            requireActivity().finishAffinity()
        }
    }


}