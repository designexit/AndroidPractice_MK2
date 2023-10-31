package com.example.myapplication_test6_7_8_9_10_11_12.ch11_test.tabTest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication_test6_7_8_9_10_11_12.R
import com.example.myapplication_test6_7_8_9_10_11_12.ch11_test.viewPagerRecycler.fragment.Test1_Fragment
import com.example.myapplication_test6_7_8_9_10_11_12.ch11_test.viewPagerRecycler.fragment.Test2_Fragment
import com.example.myapplication_test6_7_8_9_10_11_12.ch11_test.viewPagerRecycler.fragment.Test3_Fragment
import com.example.myapplication_test6_7_8_9_10_11_12.databinding.ActivityTabTestBinding
import com.google.android.material.tabs.TabLayout

class TabTestActivity : AppCompatActivity() {
    lateinit var binding : ActivityTabTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //기본 탭 레이아웃 붙이기
        //1) 코드로, 2)xml뷰로 : 이 방법이 쉽다
        //준비물 : 1)탭 레이아웃이 그려지는 캔버스(액티비티), 2)요소 : 탭을 눌렀을 때 변경되는 콘텐츠(프래그먼트사용)

        //방법1) : 코드
        val tabLayout = binding.tabs
        /*val tab1 : TabLayout.Tab = tabLayout.newTab()
        tab1.text = "Tab1"
        tabLayout.addTab(tab1)

        val tab2 : TabLayout.Tab = tabLayout.newTab()
        tab2.text = "Tab2"
        tabLayout.addTab(tab2)

        val tab3 : TabLayout.Tab = tabLayout.newTab()
        tab3.text = "Tab3"
        tabLayout.addTab(tab3)*/

        //방법2) : xml


        // 탭 첫화면을 Test1_Fragment로 구성
        supportFragmentManager.beginTransaction().add(R.id.tabContents, Test1_Fragment()).commit()

        //이벤트 리스너 추가
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val transaction = supportFragmentManager.beginTransaction()
                when(tab?.text) {
                    "Tab1" -> transaction.replace(R.id.tabContents, Test1_Fragment())
                    "Tab2" -> transaction.replace(R.id.tabContents, Test2_Fragment())
                    "Tab3" -> transaction.replace(R.id.tabContents, Test3_Fragment())
                }
                transaction.commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Toast.makeText(this@TabTestActivity, "onTabUnselected", Toast.LENGTH_SHORT).show()
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Toast.makeText(this@TabTestActivity, "onTabReselected", Toast.LENGTH_SHORT).show()
            }

        })


    }
}