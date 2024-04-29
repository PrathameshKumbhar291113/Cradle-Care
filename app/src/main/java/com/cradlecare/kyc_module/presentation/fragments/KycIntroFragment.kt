package com.cradlecare.kyc_module.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cradlecare.databinding.FragmentKycIntroBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KycIntroFragment : Fragment() {
    private lateinit var binding: FragmentKycIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentKycIntroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
    }

    private fun setupUi() {
        /*binding.btnNext.setOnClickListener {
            if (!binding.userAadharCardNumberValue.text.isNullOrEmpty() ||
                !binding.userAadharCardNumberValue.text.isNullOrBlank() &&
                !binding.userPanCardNumberValue.text.isNullOrEmpty() ||
                !binding.userPanCardNumberValue.text.isNullOrBlank()){

                val sharePrefAadharNumber : SharedPreferences = requireContext().getSharedPreferences(
                    BundleConstants.ADHAR_NUM, Context.MODE_PRIVATE)
                var editorAadhar : SharedPreferences.Editor = sharePrefAadharNumber.edit()
                editorAadhar.putString(
                    BundleConstants.USER_ADHAR_NUM,
                    binding.userAadharCardNumberValue.text?.trim().toString())
                editorAadhar.apply()

                val sharePrefPanNum : SharedPreferences = requireContext().getSharedPreferences(
                    BundleConstants.PAN_NUM, Context.MODE_PRIVATE)
                var editorPan : SharedPreferences.Editor = sharePrefPanNum.edit()
                editorPan.putString(
                    BundleConstants.USER_PAN_NUM,
                    binding.userPanCardNumberValue.text?.trim().toString())
                editorPan.apply()

                lifecycleScope.launch {
                    LoaderOverlay.showLoaderAnimationDialog(requireContext())
                    binding.btnVerify.isVisible = true
                    binding.btnNext.isVisible = false

                    delay(3000)

                    binding.btnVerify.isVisible = false
                    binding.btnNext.isVisible = true

                    Toast.makeText(requireContext(), "User Verified Successfully.", Toast.LENGTH_SHORT).show()
                    LoaderOverlay.hideLoaderAnimationDialog()
                }
            }
        }*/

        binding.btnNext.setOnClickListener {

        }
    }
}