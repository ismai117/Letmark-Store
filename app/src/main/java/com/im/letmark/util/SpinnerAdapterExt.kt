package com.im.letmark.util

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner


inline fun Spinner.getItemNameSelected(crossinline itemSelected: (String) -> Unit) {

    this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
            val item = parent?.selectedItem.toString()
            itemSelected(item)
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
            TODO("Not yet implemented")
        }

    }

}