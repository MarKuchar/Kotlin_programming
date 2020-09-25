package com.example.contacts.contact

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.R
import com.example.contacts.TextItemViewHolder
import com.example.contacts.database.Contact

class ContactListAdapter : RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {

    var data = listOf<Contact>()
    var dataTest: HashMap<String, Int> = hashMapOf("Martin" to 123, "Peter" to 234, "Jozef" to 456)
    var list = arrayListOf("Martin", "Peter", "Jozef")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.list_item_contact, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataTest[list[position]]
        holder.fullName.text = list[position]
        holder.phoneNumber.text = item.toString()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val fullName: TextView = itemView.findViewById(R.id.full_name)
        val phoneNumber: TextView = itemView.findViewById(R.id.phone_number)
    }
}