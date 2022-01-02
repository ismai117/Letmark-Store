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

class CartAdapter(private val cartItemInterface: CartItemInterface) :
    RecyclerView.Adapter<CartAdapter.CartItemViewHolder>() {

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


            val increase = itemView.increaseQty_btn
            val decrease = itemView.decreaseQty_btn
            var qty = itemView.itemQty_Value
            var qtyValue = 0
            val position = adapterPosition
            val update = itemView.update_cart
            val delete = itemView.delete_item

            itemView.cart_itemName.text = cart.title
            itemView.cart_itemPrice.text = "£${cart.price * cart.quantity}"

            Glide.with(itemView.context).load(cart.image).into(itemView.cart_itemImage)


            qty.text = cart.quantity.toString()


            increase.setOnClickListener {

                if (position == adapterPosition) {


                    qtyValue = qty.text.toString().toInt()
                    qtyValue++
                    qty.text = qtyValue.toString()


                }


            }


            decrease.setOnClickListener {

                if (position == adapterPosition) {

                    qtyValue = qty.text.toString().toInt()

                    if (qtyValue == 1) {
                        qty.text = "1"
                    } else {
                        qtyValue -= 1
                        qty.text = qtyValue.toString()
                    }

                }


            }


            update.setOnClickListener {

                if (position == adapterPosition) {

                    val item = CartEntity(
                        id = cart.id,
                        title = cart.title,
                        image = cart.image,
                        category = cart.category,
                        price = cart.price,
                        quantity = qtyValue
                    )

                    cartItemInterface.updateItem(item)

                }

            }


            delete.setOnClickListener {

                if (position == adapterPosition) {

                    val item = CartEntity(
                        id = cart.id,
                        title = cart.title,
                        image = cart.image,
                        category = cart.category,
                        price = cart.price,
                        quantity = cart.quantity
                    )

                    cartItemInterface.deleteItem(item)

                }

            }


        }


    }


}