package com.example.myapplication_test6_7_8_9_10_11_12.ch11_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.example.myapplication_test6_7_8_9_10_11_12.R
import com.example.myapplication_test6_7_8_9_10_11_12.databinding.ActivityTest11Binding
import com.example.myapplication_test6_7_8_9_10_11_12.databinding.ActivityTestActivity11ToolBarBinding

class TestActivity_11_ToolBar : AppCompatActivity() {
    lateinit var activity11ToolBarBinding: ActivityTestActivity11ToolBarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_test_activity11_tool_bar)
        activity11ToolBarBinding = ActivityTestActivity11ToolBarBinding.inflate(layoutInflater)
        setContentView(activity11ToolBarBinding.root)

        //tool bar 붙이기
        setSupportActionBar(activity11ToolBarBinding.toolBar)

        //업버튼 붙이기
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
        menuInflater.inflate(R.menu.toolbar_menu,menu)

        // 검색 뷰에, 이벤트 추가하기.
        val menuItem = menu?.findItem(R.id.menu_toolbar_search)
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
                Toast.makeText(this@TestActivity_11_ToolBar,"검색어가 전송됨 : ${query}", Toast.LENGTH_SHORT).show()
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    //오버플로우 메뉴 이벤트 핸들러 추가
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId){
        R.id.menu_toolbar1 -> {
            Toast.makeText(this@TestActivity_11_ToolBar, "Toolbar menu1 click", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.menu_toolbar2 -> {
            Toast.makeText(this@TestActivity_11_ToolBar, "Toolbar menu2 click", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.menu_toolbar3 -> {
            Toast.makeText(this@TestActivity_11_ToolBar, "Toolbar menu3 click", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.menu_toolbar4 -> {
            Toast.makeText(this@TestActivity_11_ToolBar, "Toolbar menu4 click", Toast.LENGTH_SHORT).show()
            true
        }
        // 람다식에서 return 사용 안됨
        else -> super.onOptionsItemSelected(item)
    }

}