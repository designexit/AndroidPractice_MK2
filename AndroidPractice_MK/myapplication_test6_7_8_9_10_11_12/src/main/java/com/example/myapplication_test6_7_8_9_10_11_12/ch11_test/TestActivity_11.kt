package com.example.myapplication_test6_7_8_9_10_11_12.ch11_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.example.myapplication_test6_7_8_9_10_11_12.R
import com.example.myapplication_test6_7_8_9_10_11_12.databinding.ActivityTest10Binding
import com.example.myapplication_test6_7_8_9_10_11_12.databinding.ActivityTest11Binding

class TestActivity_11 : AppCompatActivity() {
    lateinit var activityTest11Binding: ActivityTest11Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_test11)
        activityTest11Binding = ActivityTest11Binding.inflate(layoutInflater)
        setContentView(activityTest11Binding.root)

        //액션바 업버튼 붙이기
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }


    //액션바 이벤트 처리
    override fun onSupportNavigateUp(): Boolean {
        Log.d("KMK", "onSupportNavigateUp")
        onBackPressed()
        return super.onSupportNavigateUp()
    }


    // 액션바에 오버플로우 메뉴 붙이기
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)

        // 검색 뷰에, 이벤트 추가하기.
        val menuItem = menu?.findItem(R.id.menu_main_search)
        // menuItem 의 형을 SearchView 타입으로 변환, 형변환
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                //검색어가 변경시 마다, 실행될 로직을 추가.
                Log.d("kmk","텍스트 변경시 마다 호출 : ${newText} ")
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                // 검색어가 제출이 되었을 경우, 연결할 로직.
                // 사용자 디비, 검색을하고, 그 결과 뷰를 출력하는 형태.
                Toast.makeText(this@TestActivity_11,"검색어가 전송됨 : ${query}",Toast.LENGTH_SHORT).show()
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    //오버플로우 메뉴 이벤트 핸들러 추가
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId){
        R.id.menu_main1 -> {
            Toast.makeText(this@TestActivity_11, "menu1 click", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.menu_main2 -> {
            Toast.makeText(this@TestActivity_11, "menu2 click", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.menu_main3 -> {
            Toast.makeText(this@TestActivity_11, "menu3 click", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.menu_main4 -> {
            Toast.makeText(this@TestActivity_11, "menu4 click", Toast.LENGTH_SHORT).show()
            true
        }
        // 람다식에서 return 사용 안됨
        else -> super.onOptionsItemSelected(item)
    }


}