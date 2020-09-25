package com.example.contacts.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.contacts.R
import com.example.contacts.database.Contact
import com.example.contacts.database.ContactDatabase
import com.example.contacts.databinding.FragmentContactListBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager

class ContactListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentContactListBinding>(inflater,
            R.layout.fragment_contact_list,container,false)

        setHasOptionsMenu(true)

        val application = requireNotNull(this.activity).application
        val dataSource = ContactDatabase.getInstance(application).contactDatabaseDao

        val viewModelFactory = ContactViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(ContactViewModel::class.java)

        binding.lifecycleOwner = this
        binding.contactViewModel = viewModel

        // Init adapter object to manage the data display on the text holder
        val adapter = ContactListAdapter()
        binding.contactList.adapter = adapter


        // Get data from to database via view model and assign it to adapter's data
        viewModel.contacts.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        // Init manager to layout all the text holders
        val manager = GridLayoutManager(activity, 1)
//        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
//            override fun getSpanSize(position: Int) =  when (position) {
//                0 -> 3
//                else -> 1
//            }
//        }

        binding.contactList.layoutManager = manager

        return binding.root
    }

}