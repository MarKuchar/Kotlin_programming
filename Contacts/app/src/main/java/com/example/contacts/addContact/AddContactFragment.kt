package com.example.contacts.addContact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.contacts.R
import com.example.contacts.TextValidator
import com.example.contacts.database.ContactDatabase
import com.example.contacts.databinding.FragmentAddContactBinding
import kotlinx.android.synthetic.main.fragment_add_contact.*


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
            let {
                val name = personName.text.toString()
                val number = phoneNumber.text.toString().toInt()
                viewModel.createContact(name, number)
                Toast.makeText(activity, "Saved", Toast.LENGTH_SHORT).show()
            }
            activity?.onBackPressed()
        }

        binding.cancel.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.personName.addTextChangedListener(object : TextValidator(binding.personName) {
            override fun validate(textView: TextView?, text: String?) {
                if (text != null) {
                    if (text.contains(" ")) {
                        viewModel.hasFullName.value = true
                    }
                }
            }
        })

        binding.phoneNumber.addTextChangedListener(object : TextValidator(binding.phoneNumber) {
            override fun validate(textView: TextView?, text: String?) {
                if (text != null) {
                    if (text.length > 9) {
                        viewModel.hasPhoneNumber.value = true
                    }
                }
            }
        })
        return binding.root
    }
}