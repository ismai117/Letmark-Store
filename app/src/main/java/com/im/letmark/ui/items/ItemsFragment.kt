package com.im.letmark.ui.items

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.im.letmark.R
import com.im.letmark.databinding.FragmentItemsBinding
import com.im.letmark.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemsFragment : Fragment() {

    private var itemsBinding: FragmentItemsBinding? = null
    private val binding get() = itemsBinding!!
    private val itemModel: ItemsViewModel by viewModels()
    private lateinit var itemsAdapter: ItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemsAdapter = ItemsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        itemsBinding = FragmentItemsBinding.inflate(inflater, container, false)
        val view = binding.root

        itemModel.items.observe(this.viewLifecycleOwner) { result ->


            result?.let {

                if (result.data != null) {

                    initRecycler()
                    itemsAdapter.setItems(result.data)


                    binding.progressBar.isVisible =
                        result is Resource.Loading && result.data.isNullOrEmpty()


                } else {
                    Log.d("player_error", "${result.error?.message}")
                }

            }

        }


        return view
    }

    private fun initRecycler() {
        binding.itemsRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.itemsRecyclerView.setHasFixedSize(true)
        binding.itemsRecyclerView.adapter = itemsAdapter
    }


}