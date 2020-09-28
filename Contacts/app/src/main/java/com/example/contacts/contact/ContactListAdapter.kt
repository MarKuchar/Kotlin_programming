package com.example.contacts.contact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.ContactDiffCallback
import com.example.contacts.R
import com.example.contacts.database.Contact
import com.example.contacts.databinding.ListItemContactBinding


private val ITEM_VIEW_TYPE_HEADER = 0
private val ITEM_VIEW_TYPE_ITEM = 1

class ContactListAdapter : ListAdapter<ContactListAdapter.DataItem,
        RecyclerView.ViewHolder>(ContactDiffCallback()) {

    var data = listOf<Contact>()
    var dataTest: HashMap<String, Int> = hashMapOf("Martin" to 123, "Peter" to 234, "Jozef" to 456)
    var list = arrayListOf("Martin", "Peter", "Jozef")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> TextViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> ViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

    fun addHeaderAndSubmitList(list: List<Contact>?) {
        val items = when (list) {
            null -> listOf(DataItem.Header)
            else -> listOf(DataItem.Header) + list.map { DataItem.ContactItem(it) }
        }
        submitList(items)
    }

//    override fun getItemCount(): Int {
//        return 3
//    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.ContactItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

    class TextViewHolder(view: View): RecyclerView.ViewHolder(view) {
        companion object {
            fun from(parent: ViewGroup): TextViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.header, parent, false)
                return TextViewHolder(view)
            }
        }
    }

    class ViewHolder private constructor(binding: ListItemContactBinding) : RecyclerView.ViewHolder(binding.root){
        val fullName: TextView = binding.fullName
        val phoneNumber: TextView = binding.phoneNumber

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
        data class ContactItem(val contact: Contact): DataItem() {
            override val id = contact.contactID
        }
        object Header: DataItem() {
            override val id = Long.MIN_VALUE
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val contact = getItem(position) as DataItem.ContactItem
                holder.fullName.text = list[position]
                holder.phoneNumber.text = contact.toString()
                holder.phoneNumber.text = contact.toString()
            }
        }
    }
}
