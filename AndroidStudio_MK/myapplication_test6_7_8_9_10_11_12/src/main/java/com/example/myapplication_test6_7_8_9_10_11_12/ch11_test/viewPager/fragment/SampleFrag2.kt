package com.example.myapplication_test6_7_8_9_10_11_12.ch11_test.viewPager.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication_test6_7_8_9_10_11_12.R
import com.example.myapplication_test6_7_8_9_10_11_12.databinding.FragmentSampleFrag1Binding
import com.example.myapplication_test6_7_8_9_10_11_12.databinding.FragmentSampleFrag2Binding


class SampleFrag2 : Fragment() {
    lateinit var binding : FragmentSampleFrag2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSampleFrag2Binding.inflate(layoutInflater)
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sample_frag2, container, false)
    }

}