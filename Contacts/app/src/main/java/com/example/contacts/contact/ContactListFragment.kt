package com.example.contacts.contact

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.contacts.R
import com.example.contacts.database.ContactDatabase
import com.example.contacts.databinding.FragmentContactListBinding
import androidx.recyclerview.widget.GridLayoutManager
import com.example.contacts.database.Contact

class ContactListFragment : Fragment() {

    lateinit var viewModel: ContactViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentContactListBinding>(inflater,
            R.layout.fragment_contact_list,container,false)

        setHasOptionsMenu(true)

        val application = requireNotNull(this.activity).application
        val dataSource = ContactDatabase.getInstance(application).contactDatabaseDao

        val viewModelFactory = ContactViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ContactViewModel::class.java)

        binding.lifecycleOwner = this
        binding.contactViewModel = viewModel

        // Init adapter object to manage the data display on the text holder
        val adapter = ContactListAdapter()
        binding.contactList.adapter = adapter

        // Get data from to database via view model and assign it to adapter's data
        viewModel.contacts.observe(viewLifecycleOwner, Observer {
            it?.let {
                val contactWithAlphabetHeaders = alphabetizedContacts(it)
                adapter.submitList(contactWithAlphabetHeaders)
            }
        })

        // Init manager to layout all the text holders
        val manager = GridLayoutManager(activity, 1)

        binding.floatingActionButton.setOnClickListener {
            it.findNavController().navigate(ContactListFragmentDirections.actionContactListFragmentToAddContact())
        }

        binding.contactList.layoutManager = manager

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu, menu)
    }

    // Sort the list and add it to the list of DataItem, then the list will be submitted to the adapter
    // directly. And adapter object itself organize the list
    private fun alphabetizedContacts(contacts: List<Contact>) : MutableList<ContactListAdapter.DataItem> {
        val contactItems = contacts.sortedBy { it.fullName }
        val contactWithAlphabetHeaders = mutableListOf<ContactListAdapter.DataItem>()

        var currentHeader: String? = null
        contactItems.forEach { contact ->
            contact.fullName.firstOrNull().toString().let {
                if (it != currentHeader) {
                    contactWithAlphabetHeaders.add(ContactListAdapter.DataItem.Header(it))
                    currentHeader = it
                }
            }
            contactWithAlphabetHeaders.add(ContactListAdapter.DataItem.ContactItem(contact))
        }
        return contactWithAlphabetHeaders
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.refresh -> {
                Toast.makeText(activity, "Refresh", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.clear -> {
                viewModel.clearContacts()
                Toast.makeText(activity, "Clear", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}