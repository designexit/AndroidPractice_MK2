package com.example.myapplication_test6_7_8_9_10_11_12.ch11_test.recycler

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication_test6_7_8_9_10_11_12.databinding.ItemPagerBinding
import com.example.myapplication_test6_7_8_9_10_11_12.databinding.ItemRecyclerBinding

// 뷰를 모아 둔 박스 : 목록 요소의 뷰

class MyViewHolder2 (val binding: ItemPagerBinding) : RecyclerView.ViewHolder(binding.root)

// 뷰와 데이터를 연결한다
// 리사이클러 뷰, 뷰페이저2에서도 같은 패턴으로 사용

// 더미데이터 : datas, 공공데이터, 백에 연결된 데이터
class MyAdapter2 (val datas: MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    // 리사이클러 뷰 어뎁터 상속받으면 필수적으로 재정의 해야하는 함수
    // 자동완성 생성

    // 뷰생성 역할
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MyViewHolder2(
            ItemPagerBinding.inflate(
            LayoutInflater.from(parent.context),parent,false))

    override fun getItemCount(): Int {
        Log.d("KMK", "getItemCount : ${datas.size}")
        return datas.size
    }

    // 뷰에 데이터를 연동(바인딩) 해 주는 작업
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("KMK", "onBindViewHolder:$position")
        val binding = (holder as MyViewHolder2).binding

        // 뷰 데이터 출력
        binding.itemPagerTextView.text = datas[position]
        when (position % 3) {
            0 -> binding.itemPagerTextView.setBackgroundColor(Color.RED)
            1 -> binding.itemPagerTextView.setBackgroundColor(Color.BLUE)
            2 -> binding.itemPagerTextView.setBackgroundColor(Color.GREEN)
        }
    }




}


// 목록 아이템 요소 뷰 만들기