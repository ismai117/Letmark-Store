package com.im.letmark.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.im.letmark.R
import com.im.letmark.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : Fragment() {


    private var detailBinding: FragmentDetailBinding? = null
    private val binding get() = detailBinding!!
    private val detailModel: DetailViewModel by viewModels()
    private var itemName: String? = null
    private var itemImage: String? = null
    private var itemDescription: String? = null
    private var itemCategory: String? = null
    private var itemPrice: String? = null
    private var itemRate: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            val item = DetailFragmentArgs.fromBundle(it).product

            if (item != null){

                itemName = item.title
                itemImage = item.image
                itemDescription = item.description
                itemCategory = item.category
                itemPrice = item.price.toString()
                itemRate = item.rating.rate.toString()

            }

        }
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




        return view
    }


    override fun onStop() {
        super.onStop()
        detailBinding = null
    }


}