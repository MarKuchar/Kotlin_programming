package com.example.contacts.contact

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.database.Contact
import com.example.contacts.databinding.ListItemContactBinding

class ContactListAdapter : RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {

    var data = listOf<Contact>()
    var dataTest: HashMap<String, Int> = hashMapOf("Martin" to 123, "Peter" to 234, "Jozef" to 456)
    var list = arrayListOf("Martin", "Peter", "Jozef")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataTest[list[position]]
        holder.fullName.text = list[position]
        holder.phoneNumber.text = item.toString()
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
}