package com.im.letmark.ui.detail

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.im.letmark.R
import com.im.letmark.data.local.cart.CartEntity
import com.im.letmark.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DetailFragment : Fragment() {


    private var detailBinding: FragmentDetailBinding? = null
    private val binding get() = detailBinding!!
    private val detailModel: DetailViewModel by viewModels()
    private var itemID: Int? = null
    private var itemName: String? = null
    private var itemImage: String? = null
    private var itemDescription: String? = null
    private var itemCategory: String? = null
    private var itemPrice: String? = null
    private var itemRate: String? = null
    private var quantity: Int = 0
    private var itemSaved: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {

            val item = DetailFragmentArgs.fromBundle(it).product

            if (item != null) {

                itemID = item.id
                itemName = item.title
                itemImage = item.image
                itemDescription = item.description
                itemCategory = item.category
                itemPrice = item.price.toString()
                itemRate = item.rating.rate.toString()

            }

        }


        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailBinding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.detailItemName.text = itemName
        binding.detailItemDescription.text = itemDescription
        binding.detailItemPrice.text = "£$itemPrice"

        Glide.with(requireContext()).load(itemImage).into(binding.detailItemImage)

        val item = CartEntity(
            id = itemID!!,
            title = itemName!!,
            image = itemImage!!,
            category = itemCategory!!,
            price = itemPrice!!.toDouble(),
            quantity = quantity + 1
        )

        binding.addToCartBtn.setOnClickListener {


            detailModel.insert(item)

        }


        return view
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.cart_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.cart -> {

                findNavController().navigate(R.id.action_detailFragment_to_cartFragment)
                return true
            }

        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        detailBinding = null
    }


}