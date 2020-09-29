package com.example.contacts

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.contact.ContactListAdapter


class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)

class ContactDiffCallback : DiffUtil.ItemCallback<ContactListAdapter.DataItem>() {
    override fun areItemsTheSame(oldItem: ContactListAdapter.DataItem, newItem: ContactListAdapter.DataItem): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: ContactListAdapter.DataItem, newItem: ContactListAdapter.DataItem): Boolean {
        return oldItem == newItem
    }
}

abstract class TextValidator(private val textView: TextView) : TextWatcher {
    abstract fun validate(textView: TextView?, text: String?)

    override fun afterTextChanged(s: Editable) {
        val text = textView.text.toString()
        validate(textView, text)
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) { }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) { }
}

class PairLiveData<A, B>(first: MutableLiveData<A>, second: MutableLiveData<B>) : MediatorLiveData<Pair<A?, B?>>() {
    init {
        addSource(first) { value = it to second.value }
        addSource(second) { value = first.value to it }
    }
}