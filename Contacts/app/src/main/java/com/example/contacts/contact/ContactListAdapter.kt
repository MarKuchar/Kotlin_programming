package com.example.contacts.contact

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.R
import com.example.contacts.TextItemViewHolder
import com.example.contacts.database.Contact

class ContactListAdapter : RecyclerView.Adapter<TextItemViewHolder>() {

    var data = listOf<Contact>()
    var dataTest: HashMap<String, Int> = hashMapOf("Martin" to 123, "Peter" to 234, "Jozef" to 456)
    var list = arrayListOf("Martin", "Peter", "Jozef")



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.contact_text_view, parent, false) as TextView
        return TextItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = dataTest[list[position]]
        holder.textView.text = item.toString()

    }
}