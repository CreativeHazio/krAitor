package com.creativehazio.kraitor.ui.screens.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.creativehazio.kraitor.R
import com.creativehazio.kraitor.databinding.ActivityPayNowOrLaterBinding
import com.flutterwave.raveandroid.RavePayActivity
import com.flutterwave.raveandroid.RaveUiManager
import com.flutterwave.raveandroid.rave_java_commons.RaveConstants

class PayNowOrLaterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPayNowOrLaterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPayNowOrLaterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.apply {
            payNowBtn.setOnClickListener {
                makePayment()
            }

            payLaterBtn.background = null
            payLaterBtn.setOnClickListener {
                val intent = Intent(this@PayNowOrLaterActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun makePayment() {

        // TODO  find a way to make recurring payments
        RaveUiManager(this@PayNowOrLaterActivity).setAmount(9.99)
            .setCurrency("USD")
            .setfName("David")
            .setlName("Ezekiel")
            .setEmail("ezekieldavid036@gmail.com")
            .setPublicKey(getString(R.string.flutterwave_public_key))
            .setEncryptionKey(getString(R.string.flutterwave_encryption_key))
            .setTxRef(System.currentTimeMillis().toString() + "Ref")
            .acceptCardPayments(true)
            .shouldDisplayFee(false)
            .onStagingEnv(true)
            .initialize()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == RaveConstants.RAVE_REQUEST_CODE && data != null) {
            val message: String? = data.getStringExtra("response")
            if (resultCode == RavePayActivity.RESULT_SUCCESS) {
                Toast.makeText(this, "SUCCESS $message", Toast.LENGTH_SHORT).show()
            } else if (resultCode == RavePayActivity.RESULT_ERROR) {
                Toast.makeText(this, "ERROR $message", Toast.LENGTH_SHORT).show()
            } else if (resultCode == RavePayActivity.RESULT_CANCELLED) {
                Toast.makeText(this, "CANCELLED $message", Toast.LENGTH_SHORT).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}