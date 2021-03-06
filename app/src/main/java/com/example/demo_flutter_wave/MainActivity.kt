package com.example.demo_flutter_wave

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.flutterwave.raveandroid.RaveUiManager
import com.google.android.material.button.MaterialButton
import android.widget.Toast

import com.flutterwave.raveandroid.RavePayActivity

import com.flutterwave.raveandroid.rave_java_commons.RaveConstants

import android.content.Intent
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView


class MainActivity : AppCompatActivity() {

  private val tvMessage by lazy {
    findViewById<MaterialTextView>(R.id.tvMessage)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val edAmount = findViewById<TextInputEditText>(R.id.edAmout)
    findViewById<MaterialButton>(R.id.btnPayment).setOnClickListener {
      val amount = edAmount.text?.trim().toString().toDoubleOrNull() ?: return@setOnClickListener
      RaveUiManager(this).setAmount(amount)
        .setCurrency("USD")
        .setEmail("ericampire.top@gmail.com")
        .setfName("Eric")
        .setlName("Ampire")
        .setPublicKey("FLWPUBK-c448347a08ff700607b91bb695a6a389-X")
        .setEncryptionKey("6e583287c1002cb9bf363394")
        .setPhoneNumber("+243999052349", true)
        .acceptAccountPayments(true)
        .acceptCardPayments(true)
        .acceptMpesaPayments(true)
        .acceptAchPayments(true)
        .acceptGHMobileMoneyPayments(true)
        .acceptUgMobileMoneyPayments(true)
        .acceptZmMobileMoneyPayments(true)
        .acceptRwfMobileMoneyPayments(true)
        .acceptSaBankPayments(true)
        .acceptUkPayments(true)
        .setTxRef("tax")
        .acceptBankTransferPayments(true)
        .acceptUssdPayments(true)
        .acceptBarterPayments(true)
        .allowSaveCardFeature(true)
        .onStagingEnv(false)
        .isPreAuth(true)
        .shouldDisplayFee(true)
        .showStagingLabel(false)
        .initialize()
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    if (requestCode == RaveConstants.RAVE_REQUEST_CODE && data != null) {
      val message = data.getStringExtra("response")
      when (resultCode) {
        RavePayActivity.RESULT_SUCCESS -> {
          tvMessage.text = "SUCCESS $message"
          Toast.makeText(this, "SUCCESS $message", Toast.LENGTH_SHORT).show()
        }
        RavePayActivity.RESULT_ERROR -> {
          tvMessage.text = "ERROR $message"
          Toast.makeText(this, "ERROR $message", Toast.LENGTH_SHORT).show()
        }
        RavePayActivity.RESULT_CANCELLED -> {
          tvMessage.text = "CANCELLED $message"
          Toast.makeText(this, "CANCELLED $message", Toast.LENGTH_SHORT).show()
        }
      }
    } else {
      super.onActivityResult(requestCode, resultCode, data)
    }
  }
}