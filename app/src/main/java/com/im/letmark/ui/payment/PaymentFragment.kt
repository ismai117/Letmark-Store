package com.im.letmark.ui.payment

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.im.letmark.R
import com.im.letmark.databinding.FragmentPaymentBinding
import com.im.letmark.domain.model.Order
import com.im.letmark.domain.model.Product
import com.im.letmark.util.Constants.CHANNEL_ID
import com.im.letmark.util.Constants.CHANNEL_NAME
import com.im.letmark.util.Constants.NOTIFICATION_ID
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class PaymentFragment : Fragment() {

    private var paymentBinding: FragmentPaymentBinding? = null
    private val binding get() = paymentBinding!!
    private val paymentModel: PaymentViewModel by viewModels()
    private var checked = false

    private var firstnameValue: String? = null
    private var lastnameValue: String? = null
    private var addressOneValue: String? = null
    private var addressTwoValue: String? = null
    private var cityValue: String? = null
    private var postcodeValue: String? = null
    private var phoneValue: String? = null
    private var countryValue: String? = null
    private var countyValue: String? = null

    private var finalOrder: String? = null
    private var totalPrice: String? = null

    private var customerDetails: SharedPreferences? = null
    private var customerOrders: SharedPreferences? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createNotificationChannel()
        customerDetails =
            requireActivity().getSharedPreferences("shipping_address", Context.MODE_PRIVATE)
        customerOrders =
            requireActivity().getSharedPreferences("customer_orders", Context.MODE_PRIVATE)

        customerDetails?.let {

            val editor = it.edit()


            firstnameValue = it.getString("firstname", null)

            lastnameValue = it.getString("lastname", null)

            addressOneValue = it.getString("addressOne", null)

            addressTwoValue = it.getString("addressTwo", null)

            cityValue = it.getString("city", null)

            postcodeValue = it.getString("postcode", null)

            phoneValue = it.getString("phone", null)

            countryValue = it.getString("country", null)

            countyValue = it.getString("county", null)

            editor.apply()

        }

        customerOrders?.let {

            val editor = it.edit()

            finalOrder = it.getString("order", null)
            totalPrice = it.getString("price", null)

            editor.apply()

        };

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        paymentBinding = FragmentPaymentBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.storeRadioButton.setOnCheckedChangeListener { compoundButton, isChecked ->

            checked = isChecked == true

        }

        binding.buyNowBtn.setOnClickListener {

            when (checked) {

                true -> {

                    saveOrder()
                    successAction()

                }

                false -> {

                    Snackbar.make(binding.paymentFrag,
                        "Please select payment method",
                        Snackbar.LENGTH_LONG).show()

                }

            }

        }

        return view
    }

    private fun successAction() {


        val notification = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
            .setContentTitle("Letmark")
            .setContentText("Letmark order placed successfully")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setAutoCancel(false)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()


        val notificationManager = NotificationManagerCompat.from(requireContext())

        notificationManager.notify(NOTIFICATION_ID, notification)

        paymentModel.deleteAll()

        findNavController().navigate(R.id.action_paymentFragment_to_paymentSuccess_Fragment)

    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                lightColor = Color.GREEN
                enableLights(true)
            }

            val notificationManager = requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)

        }

    }

    private fun saveOrder() {


        val address =
            "$addressOneValue\n,$postcodeValue\n,$cityValue\n,$countyValue\n,$countryValue"

        val order = Order(
            id = 0,
            firstname = firstnameValue ?: "null",
            lastname = lastnameValue ?: "null",
            phone = phoneValue ?: "null",
            address = address,
            orderID = UUID.randomUUID().toString(),
            customerOrder = finalOrder ?: "null",
            price = totalPrice.toString().toDouble(),
        )

        paymentModel.saveCustomerOrder(order)

    }


    override fun onDestroy() {
        super.onDestroy()
        paymentBinding = null
    }

}