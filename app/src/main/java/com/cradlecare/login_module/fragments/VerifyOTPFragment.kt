package com.cradlecare.login_module.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.cradlecare.R
import com.cradlecare.databinding.FragmentVerifyOtpBinding
import com.cradlecare.onboarding_module.OnboardingActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.resq360.utils.hideKeyboard
import splitties.fragments.start

class VerifyOTPFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentVerifyOtpBinding
    private lateinit var verificationId : String

    companion object{
        const val USER_VERIFICATION_ID = "verificationId"
    }

    private val _verificationId: String by lazy {
        arguments?.getString(USER_VERIFICATION_ID) ?: ""
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentVerifyOtpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        auth = FirebaseAuth.getInstance()
        binding.userOtpEditText.doOnTextChanged { text, start, before, count ->
            text?.let {
                if (it.length < 6) {
//                    disableSubmitButton()
                } else if (it.length == 6) {
                    requireActivity().hideKeyboard()
//                    enableSubmitButton()
                }
            }
        }
        binding.btnVerify.setOnClickListener {
            requireActivity().hideKeyboard()
            binding.userOtpEditText.text?.let {
                if (it.isNullOrBlank() || it.isNullOrEmpty()) {
//                    errorToast("OTP Cannot Be Empty!!!")
//                    disableSubmitButton()
                } else if (it.length < 6) {
//                    errorToast("OTP entered is less than 6 Digits!!!")
//                    disableSubmitButton()
                } else {
                    val credential : PhoneAuthCredential = PhoneAuthProvider.getCredential(_verificationId, it.toString().trim())
                    signInWithPhoneAuthCredential(credential)
                }
            }
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential).addOnCompleteListener(requireActivity()) { task ->
            if (task.isSuccessful) {
                //api call to check if existing user or not
                start<OnboardingActivity>()
            } else {
//                errorToast(task.exception?.message.toString())
            }
        }
    }
}