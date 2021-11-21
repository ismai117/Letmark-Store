package com.im.letmark.ui.items

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.im.letmark.R
import com.im.letmark.domain.model.Product
import com.im.letmark.ui.detail.DetailFragmentDirections
import kotlinx.android.synthetic.main.product_item_layout.view.*
import kotlin.math.roundToInt

class ItemsAdapter : RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder>() {

    private lateinit var items: List<Product>

    fun setItems(items: List<Product>) {
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        return ItemsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.product_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ItemsViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        fun bind(product: Product) {

            itemView.item_name.text = product.title
            itemView.item_price.text = "£${product.price.roundToInt()}"
            itemView.item_rating.text = "Rating: ${product.rating.rate}"

            Glide.with(itemView.context).load(product.image).into(itemView.item_image)


            itemView.setOnClickListener {

                val action = ItemsFragmentDirections.actionItemsFragmentToDetailFragment()
                action.product = product
                Navigation.findNavController(it).navigate(action)

            }


        }

    }


}