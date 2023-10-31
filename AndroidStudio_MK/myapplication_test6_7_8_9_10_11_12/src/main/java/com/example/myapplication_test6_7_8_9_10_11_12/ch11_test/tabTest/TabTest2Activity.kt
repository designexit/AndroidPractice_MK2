package com.example.myapplication_test6_7_8_9_10_11_12.ch11_test.tabTest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication_test6_7_8_9_10_11_12.R
import com.example.myapplication_test6_7_8_9_10_11_12.ch11_test.viewPagerRecycler.adapter.ViewPagerAdapterTest
import com.example.myapplication_test6_7_8_9_10_11_12.databinding.ActivityTabTest2Binding
import com.example.myapplication_test6_7_8_9_10_11_12.databinding.ActivityTabTestBinding
import com.google.android.material.tabs.TabLayoutMediator

class TabTest2Activity : AppCompatActivity() {
    lateinit var binding : ActivityTabTest2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabTest2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //탭 레이아웃과 뷰페이져2 연동하기
        //탭 뷰 부분 선택
        //예) 요소의 예 : Tab1, Tab2, Tab3등 이름이 기본값
        val tabLayout = binding.tab2

        //뷰페이져2 뷰 부분 선택
        val viewPager = binding.viewpagerTab

        // 뷰페이져2를 연동 시 사용할 프래그먼트 어댑터 필요
        // 기존꺼 재사용
        // 어댑터 : 뷰에 필요한 데이터를 바인딩(연결) 시키기
        viewPager.adapter = ViewPagerAdapterTest(this)

        //뷰 페이져2, 탭 레이아웃 연결 시켜주기
        TabLayoutMediator(tabLayout, viewPager) {
            tab, position -> tab.text = "Tab${position+1}"
        }.attach()
    }
}