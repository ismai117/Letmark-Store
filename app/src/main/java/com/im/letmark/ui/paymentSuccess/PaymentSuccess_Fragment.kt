package com.im.letmark.ui.paymentSuccess

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.im.letmark.R
import com.im.letmark.databinding.FragmentPaymentSuccessBinding
import com.im.letmark.ui.payment.PaymentFragment


class PaymentSuccess_Fragment : Fragment() {

    private var paymentSuccessbinding: FragmentPaymentSuccessBinding? = null
    private val binding get() = paymentSuccessbinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                isEnabled = true

            }
        })


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        paymentSuccessbinding = FragmentPaymentSuccessBinding.inflate(inflater, container, false)
        val view = binding.root



        binding.backToHome.setOnClickListener {
            findNavController().navigate(R.id.action_paymentSuccess_Fragment_to_itemsFragment)
        }


        return view
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        paymentSuccessbinding = null
    }


}


