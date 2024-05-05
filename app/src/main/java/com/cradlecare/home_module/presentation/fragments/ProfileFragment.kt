package com.cradlecare.home_module.presentation.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.cradlecare.SplashActivity
import com.cradlecare.databinding.FragmentProfileBinding
import com.cradlecare.utils.calculateAge
import com.cradlecare.utils.formatPhoneNumber
import com.cradlecare.utils.getInitials
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.resq360.utils.BundleConstants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import splitties.fragments.start

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var fireBaseCurrentUser: FirebaseUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.currentUser?.let {
            fireBaseCurrentUser = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logout()
        setUpUi()
    }

    private fun setUpUi() {
        setupUserDetails()
    }

    private fun setupUserDetails() {
        binding.incUserProfileDetails.userMobileNumber.text = formatPhoneNumber( fireBaseCurrentUser.phoneNumber.toString())

        val sharePrefFullName : SharedPreferences = requireContext().getSharedPreferences(BundleConstants.NAME, Context.MODE_PRIVATE)
        var userName = sharePrefFullName.getString(BundleConstants.USER_FULL_NAME, "")

        binding.incUserProfileDetails.userFullName.text = userName.toString()


        val sharePrefDob : SharedPreferences = requireContext().getSharedPreferences(BundleConstants.DOB, Context.MODE_PRIVATE)
        var userDob = sharePrefDob.getString(BundleConstants.USER_DOB, "")


        binding.incUserProfileDetails.userAge.text = calculateAge(userDob.toString()).toString()

        binding.incUserProfileDetails.userNameShort.text = getInitials(userName.toString())
    }

    private fun logout() {
        binding.userLogoutContainer.setOnClickListener {
            firebaseAuth.signOut()
            //call update isLoggedInFlag from backend with api call..
            lifecycleScope.launch(){
                delay(1000)
                start<SplashActivity>(){
                    val sharePrefLogin : SharedPreferences = requireContext().getSharedPreferences(
                        BundleConstants.LOGIN, Context.MODE_PRIVATE)
                    var editor : SharedPreferences.Editor = sharePrefLogin.edit()
                    editor.putBoolean(BundleConstants.IS_LOGIN_COMPLETED,false)
                    editor.apply()

                    val sharePrefOnboarding: SharedPreferences = requireContext().getSharedPreferences(
                        BundleConstants.ONBOARDING, Context.MODE_PRIVATE
                    )
                    var editorOnboarding: SharedPreferences.Editor = sharePrefOnboarding.edit()
                    editorOnboarding.putBoolean(
                        BundleConstants.IS_ONBOARDING_COMPLETED, false
                    )
                    editorOnboarding.apply()

                    activity?.finish()
                }
            }
        }
    }

}