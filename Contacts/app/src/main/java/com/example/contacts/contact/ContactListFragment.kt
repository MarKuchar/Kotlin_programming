package com.example.contacts.contact

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.contacts.R
import com.example.contacts.database.ContactDatabase
import com.example.contacts.databinding.FragmentContactListBinding
import androidx.recyclerview.widget.GridLayoutManager
import com.example.contacts.model.DomainContact
import kotlinx.coroutines.launch

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
        val adapter = ContactListAdapter(ContactListener { name: String, cell: String ->
            Toast.makeText(context, "${name}", Toast.LENGTH_LONG).show()
            viewModel.onContactClicked(name, cell)
        })

        binding.contactList.adapter = adapter

        viewModel.navigateToSleepDetail.observe(viewLifecycleOwner, Observer {
            it?.let {
                this.findNavController().navigate(ContactListFragmentDirections
                    .actionContactListFragmentToDetailContactFragment(it.name, it.cell))
            }
        })

        // Get data from to database via view model and assign it to adapter's data
        viewModel.contacts.observe(viewLifecycleOwner, Observer<List<DomainContact>> { contacts ->
            contacts?.apply {
                lifecycleScope.launch {
                    val contactWithAlphabetHeaders =
                        alphabetizedContactsAPI(contacts)
                    adapter.submitList(contactWithAlphabetHeaders)
                }
            }
        })

        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
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
    private fun alphabetizedContactsAPI(contacts: List<DomainContact>) : MutableList<ContactListAdapter.DataItem> {
        val contactItems = contacts.sortedBy { it.name }
        val contactWithAlphabetHeaders = mutableListOf<ContactListAdapter.DataItem>()

        var currentHeader: String? = null
        contactItems.forEach { contact ->
            contact.name.first().toString().let {
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
                viewModel.refreshDataFromRepository()
                Toast.makeText(activity, "Refresh", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.clear -> {
                viewModel.onClearContacts()
                Toast.makeText(activity, "Clear", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
}