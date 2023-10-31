package com.example.myapplication_test6_7_8_9_10_11_12.ch11_test.recyclerDataTest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapplication_test6_7_8_9_10_11_12.R
import com.example.myapplication_test6_7_8_9_10_11_12.ch11_test.recycler.MyAdapter
import com.example.myapplication_test6_7_8_9_10_11_12.databinding.ActivityTestActivity11RecyclerViewBinding
import com.example.myapplication_test6_7_8_9_10_11_12.databinding.ActivityTestRecycleDataBinding

class TestActivity_recycleData : AppCompatActivity() {
    lateinit var binding : ActivityTestRecycleDataBinding
    var newDataNumber = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestRecycleDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val datas = mutableListOf<String>()
        for (i in 1..10){
            datas.add("nova $i")
        }

        val recyclerView = binding.recyclerView

        //--- LayoutManager는 아래 3가지중 하나를 선택하여 사용 ----
        // 1) LinearLayoutManager()
        // 2) GridLayoutManager()
        // 3) StaggeredGridLayoutManager()
        //---------------------------------------------------------
        val linearLayoutManager = LinearLayoutManager(this as Context)
        val gridLayoutManager = GridLayoutManager(this,3,GridLayoutManager.HORIZONTAL,true)
        val staggeredoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        // 리니어, 수직 , 수평 방향으로 출력
         recyclerView.layoutManager = linearLayoutManager // LayoutManager 설정
        // 그리드, 테이블 형식으로 출력하기.
        //recyclerView.layoutManager = gridLayoutManager // LayoutManager 설정
        // 지그 재그
        //recyclerView.layoutManager = staggeredoutManager // LayoutManager 설정

        // 리사이클러 뷰의 꾸미기 담당 클래스를 붙이는 작업. (적용)
        recyclerView.addItemDecoration(MyDecoration(this))


        // val customAdapter = CustomAdapter(testDataSet)
        // 만들어 둔 어댑터를 붙이는 작업.
        val customAdapter = MyAdapter(datas)
        recyclerView.adapter = customAdapter // 어댑터 설정


        //===== [데이터 추가/삭제 기능을 위해 추가된 코드] ===========


        //===== [데이터 추가/삭제 기능을 위해 추가된 코드] ===========
//        val buttonAddItem = findViewById<Button>(R.id.buttonAddItem)
//        val buttonDeleteItem = findViewById<Button>(R.id.buttonDeleteItem)
        val buttonAddItem = binding.buttonAddItem
        val buttonDeleteItem = binding.buttonDeleteItem

        buttonAddItem.setOnClickListener {
            datas.add("NEW DATA " + newDataNumber++)
            customAdapter.notifyItemInserted(datas.size)
        }

        buttonDeleteItem.setOnClickListener {
            datas.removeAt(datas.size - 1)
            customAdapter.notifyDataSetChanged() // 만능, 되도록 사용x
        }






    }
}