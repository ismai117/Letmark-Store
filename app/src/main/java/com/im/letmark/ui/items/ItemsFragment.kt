package com.im.letmark.ui.items

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.im.letmark.R
import com.im.letmark.databinding.FragmentItemsBinding
import com.im.letmark.util.Resource
import com.im.letmark.util.onQueryTextChanged
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemsFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var itemsBinding: FragmentItemsBinding? = null
    private val binding get() = itemsBinding!!
    private val itemModel: ItemsViewModel by viewModels()
    private lateinit var itemsAdapter: ItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemsAdapter = ItemsAdapter()
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        itemsBinding = FragmentItemsBinding.inflate(inflater, container, false)
        val view = binding.root

        itemModel.items.observe(this.viewLifecycleOwner) { result ->

            result?.let {

                if (result.data != null) {

                    initRecycler()
                    itemsAdapter.setItems(result.data)


                    binding.itemProgressBar.isVisible =
                        result is Resource.Loading && result.data.isNullOrEmpty()


                } else {
                    Log.d("item_error", "${result.error?.message}")
                }

            } ?: Snackbar.make(binding.itemCoordinatorLayout, "${result.error?.message}", Snackbar.LENGTH_LONG).show()

        }



        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.sort_array,
            android.R.layout.simple_spinner_dropdown_item
        ).also { adapter ->

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            binding.sortSpinner.adapter = adapter

        }


        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.category_array,
            android.R.layout.simple_spinner_dropdown_item
        ).also { adapter ->

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            binding.filterSpinner.adapter = adapter

        }


        binding.sortSpinner.onItemSelectedListener = this

        binding.filterSpinner.onItemSelectedListener = this



        return view
    }

    private fun initRecycler() {
        binding.itemsRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.itemsRecyclerView.setHasFixedSize(true)
        binding.itemsRecyclerView.adapter = itemsAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.cart_menu, menu)


        val search = menu.findItem(R.id.search)
        val searchView = search.actionView as? SearchView

        searchView?.onQueryTextChanged{
            getItem(it)
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun getItem(it: String) {

        val item = "%$it%"

        itemModel.itemsByTitle(item).observe(this.viewLifecycleOwner, { result ->

            result?.let {
                itemsAdapter.setItems(result)
            }

        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.cart -> {
                findNavController().navigate(R.id.action_itemsFragment_to_cartFragment)
                return true
            }

        }

        return super.onOptionsItemSelected(item)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, p2: Int, p3: Long) {


        parent?.let {

            when (it.selectedItem.toString()) {

                "Sort" -> {

                    itemModel.items.observe(this.viewLifecycleOwner, { result ->

                        result?.let {

                            if (result.data != null) {

                                itemsAdapter.setItems(result.data)

                            }

                        }

                    })

                }

                "Name (A to Z)" -> {

                    itemModel.itemsNameByAsc.observe(this.viewLifecycleOwner, { result ->

                        result?.let {
                            itemsAdapter.setItems(result)
                        }

                    })

                }

                "Name (Z TO A)" -> {

                    itemModel.itemsNameByDesc.observe(this.viewLifecycleOwner, { result ->

                        result?.let {
                            itemsAdapter.setItems(result)
                        }

                    })

                }

                "Price (Low to High)" -> {

                    itemModel.itemsPriceByAsc.observe(this.viewLifecycleOwner, { result ->

                        result?.let {
                            itemsAdapter.setItems(result)
                        }

                    })

                }

                "Price (High to Low)" -> {

                    itemModel.itemsPriceByDesc.observe(this.viewLifecycleOwner, { result ->

                        result?.let {
                            itemsAdapter.setItems(result)
                        }

                    })

                }

                "Category" -> {

                    itemModel.items.observe(this.viewLifecycleOwner, { result ->

                        result?.let {

                            if (result.data != null) {

                                itemsAdapter.setItems(result.data)

                            }

                        }

                    })

                }

                "men's clothing" -> {

                    itemModel.itemsByCategory(parent.selectedItem.toString())
                        .observe(this.viewLifecycleOwner, { result ->

                            result?.let {
                                itemsAdapter.setItems(result)
                            }

                        })

                }

                "women's clothing" -> {

                    itemModel.itemsByCategory(parent.selectedItem.toString())
                        .observe(this.viewLifecycleOwner, { result ->

                            result?.let {
                                itemsAdapter.setItems(result)
                            }

                        })

                }

                "jewelery" -> {

                    itemModel.itemsByCategory(parent.selectedItem.toString())
                        .observe(this.viewLifecycleOwner, { result ->

                            result?.let {
                                itemsAdapter.setItems(result)
                            }

                        })

                }

                "electronics" -> {

                    itemModel.itemsByCategory(parent.selectedItem.toString())
                        .observe(this.viewLifecycleOwner, { result ->

                            result?.let {
                                itemsAdapter.setItems(result)
                            }

                        })

                }

            }


        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
        itemsBinding = null
    }


}