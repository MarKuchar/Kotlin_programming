package com.example.contacts.contact

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.TextItemViewHolder
import com.example.contacts.database.Contact

class ContactListAdapter : RecyclerView.Adapter<TextItemViewHolder>() {

    var data = listOf<Contact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}