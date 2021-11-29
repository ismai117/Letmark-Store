package com.im.letmark.ui.checkout

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.im.letmark.R
import com.im.letmark.databinding.FragmentCheckoutBinding
import com.im.letmark.ui.cart.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckoutFragment : Fragment() {


    private var checkoutBinding: FragmentCheckoutBinding? = null
    private val binding get() = checkoutBinding!!
    private var customerDetails: SharedPreferences? = null
    private var customerOrders: SharedPreferences? = null

    private lateinit var checkoutAdapter: CheckoutAdapter
    private val cartModel: CartViewModel by activityViewModels()
    private var total = 00.00

   private var order: String? = null
   private var price: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        customerDetails =
            requireActivity().getSharedPreferences("shipping_address", Context.MODE_PRIVATE)
        customerOrders =
            requireActivity().getSharedPreferences("customer_orders", Context.MODE_PRIVATE)
        checkoutAdapter = CheckoutAdapter()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        checkoutBinding = FragmentCheckoutBinding.inflate(inflater, container, false)
        val view = binding.root

        val name = binding.checkoutName
        val phone = binding.checkoutPhone
        val address = binding.checkoutAddress


        customerDetails?.let {

            val editor = it.edit()

            val firstnameValue = it.getString("firstname", null)
            val lastnameValue = it.getString("lastname", null)
            val addressOneValue = it.getString("addressOne", null)
            val addressTwoValue = it.getString("addressTwo", null)
            val cityValue = it.getString("city", null)
            val postcodeValue = it.getString("postcode", null)
            val phoneValue = it.getString("phone", null)
            val countryValue = it.getString("country", null)
            val countyValue = it.getString("county", null)

            editor.apply()

            name.text = "$firstnameValue $lastnameValue"
            phone.text = "$phoneValue"
            address.text =
                "$addressOneValue, $postcodeValue\n$cityValue,$countyValue\n$countryValue"


        }


        cartModel.cartItems.observe(this.viewLifecycleOwner, { result ->


            result?.let {

                initRecycler()
                checkoutAdapter.setItem(result)


               result.forEach {
                   total = total.plus(it.price)

                   binding.cartTotal.text = "TOTAL: £${total}"

                   price = "$total"
                   Log.d("order", "$order")

               }

                order = result.joinToString {
                    "\'${it.title}\'"
                }

            }



        })



        binding.editAddressBtn.setOnClickListener {
            findNavController().navigate(R.id.action_checkoutFragment_to_addressFragment)
        }

        binding.editOrderBtn.setOnClickListener {
            findNavController().navigate(R.id.action_checkoutFragment_to_cartFragment)
        }

        binding.nextToPayment.setOnClickListener {

            customerOrders?.let {

                val edit = it.edit()

                edit.putString("order", order)
                edit.putString("price", price)
                edit.apply()

            }

            findNavController().navigate(R.id.action_checkoutFragment_to_paymentFragment)
        }

        return view
    }

    private fun initRecycler() {
        binding.orderItemsRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        binding.orderItemsRecyclerView.setHasFixedSize(true)
        binding.orderItemsRecyclerView.adapter = checkoutAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        checkoutBinding = null
    }

}
