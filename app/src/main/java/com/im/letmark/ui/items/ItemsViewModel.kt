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
) : ViewModel(){




    val items = repository.getProducts().asLiveData()



}