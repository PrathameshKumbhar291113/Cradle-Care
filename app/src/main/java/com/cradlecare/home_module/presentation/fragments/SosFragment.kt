package com.cradlecare.home_module.presentation.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

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
            binding.sosInactiveState.isVisible = false
            binding.sosActiveState.isVisible = true
            sosActivation(binding.sosActiveState.isVisible)
        }
    }

    private fun sosActivation(isVisible: Boolean) {
        if (isVisible) {
            lifecycleScope.launch {

                startLoadingAnimation()

                delay(5000)

                binding.sosEstimatedTimeText.isVisible = true
                binding.sosTimeLeftText.text = "Time Left"

                val twentyMinutesInMillis: Long = 20 * 60 * 1000

                val currentTimeMillis = System.currentTimeMillis()

                // Calculate the estimated time 20 minutes ahead of the current time
                val estimatedFinishTimeMillis = currentTimeMillis + twentyMinutesInMillis
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = estimatedFinishTimeMillis
                val estimatedTime =
                    SimpleDateFormat("hh:mm a", Locale.getDefault()).format(calendar.time)

                object : CountDownTimer(twentyMinutesInMillis, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        // Calculate minutes and seconds from milliseconds
                        val minutes = millisUntilFinished / (60 * 1000)
                        val seconds = (millisUntilFinished % (60 * 1000)) / 1000

                        binding.sosTimeLeftValue.text = "$minutes:$seconds Mins"
                        binding.sosEstimatedTimeText.text =
                            "Estimated ambulance arrival: $estimatedTime"
                    }

                    override fun onFinish() {
                        // Countdown is finished, perform any final action here
                    }
                }.start()

            }
        }
    }

    private fun startLoadingAnimation() {

        val handler = Handler(Looper.getMainLooper())
        var dotsCount = 0

        handler.post(object : Runnable {
            override fun run() {
                // Update the loading text with dots
                binding.sosTimeLeftText.text =
                    "Searching nearby ambulances ${".".repeat(dotsCount)}"
                binding.sosEstimatedTimeText.isVisible = false

                // Increment dotsCount and reset if it exceeds 3
                dotsCount = (dotsCount + 1) % 4

                // Schedule the next iteration after a delay
                handler.postDelayed(this, 500)
            }
        })

        // Stop the animation after 3 seconds
        handler.postDelayed({
            handler.removeCallbacksAndMessages(null) // Stop all callbacks and messages
            // Do any additional cleanup or actions here
        }, 5000)
    }
}