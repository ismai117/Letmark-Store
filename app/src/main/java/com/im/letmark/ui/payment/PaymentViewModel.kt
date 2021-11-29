package com.im.letmark.ui.payment

import androidx.lifecycle.ViewModel
import com.im.letmark.domain.model.Order
import com.im.letmark.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel
@Inject
constructor(
    private val repository: Repository
): ViewModel(){


    fun deleteAll() = runBlocking { repository.deleteAllCart() }

    fun saveCustomerOrder(order: Order) = runBlocking { repository.saveCustomerOrder(order) }


}