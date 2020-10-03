package com.example.contacts

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.contacts.model.DomainContact

@BindingAdapter("setName")
fun TextView.setName(item: DomainContact?) {
    item?.let {
        text = item.name
    }
}