package com.cradlecare.onboarding_module.presentation.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cradlecare.R
import com.cradlecare.databinding.FragmentFullNameBinding
import com.resq360.utils.BundleConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FullNameFragment : Fragment() {

    private lateinit var binding: FragmentFullNameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFullNameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
    }

    private fun setUpUi() {

        binding.btnNext.setOnClickListener {
            if (!binding.userFullNameValue.text.isNullOrBlank() || !binding.userFullNameValue.text.isNullOrEmpty()) {
                val sharePrefFullName : SharedPreferences = requireContext().getSharedPreferences(BundleConstants.NAME, Context.MODE_PRIVATE)
                var editorFullName : SharedPreferences.Editor = sharePrefFullName.edit()
                editorFullName.putString(BundleConstants.USER_FULL_NAME,
                    binding.userFullNameValue.text?.trim().toString())
                editorFullName.apply()
                findNavController().navigate(R.id.dobFragment)
            }
        }
    }
}