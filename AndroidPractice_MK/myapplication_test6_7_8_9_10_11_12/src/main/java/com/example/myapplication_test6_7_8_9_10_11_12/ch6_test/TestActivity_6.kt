package com.example.myapplication_test6_7_8_9_10_11_12.ch6_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication_test6_7_8_9_10_11_12.databinding.ActivityTest6Binding

class TestActivity_6 : AppCompatActivity() {
    lateinit var activityTest6Binding: ActivityTest6Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_test)
        activityTest6Binding = ActivityTest6Binding.inflate(layoutInflater)
        setContentView(activityTest6Binding.root)

        activityTest6Binding.nova.setOnClickListener {
            activityTest6Binding.nova.visibility = View.INVISIBLE
            activityTest6Binding.rose.visibility = View.VISIBLE
        }

        activityTest6Binding.rose.setOnClickListener {
            activityTest6Binding.nova.visibility = View.VISIBLE
            activityTest6Binding.rose.visibility = View.INVISIBLE
        }
    }
}