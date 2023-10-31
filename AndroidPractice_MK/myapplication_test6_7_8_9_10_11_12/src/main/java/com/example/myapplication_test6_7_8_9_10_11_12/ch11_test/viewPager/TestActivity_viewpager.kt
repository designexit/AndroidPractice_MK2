package com.example.myapplication_test6_7_8_9_10_11_12.ch11_test.viewPager

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication_test6_7_8_9_10_11_12.ch11_test.recycler.MyAdapter2
import com.example.myapplication_test6_7_8_9_10_11_12.ch11_test.viewPager.adapter.MyFragmentAdapter
import com.example.myapplication_test6_7_8_9_10_11_12.databinding.ActivityTestViewpagerBinding
import com.example.myapplication_test6_7_8_9_10_11_12.databinding.ItemPagerBinding

class TestActivity_viewpager : AppCompatActivity() {
    lateinit var binding : ActivityTestViewpagerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestViewpagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val datas = ArrayList<String>()
        for (i in 1..5){
            datas.add("My lovely Cat $i")
        }

        // 외부기능이니 디펜던시에 build.gradle에 복사 후 sync now 해서 사용
        // 뷰페이저 출력방식 2가지 : 1) 어뎁터 사용 방법, 2) 프래그먼트 사용 방법

        binding.viewPager1.adapter = MyAdapter2(datas)

        // 2) 프래그먼트 형식으로 뷰페이저 구성
        binding.viewPager2.adapter = MyFragmentAdapter(this)



    }
    class MyPagerViewHolder(val binding: ItemPagerBinding): RecyclerView.ViewHolder(binding.root)

    //    class MyPagerViewHolder(val binding: ItemPager2Binding): RecyclerView.ViewHolder(binding.root)
    class MyPagerAdapter(val datas: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun getItemCount(): Int {
            return datas.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            MyPagerViewHolder(
                ItemPagerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val binding = (holder as MyPagerViewHolder).binding
            //뷰에 데이터 출력
            binding.itemPagerTextView.text = datas[position]
            when (position % 3) {
                0 -> binding.itemPagerTextView.setBackgroundColor(Color.RED)
                1 -> binding.itemPagerTextView.setBackgroundColor(Color.BLUE)
                2 -> binding.itemPagerTextView.setBackgroundColor(Color.GREEN)
            }
        }
    }
}