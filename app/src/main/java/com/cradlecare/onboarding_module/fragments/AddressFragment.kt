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
import com.cradlecare.databinding.FragmentAddressBinding
import com.resq360.utils.BundleConstants

class AddressFragment : Fragment() {

    private lateinit var binding: FragmentAddressBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
    }

    private fun setUpUi() {
        binding.btnNext.setOnClickListener {
            if (!binding.userPinCodeValue.text.isNullOrEmpty() || !binding.userPinCodeValue.text.isNullOrBlank()) {
                val sharePrefAddress: SharedPreferences = requireContext().getSharedPreferences(
                    BundleConstants.PINCODE, Context.MODE_PRIVATE
                )
                var editorAddress: SharedPreferences.Editor = sharePrefAddress.edit()
                editorAddress.putString(
                    BundleConstants.USER_PINCODE,
                    binding.userPinCodeValue.text?.trim().toString()
                )
                editorAddress.apply()
                findNavController().navigate(R.id.kycIntroFragment)
            }
        }
    }
}