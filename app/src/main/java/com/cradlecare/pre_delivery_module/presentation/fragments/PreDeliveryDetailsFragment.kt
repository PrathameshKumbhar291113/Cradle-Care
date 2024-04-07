package com.cradlecare.pre_delivery_module.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cradlecare.R
import com.cradlecare.databinding.FragmentPreDeliveryDetailsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PreDeliveryDetailsFragment : Fragment() {
    private lateinit var binding: FragmentPreDeliveryDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPreDeliveryDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    private fun setupUi() {
        binding.btnCheckout.setOnClickListener {
            findNavController().navigate(R.id.subscriptionCheckoutActivity)
        }
    }

}