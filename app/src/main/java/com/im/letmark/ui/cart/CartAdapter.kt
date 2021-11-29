package com.im.letmark.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.im.letmark.R
import com.im.letmark.data.local.cart.CartEntity
import com.im.letmark.domain.model.Cart
import com.im.letmark.util.getItemNameSelected
import kotlinx.android.synthetic.main.cart_item_layout.view.*

class CartAdapter(private val cartItemInterface: CartItemInterface) : RecyclerView.Adapter<CartAdapter.CartItemViewHolder>() {

    private lateinit var items: List<Cart>

    fun setItems(items: List<Cart>) {
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        return CartItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cart_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        holder.bind(items[position], cartItemInterface)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class CartItemViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {


        fun bind(cart: Cart, cartItemInterface: CartItemInterface) {


            val position = adapterPosition

            itemView.cart_itemName.text = cart.title
            itemView.cart_itemPrice.text = "£${cart.price * cart.quantity}"

            Glide.with(itemView.context).load(cart.image).into(itemView.cart_itemImage)



            when (cart.category) {


                "men's clothing" -> {

                    ArrayAdapter.createFromResource(
                        itemView.context,
                        R.array.mens_clothing_quantity_array,
                        android.R.layout.simple_spinner_dropdown_item
                    ).also { adapter ->

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
                        itemView.quantity_spinner.adapter = adapter

                    }

                }

                "women's clothing" -> {

                    ArrayAdapter.createFromResource(
                        itemView.context,
                        R.array.womens_clothing_quantity_array,
                        android.R.layout.simple_spinner_dropdown_item
                    ).also { adapter ->

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
                        itemView.quantity_spinner.adapter = adapter

                    }

                }

                "jewelery" -> {

                    ArrayAdapter.createFromResource(
                        itemView.context,
                        R.array.jewelery_quantity_array,
                        android.R.layout.simple_spinner_dropdown_item
                    ).also { adapter ->

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
                        itemView.quantity_spinner.adapter = adapter

                    }

                }

                "electronics" -> {

                    ArrayAdapter.createFromResource(
                        itemView.context,
                        R.array.electronics_quantity_array,
                        android.R.layout.simple_spinner_dropdown_item
                    ).also { adapter ->

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
                        itemView.quantity_spinner.adapter = adapter

                    }

                }


            }



            val spinner = itemView.quantity_spinner

            var qtyUpdated: Int? = null

            spinner.getItemNameSelected{

                qtyUpdated = it.toInt()

            }


            itemView.cart_updateQty.setOnClickListener {

                if(position == adapterPosition){



                    qtyUpdated?.let {

                        val item = CartEntity(

                            id = cart.id,
                            title = cart.title,
                            image = cart.image,
                            category = cart.category,
                            price = cart.price,
                            quantity = qtyUpdated!!,

                            )

                        cartItemInterface.updateItem(item)

                    } ?: 0

                }

            }

        }


    }


}