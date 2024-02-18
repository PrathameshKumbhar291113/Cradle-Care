package com.cradlecare.home_module.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.cradlecare.databinding.FragmentSosBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SosFragment : Fragment() {
    private lateinit var binding: FragmentSosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpUi()

        setUpObserver()
    }

    private fun setUpObserver() {

    }

    private fun setUpUi() {
        binding.innerCircle.setOnClickListener {
            lifecycleScope.launch {
                delay(1000)
                binding.sosInactiveState.isVisible = false
                binding.sosActiveState.isVisible = true
            }
        }
    }

}