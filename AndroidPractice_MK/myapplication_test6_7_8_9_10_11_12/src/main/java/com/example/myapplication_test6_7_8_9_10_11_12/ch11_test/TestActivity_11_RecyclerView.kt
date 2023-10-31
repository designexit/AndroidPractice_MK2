package com.example.myapplication_test6_7_8_9_10_11_12.ch11_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication_test6_7_8_9_10_11_12.R
import com.example.myapplication_test6_7_8_9_10_11_12.databinding.ActivityTestActivity11RecyclerViewBinding
import com.example.myapplication_test6_7_8_9_10_11_12.ch11_test.recycler.MyAdapter

// 액티비티가 있어야 뷰를 표현, 액티비티 = 캔버스
class TestActivity_11_RecyclerView : AppCompatActivity() {
    lateinit var binding: ActivityTestActivity11RecyclerViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestActivity11RecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 공공데이터, 사용자 정의한 데이터를 가지고 와서 원하는 뷰에 데이터를 넣는 바인딩 작업을 함.
        val datas = mutableListOf<String>()
        for (i in 1..10){
            datas.add("My Lovely Cat $i")
        }

        // 기본 값으로 세로 방향 출력.
        //binding.recyclerView.layoutManager = LinearLayoutManager(this)
        // 가로 방향으로 출력 해보기.
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.recyclerView.layoutManager = layoutManager

        //액티비티 -> 리사이클러 뷰 -> 실제 데이터를 연결하는 부분 !!!! 중요!!!!!!!
        binding.recyclerView.adapter = MyAdapter(datas)
        // 구분선 넣기, 나중에 옵션으로 배경이미지도 넣기 가능.
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this,LinearLayoutManager.VERTICAL))

        // 2번째 리사이클러 뷰 출력해보기. 기존 데이터 재사용, 뷰홀더, 마이 어댑터 재사용해보기.
        binding.recyclerView2.layoutManager = LinearLayoutManager(this)
        // 기존 재사용
        binding.recyclerView2.adapter = MyAdapter(datas)
        binding.recyclerView2.addItemDecoration(DividerItemDecoration(this,LinearLayoutManager.VERTICAL))
    }
}