package com.cradlecare.pre_delivery_module.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cradlecare.databinding.ActivitySubscriptionCheckoutBinding
import com.cradlecare.utils.appStatusBarColor
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import com.resq360.utils.NavigationConstants.IS_PAYMENT_SUCCESSFUL
import com.resq360.utils.NavigationConstants.PAYMENT_TRANSACTION_ID
import com.resq360.utils.RazorPayConstants
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONException
import org.json.JSONObject
import splitties.activities.start


@AndroidEntryPoint
class SubscriptionCheckoutActivity : AppCompatActivity(), /*PaymentResultWithDataListener,*/
    PaymentResultListener {
    private lateinit var binding: ActivitySubscriptionCheckoutBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubscriptionCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRazorPayGateway()
    }

    private fun setupRazorPayGateway() {
        appStatusBarColor(this, window)
        binding.btnPayout.setOnClickListener {
            startPayment()
        }
    }

    private fun startPayment() {
        val co = Checkout()
        co.setKeyID(RazorPayConstants.TEST_KEY_ID)
//        co.setKeyID(RazorPayConstants.LIVE_KEY_ID)

        try {
            val options = JSONObject()
            options.put("name", "Cradle Care")
            options.put("description", "Motherhood Prime")
            options.put("theme.color", "#fb7185")
            options.put("currency", "INR")
            options.put("amount", Math.round(2999.toFloat() * 100))
            options.put("prefill.contact", "9082379158")
            options.put("prefill.email", "prathameshkumbhar291113@gmail.com")
            co.open(this@SubscriptionCheckoutActivity, options)
        } catch (e: JSONException) {
            Toast.makeText(this@SubscriptionCheckoutActivity, "Error in payment: ${e.message}", Toast.LENGTH_LONG)
                .show()
            Log.e("Payment", "startPayment: ${e.message}", )
            e.printStackTrace()
        }
    }

    override fun onPaymentSuccess(paymentTransactionId: String?) {
        Log.e("Payment", "onPaymentSuccess: $paymentTransactionId")
        Toast.makeText(this@SubscriptionCheckoutActivity, "Payment successful", Toast.LENGTH_SHORT).show()

        start<PreDeliveryActivity> {
            this.putExtra(IS_PAYMENT_SUCCESSFUL, true)
            this.putExtra(PAYMENT_TRANSACTION_ID, paymentTransactionId)
        }

        finish()
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        Log.e("Payment", "onPaymentError: $p0 ---------- $p1")
        Toast.makeText(this@SubscriptionCheckoutActivity, "Payment error", Toast.LENGTH_SHORT).show()
    }

}