package com.cradlecare.auth_module.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.cradlecare.R
import com.cradlecare.databinding.FragmentSignInSignUpBinding
import com.cradlecare.utils.hideKeyboard
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class SignInSignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignInSignUpBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private var _verificationId : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignInSignUpBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setUpUI() {
        auth = FirebaseAuth.getInstance()
        binding.userMobileNumberEditText.doOnTextChanged { text, _, _, _ ->
            text?.let {
                if (it.length < 10) {
                    binding.btnGetOtp.background = resources.getDrawable(R.drawable.shape_layer_button_disabled)
                } else if (it.length == 10) {
                    binding.btnGetOtp.background = resources.getDrawable(R.drawable.shape_layer_button)
                    requireActivity().hideKeyboard()
                }
            }
        }

        binding.btnGetOtp.setOnClickListener {
            requireActivity().hideKeyboard()
            binding.userMobileNumberEditText.text?.let {
                if (it.isNullOrEmpty() || it.isNullOrBlank()) {
                    Toast.makeText(requireContext(), "Mobile Field Cannot Be Empty.", Toast.LENGTH_SHORT).show()
                } else if (it.length < 10) {
                    Toast.makeText(requireContext(), "Mobile No. provided is less than 10 digits.", Toast.LENGTH_SHORT).show()
                } else  {
                    sendOtp("+91${it.toString().trim()}")
                }
            }

        }

        callBackFunctionForPhoneAuth()
    }

    private fun callBackFunctionForPhoneAuth() {
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            }

            override fun onVerificationFailed(e: FirebaseException) {
                if (e is FirebaseAuthInvalidCredentialsException) {
                    Toast.makeText(requireContext(),"Invalid phone number.",Toast.LENGTH_SHORT).show()
                } else if (e is FirebaseTooManyRequestsException) {
//                    errorToast("Quota exceeded.")
                    Toast.makeText(requireContext(),"Quota exceeded.",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                lifecycleScope.launch {
                    Toast.makeText(requireContext(),"OTP Send Successfully!!!",Toast.LENGTH_SHORT).show()
                    delay(1000)
                    _verificationId = verificationId
                    resendToken = token

                    val bundle = bundleOf(
                        VerifyOTPFragment.USER_VERIFICATION_ID to _verificationId
                    )
                    findNavController().navigate(R.id.verifyOTPFragment,bundle)
                }
            }
        }
    }

    private fun sendOtp(mobileNumber: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(mobileNumber) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity()) // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}