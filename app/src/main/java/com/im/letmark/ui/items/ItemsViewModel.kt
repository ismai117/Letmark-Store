package com.im.letmark.ui.items

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.im.letmark.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel
@Inject
constructor(
    private val repository: Repository
) : ViewModel() {


    val items = repository.getProducts().asLiveData()

    val itemsNameByAsc = repository.getAllProductsNameAsc().asLiveData()

    val itemsNameByDesc = repository.getAllProductsNameDesc().asLiveData()

    val itemsPriceByAsc = repository.getAllProductsPriceAsc().asLiveData()

    val itemsPriceByDesc = repository.getAllProductsPriceDesc().asLiveData()

    fun itemsByCategory(category: String) = repository.getCategoryProducts(category).asLiveData()

    fun itemsByTitle(title: String) = repository.getTitleProducts(title).asLiveData()


}