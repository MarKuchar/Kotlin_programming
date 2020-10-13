package com.example.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.databinding.FragmentBinding

class TestFragment : Fragment() {

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment,
            container, false
        )
        val adapter = Adapter()
        binding.list.adapter = adapter

        linearLayoutManager = LinearLayoutManager(context)
//        binding.list.layoutManager = linearLayoutManager

        val manager = GridLayoutManager(activity, 3)
        binding.list.layoutManager = manager

        return binding.root
    }

}