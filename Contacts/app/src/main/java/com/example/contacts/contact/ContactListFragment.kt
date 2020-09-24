package com.example.contacts.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.contacts.R
import com.example.contacts.database.Contact
import com.example.contacts.database.ContactDatabase
import com.example.contacts.databinding.FragmentContactListBinding

class ContactListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentContactListBinding>(inflater,
            R.layout.fragment_contact_list,container,false)

        setHasOptionsMenu(true)

        val application = requireNotNull(this.activity).application
        val dataSource = ContactDatabase.getInstance(application).contactDatabaseDao
//
        val viewModelFactory = ContactViewModelFactory(dataSource, application)

//        binding.lifecycleOwner = this
//        binding.contactViewModel = viewModel

//        val viewModelFactory = ContactViewModelFactory("Martin Kuchar")
        val viewModel = ViewModelProvider(this, viewModelFactory).get(ContactViewModel::class.java)

        return binding.root
    }

}