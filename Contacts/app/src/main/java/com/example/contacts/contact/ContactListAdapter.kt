package com.example.contacts.contact

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.ContactDiffCallback
import com.example.contacts.databinding.HeaderBinding
import com.example.contacts.databinding.ListItemContactBinding
import com.example.contacts.model.DomainContact


private val ITEM_VIEW_TYPE_HEADER = 0
private val ITEM_VIEW_TYPE_ITEM = 1

class ContactListAdapter(val clickListener: ContactListener) : ListAdapter<ContactListAdapter.DataItem,
        RecyclerView.ViewHolder>(ContactDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> IndexViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> ViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.ContactItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

    class IndexViewHolder(binding: HeaderBinding): RecyclerView.ViewHolder(binding.root) {
        val firstLetter = binding.firstLetter

        companion object {
            fun from(parent: ViewGroup): IndexViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HeaderBinding
                    .inflate(layoutInflater, parent, false)
                return IndexViewHolder(binding)
            }
        }
    }

    class ViewHolder private constructor(val binding: ListItemContactBinding) : RecyclerView.ViewHolder(binding.root) {
        val fullName: TextView = binding.fullName
        val phoneNumber: TextView = binding.phoneNumber

        fun bind(item: DomainContact, clickListener: ContactListener) {
            binding.contact = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemContactBinding
                    .inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    sealed class DataItem {
        abstract val id: Long
        data class ContactItem(val contact: DomainContact): DataItem() {
            override val id = Long.MAX_VALUE
        }

        data class Header(val letter: String): DataItem() {
            override val id = Long.MIN_VALUE
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val contact = getItem(position) as DataItem.ContactItem
                holder.fullName.text = contact.contact.name
                holder.phoneNumber.text = contact.contact.cell
                holder.bind(contact.contact, clickListener)

            }
            is IndexViewHolder -> {
                val contact = getItem(position) as DataItem.Header
                holder.firstLetter.text = contact.letter
            }
        }
    }
}

class ContactListener(val clickListener: (name: String, cell: String) -> Unit) {
    fun onClick(contact: DomainContact) = clickListener(contact.name, contact.cell)
}