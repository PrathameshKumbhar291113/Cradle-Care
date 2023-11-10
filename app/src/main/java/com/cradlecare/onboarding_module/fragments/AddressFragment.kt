package com.cradlecare.onboarding_module.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cradlecare.R
import com.cradlecare.databinding.FragmentAddressBinding

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
}