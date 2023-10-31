package com.example.myapplication_test6_7_8_9_10_11_12.ch11_test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication_test6_7_8_9_10_11_12.R
import com.example.myapplication_test6_7_8_9_10_11_12.databinding.FragmentFiveBinding
import com.example.myapplication_test6_7_8_9_10_11_12.databinding.FragmentFourBinding

class FiveFragment : Fragment() {
    lateinit var binding: FragmentFiveBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFiveBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}