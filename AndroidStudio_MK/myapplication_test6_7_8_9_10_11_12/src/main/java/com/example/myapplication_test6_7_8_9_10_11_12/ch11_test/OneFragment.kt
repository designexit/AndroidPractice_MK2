package com.example.myapplication_test6_7_8_9_10_11_12.ch11_test

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication_test6_7_8_9_10_11_12.R
import com.example.myapplication_test6_7_8_9_10_11_12.databinding.FragmentOneBinding

class OneFragment : Fragment() {
    //베이스 엑티비티 위에 출력되기 위한 프래그먼트
    lateinit var binding : FragmentOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("KMK", "생명주기, onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("KMK", "생명주기, onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("KMK", "생명주기, onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("KMK", "생명주기, onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("KMK", "생명주기, onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("KMK", "생명주기, onDestroy")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


}