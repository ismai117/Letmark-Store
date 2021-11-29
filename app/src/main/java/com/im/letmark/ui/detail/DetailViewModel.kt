package com.im.letmark.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.im.letmark.data.local.cart.CartEntity
import com.im.letmark.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class DetailViewModel
@Inject
constructor(
    private val repository: Repository
) : ViewModel() {


    fun insert(cartEntity: CartEntity) = viewModelScope.launch(Dispatchers.IO){
        repository.insertItem(cartEntity)
    }



}