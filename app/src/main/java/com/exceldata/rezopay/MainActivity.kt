package com.exceldata.rezopay
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONException
import org.json.JSONObject


class MainActivity : AppCompatActivity(), PaymentResultListener {
    lateinit var  button:Button
    lateinit var texT:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        texT = findViewById(R.id.text)
        button = findViewById(R.id.button)
        button.setOnClickListener(View.OnClickListener {
            val amountS: String = texT.text.toString()
            // rounding off the amount.
            // rounding off the amount.
            val amount = Math.round(amountS.toFloat() * 100).toInt()

            // initialize Razorpay account.

            // initialize Razorpay account.
            val checkout = Checkout()

            // set your id as below

            // set your id as below
            checkout.setKeyID("rzp_test_5yVPSJGsL5KjIv")

            // set image

            // set image
            checkout.setImage(R.drawable.common_full_open_on_phone)

            // initialize json object
            Checkout.preload(applicationContext);
            // initialize json object
            val ob = JSONObject()
            try {
                // to put name
                ob.put("name", "Example")

                // put description
                ob.put("description", "Test payment")

                // to set theme color
                ob.put("theme.color", "")

                // put the currency
                ob.put("currency", "INR")

                // put amount
                ob.put("amount", amount)

                // put mobile number
                ob.put("prefill.contact", "8219416633")

                // put email
                ob.put("prefill.email", "kamlesh6633ak@gmail.com")

                // open razorpay to checkout activity
                checkout.open(this@MainActivity, ob)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        })
    }

    override fun onPaymentSuccess(p0: String?) {
        Toast.makeText(this, "Payment is successful : " + p0, Toast.LENGTH_SHORT).show();
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        Toast.makeText(this, "Payment Failed due to error : " + p1, Toast.LENGTH_SHORT).show();
    }
}