package com.example.contacts

import android.annotation.SuppressLint
import android.widget.TextView
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