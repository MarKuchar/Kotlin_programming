package com.example.contacts.addContact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.contacts.R
import com.example.contacts.contact.ContactViewModel
import com.example.contacts.database.ContactDatabase
import com.example.contacts.databinding.FragmentAddContactBinding


class AddContactFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentAddContactBinding>(inflater,
        R.layout.fragment_add_contact, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = ContactDatabase.getInstance(application).contactDatabaseDao

        val viewModelFactory = AddContactViewModelFactory(dataSource)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(AddContactViewModel::class.java)

        binding.lifecycleOwner = this
        binding.newContactViewModel = viewModel

        binding.save.setOnClickListener {
            
        }

        binding.cancel.setOnClickListener {
            activity?.onBackPressed()
        }

        return binding.root
    }
}