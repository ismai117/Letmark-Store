package com.im.letmark.util

import androidx.appcompat.widget.SearchView

inline fun SearchView.onQueryTextChanged(crossinline listener: (String) -> Unit){

    this.setOnQueryTextListener(object :SearchView.OnQueryTextListener{

        override fun onQueryTextSubmit(p0: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(string: String?): Boolean {
            listener(string.orEmpty())
            return true
        }

    })

}


