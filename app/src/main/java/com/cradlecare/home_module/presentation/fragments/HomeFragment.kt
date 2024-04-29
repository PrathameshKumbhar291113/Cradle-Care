package com.cradlecare.home_module.presentation.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cradlecare.databinding.FragmentHomeBinding
import com.cradlecare.kyc_module.presentation.KycActivity
import com.cradlecare.pre_delivery_module.presentation.PreDeliveryActivity
import com.cradlecare.utils.daysUntilDueDate
import com.resq360.utils.BundleConstants
import dagger.hilt.android.AndroidEntryPoint
import splitties.fragments.start

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    private fun setupUi() {
        binding.preDeliveryDetailsCard.btnStartNow.setOnClickListener {
            start<PreDeliveryActivity>()
        }

        val sharePrefPregnancyMonth: SharedPreferences = requireContext().getSharedPreferences(
            BundleConstants.PREGNANCY_MONTH,
            Context.MODE_PRIVATE
        )

        var userExpectedPregnancyDate = sharePrefPregnancyMonth.getString(BundleConstants.USER_PREGNANCY_MONTH, "")

        userExpectedPregnancyDate?.let {
            binding.deliveryDaysLeftCard.estimatedDaysValue.text =   daysUntilDueDate(it).toString()
        }

        binding.kycHomeCard.kycStatusDetailsIcon.setOnClickListener {
            start<KycActivity>()
        }
    }

}