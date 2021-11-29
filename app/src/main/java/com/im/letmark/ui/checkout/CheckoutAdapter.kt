package com.im.letmark.ui.checkout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.im.letmark.R
import com.im.letmark.domain.model.Cart
import kotlinx.android.synthetic.main.checkout_cart_item_layout.view.*

class CheckoutAdapter : RecyclerView.Adapter<CheckoutAdapter.CheckoutCartItemHolder>() {

    private lateinit var items: List<Cart>

    fun setItem(items: List<Cart>) {
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckoutCartItemHolder {
        return CheckoutCartItemHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.checkout_cart_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CheckoutCartItemHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class CheckoutCartItemHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {


        fun bind(cart: Cart) {


            itemView.checkoutcart_itemName.text = cart.title
            itemView.checkoutcart_itemPrice.text = "£${cart.price}"

            Glide.with(itemView.context).load(cart.image).into(itemView.checkoutcart_itemImage)


        }


    }


}