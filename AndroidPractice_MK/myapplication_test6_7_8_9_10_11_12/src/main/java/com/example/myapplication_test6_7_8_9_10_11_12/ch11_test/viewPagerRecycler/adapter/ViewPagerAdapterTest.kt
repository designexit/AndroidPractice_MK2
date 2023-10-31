package com.example.myapplication_test6_7_8_9_10_11_12.ch11_test.viewPagerRecycler.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication_test6_7_8_9_10_11_12.ch11_test.viewPagerRecycler.fragment.Test1_Fragment
import com.example.myapplication_test6_7_8_9_10_11_12.ch11_test.viewPagerRecycler.fragment.Test2_Fragment
import com.example.myapplication_test6_7_8_9_10_11_12.ch11_test.viewPagerRecycler.fragment.Test3_Fragment

class ViewPagerAdapterTest (fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    var testFregmet : List<Fragment>

    init {
        testFregmet = listOf(Test1_Fragment(), Test2_Fragment(), Test3_Fragment())
    }

    override fun getItemCount(): Int {
        return testFregmet.size
    }

    override fun createFragment(position: Int): Fragment {
        val returnFragment : Fragment = testFregmet[position]
        return returnFragment
    }

}