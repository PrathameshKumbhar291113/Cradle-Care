package com.cradlecare.home_module.presentation.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.cradlecare.databinding.FragmentHomeBinding
import com.cradlecare.home_module.presentation.adapter.PregnancyTipsAdapter
import com.cradlecare.kyc_module.presentation.KycActivity
import com.cradlecare.pre_delivery_module.presentation.PreDeliveryActivity
import com.cradlecare.utils.PregnancyTips
import com.cradlecare.utils.daysUntilDueDate
import com.resq360.utils.BundleConstants
import dagger.hilt.android.AndroidEntryPoint
import splitties.fragments.start

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var pregnancyTipsAdapter: PregnancyTipsAdapter

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
        setupTips()
    }

    private fun setupTips() {
        var listOfTips: ArrayList<PregnancyTips> = arrayListOf(
            PregnancyTips(
                "Nutritious Diet",
                "Fuel your baby's growth with a balanced diet rich in vitamins and minerals."
            ),
            PregnancyTips(
                "Prenatal Vitamins",
                "Ensure optimal nutrition with prenatal vitamins recommended by your doctor."
            ),
            PregnancyTips(
                "Regular Prenatal Visits",
                "Monitor your baby's growth and well-being through regular check-ups."
            ),
            PregnancyTips(
                "Stay Hydrated",
                "Drink plenty of water throughout the day to support healthy circulation and amniotic fluid levels."
            )
        )

        pregnancyTipsAdapter = PregnancyTipsAdapter(listOfTips)
        binding.tipsRecyclerView.adapter = pregnancyTipsAdapter
    }

    private fun setupUi() {
        binding.preDeliveryDetailsCard.btnStartNow.setOnClickListener {
            start<PreDeliveryActivity>()
        }

        val sharePrefPregnancyMonth: SharedPreferences = requireContext().getSharedPreferences(
            BundleConstants.PREGNANCY_MONTH,
            Context.MODE_PRIVATE
        )

        var userExpectedPregnancyDate =
            sharePrefPregnancyMonth.getString(BundleConstants.USER_PREGNANCY_MONTH, "")


        userExpectedPregnancyDate?.let {
            binding.deliveryDaysLeftCard.estimatedDaysValue.text = daysUntilDueDate(it).toString()
        }

        binding.kycHomeCard.kycStatusDetailsIcon.setOnClickListener {
            start<KycActivity>()
        }

        val sharePrefSubscription: SharedPreferences = requireContext().getSharedPreferences(
            BundleConstants.SUBSCRIPTION,
            Context.MODE_PRIVATE
        )

        val isSubscriptionActive =
            sharePrefSubscription.getBoolean(BundleConstants.IS_SUBSCRIPTION_ACTIVATED, false)

        if (isSubscriptionActive) {
            binding.preDeliveryDetailsCard.preDeliveryCardAfterSubscription.isVisible = true
            binding.preDeliveryDetailsCard.preDeliveryCard.isVisible = false
        } else {
            binding.preDeliveryDetailsCard.preDeliveryCardAfterSubscription.isVisible = false
            binding.preDeliveryDetailsCard.preDeliveryCard.isVisible = true
        }
    }


}