package com.im.letmark.ui.cart

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.im.letmark.R
import com.im.letmark.data.local.cart.CartEntity
import com.im.letmark.databinding.FragmentCartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment(), CartItemInterface {

    private var cartBinding: FragmentCartBinding? = null
    private val binding get() = cartBinding!!
    private val cartModel: CartViewModel by activityViewModels()
    private lateinit var cartAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cartAdapter = CartAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        cartBinding = FragmentCartBinding.inflate(inflater, container, false)
        val view = binding.root


        cartModel.cartItems.observe(this.viewLifecycleOwner, { result ->

            result?.let {
                initRecycler()
                cartAdapter.setItems(it)

            }

        })


        binding.nextToAddress.setOnClickListener {


            cartModel.cartItems.observe(this.viewLifecycleOwner, { result ->

                result?.let {

                    if (it.isEmpty()) {
                        Toast.makeText(requireContext(), "empty cart", Toast.LENGTH_LONG).show()
                    } else {
                        findNavController().navigate(R.id.action_cartFragment_to_addressFragment)
                    }

                }

            })


        }


        binding.deleteCart.setOnClickListener {
            cartModel.delete()
        }


        return view
    }

    private fun initRecycler() {
        binding.cartRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        binding.cartRecyclerView.setHasFixedSize(true)
        binding.cartRecyclerView.adapter = cartAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        cartBinding = null
    }

    override fun updateItem(cartEntity: CartEntity) {

        cartModel.update(cartEntity)

        Toast.makeText(requireContext(), "updated", Toast.LENGTH_LONG).show()
    }


}