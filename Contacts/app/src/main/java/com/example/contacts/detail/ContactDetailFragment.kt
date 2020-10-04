package com.example.contacts.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.contacts.R
import com.example.contacts.contact.ContactListFragmentDirections
import com.example.contacts.databinding.FragmentContactDetailBinding


class ContactDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentContactDetailBinding>(inflater,
            R.layout.fragment_contact_detail, container, false)

        binding.lifecycleOwner = this

        val arg = ContactDetailFragmentArgs.fromBundle(requireArguments())
        binding.name.text = arg.name
        binding.cell.text = arg.cell
        binding.back.setOnClickListener {
            this.findNavController().navigate(
                ContactDetailFragmentDirections.actionContactDetailToContactListFragment())
        }
        return binding.root
    }

}