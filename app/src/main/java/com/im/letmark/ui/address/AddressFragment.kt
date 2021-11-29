package com.im.letmark.ui.address

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.im.letmark.R
import com.im.letmark.databinding.FragmentAddressBinding
import com.im.letmark.util.getItemNameSelected
import dagger.hilt.android.AndroidEntryPoint
import org.w3c.dom.Text

@AndroidEntryPoint
class AddressFragment : Fragment() {

    private var addressBinding: FragmentAddressBinding? = null
    private val binding get() = addressBinding!!
    private var sharedPref: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = requireActivity().getSharedPreferences("shipping_address", Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        addressBinding = FragmentAddressBinding.inflate(inflater, container, false)
        val view = binding.root



        binding.nextToCheckOut.setOnClickListener {

            val firstname = binding.firstnameInput.text.toString()
            val lastname = binding.lastnameInput.text.toString()
            val addressOne = binding.addressOneInput.text.toString()
            val addressTwo = binding.addressTwoInput.text.toString()
            val city = binding.cityInput.text.toString()
            val postcode = binding.postcodeInput.text.toString()
            val phone = binding.phoneInput.text.toString()
            val country = binding.countryInput.text.toString()
            val county = binding.countyInput.text.toString()


            when {

                TextUtils.isEmpty(firstname) && TextUtils.isEmpty(lastname) && TextUtils.isEmpty(
                    addressOne) && TextUtils.isEmpty(city) && TextUtils.isEmpty(postcode) && TextUtils.isEmpty(
                    phone) && TextUtils.isEmpty(country) && TextUtils.isEmpty(county) -> {

                    binding.firstnameInput.error = "Don't leave field empty"
                    binding.lastnameInput.error = "Don't leave field empty"
                    binding.addressOneInput.error = "Don't leave field empty"
                    binding.cityInput.error = "Don't leave field empty"
                    binding.postcodeInput.error = "Don't leave field empty"
                    binding.phoneInput.error = "Don't leave field empty"
                    binding.countryInput.error = "Don't leave field empty"
                    binding.countyInput.error = "Don't leave field empty"

                }

                TextUtils.isEmpty(firstname) -> {

                    binding.firstnameInput.error = "Don't leave firstname field empty"
                }


                TextUtils.isEmpty(lastname) -> {

                    binding.lastnameInput.error = "Don't leave lastname field empty"
                }

                TextUtils.isEmpty(addressOne) -> {

                    binding.addressOneInput.error = "Don't leave address field empty"
                }

                TextUtils.isEmpty(city) -> {

                    binding.cityInput.error = "Don't leave city field empty"
                }


                TextUtils.isEmpty(postcode) -> {

                    binding.postcodeInput.error = "Don't leave postcode field empty"
                }

                TextUtils.isEmpty(phone) -> {
                    binding.phoneInput.error = "Don't leave phone field empty"

                }

                TextUtils.isEmpty(country) -> {
                    binding.countryInput.error = "Don't leave country field empty"

                }

                TextUtils.isEmpty(county) -> {

                    binding.countyInput.error = "Don't leave county field empty"
                }

                else -> {


                    sharedPref?.let {

                        val editor = it.edit()

                        editor.putString("firstname", firstname)
                        editor.putString("lastname", lastname)
                        editor.putString("addressOne", addressOne)
                        editor.putString("addressTwo", addressTwo)
                        editor.putString("city", city)
                        editor.putString("postcode", postcode)
                        editor.putString("phone", phone)
                        editor.putString("country", country)
                        editor.putString("county", county)

                        editor.apply()

                        Log.d("shipping_address", "shipping address added")

                        findNavController().navigate(R.id.action_addressFragment_to_checkoutFragment)

                    }


                }

            }


        }


        binding.firstnameInput.setText(sharedPref?.getString("firstname", null))
        binding.lastnameInput.setText(sharedPref?.getString("lastname", null))
        binding.addressOneInput.setText(sharedPref?.getString("addressOne", null))
        binding.addressTwoInput.setText(sharedPref?.getString("addressTwo", null))
        binding.cityInput.setText(sharedPref?.getString("city", null))
        binding.postcodeInput.setText(sharedPref?.getString("postcode", null))
        binding.phoneInput.setText(sharedPref?.getString("phone", null))
        binding.countryInput.setText(sharedPref?.getString("country", null))
        binding.countyInput.setText(sharedPref?.getString("county", null))


        return view
    }



    override fun onDestroy() {
        super.onDestroy()
        addressBinding = null
    }

}