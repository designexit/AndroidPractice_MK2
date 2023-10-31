package com.example.myapplication_test6_7_8_9_10_11_12.ch11_test.viewPagerRecycler.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication_test6_7_8_9_10_11_12.R
import com.example.myapplication_test6_7_8_9_10_11_12.databinding.FragmentTest1Binding

class Test2_Fragment : Fragment() {
    lateinit var binding : FragmentTest1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentTest1Binding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTest1Binding.inflate(layoutInflater, container, false )
        return inflater.inflate(R.layout.fragment_test2_, container, false)
    }
}