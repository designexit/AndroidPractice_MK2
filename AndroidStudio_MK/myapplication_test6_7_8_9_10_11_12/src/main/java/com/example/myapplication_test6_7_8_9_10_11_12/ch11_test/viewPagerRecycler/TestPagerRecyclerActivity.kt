package com.example.myapplication_test6_7_8_9_10_11_12.ch11_test.viewPagerRecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication_test6_7_8_9_10_11_12.R
import com.example.myapplication_test6_7_8_9_10_11_12.ch11_test.recycler.MyAdapter
import com.example.myapplication_test6_7_8_9_10_11_12.ch11_test.viewPager.adapter.RecyclerViewTest
import com.example.myapplication_test6_7_8_9_10_11_12.ch11_test.viewPagerRecycler.adapter.ViewPagerAdapterTest
import com.example.myapplication_test6_7_8_9_10_11_12.databinding.ActivityTestPagerRecyclerBinding

class TestPagerRecyclerActivity : AppCompatActivity() {
    lateinit var binding : ActivityTestPagerRecyclerBinding
    var newDataNumber = 11
    //액션버튼 토글
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestPagerRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //1)tool bar, 2)드로워, 3)드로워 네비게이션, 4)플로팅 액션버튼, 5)앱바, 6)탭 레이아웃
        //1)툴바 붙이기 : TestActivity_11_ToolBar 재사용
        setSupportActionBar(binding.toolbar)

        //1)-1 툴바 오버플로우 메뉴 붙이기
        //toolbar-menu 재사용

        //2)드로워 화면은 뷰에서 설정했음
        //뷰1:본문, 뷰2 : 드로워

        //3)드로워 네비게이션 뷰 추가설정
        
        //3)이벤트 핸들러 추가

        binding.mainDrawerView.setNavigationItemSelectedListener {
                it ->
            if (it.title == "로그인") {
                Toast.makeText(this@TestPagerRecyclerActivity,"로그인 화면 이동",Toast.LENGTH_SHORT).show()
            }
            else if (it.title == "로그아웃") {
                Toast.makeText(this@TestPagerRecyclerActivity,"로그아웃 화면 이동",Toast.LENGTH_SHORT).show()
            }
            else if (it.title == "메인가기") {
                Toast.makeText(this@TestPagerRecyclerActivity,"메인가기 화면 이동",Toast.LENGTH_SHORT).show()
            }
            true
        }

        //드로워화면 액션버튼 클릭 시 드로워 화면 나오게 하기
        toggle =
            ActionBarDrawerToggle(this@TestPagerRecyclerActivity, binding.drawer,R.string.open, R.string.close)
        //화면 적용하기
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //버튼 클릭스 동기화 : 드로워 열어주기
        toggle.syncState()

        //4)플로팅 엑션버튼 이벤트추가
        binding.floatingActionBtn.setOnClickListener {
            when(binding.floatingActionBtn.isExtended){
                true -> binding.floatingActionBtn.shrink()
                false -> binding.floatingActionBtn.extend()
            }
            Toast.makeText(this@TestPagerRecyclerActivity, "floating Action Button", Toast.LENGTH_SHORT).show()
        }



        // 뷰페이저2 프래그먼트 출력
        // 현재 액티비티는 캔버스, 기본화면
        // 뷰페이저와 리사이클러 2개를 붙인다

        // 뷰페이저 준비물 : 1) 프래그먼트 어뎁터, 2) 목록요소, 3) 프래그먼트3개
        binding.viewPager1.adapter = ViewPagerAdapterTest(this)

        // 리사이클러 뷰 붙이기
        // 리사이클러 준비물 : 1) 리사이클러뷰, 2) 목록요소, 3) 더미데이터
        val datas = mutableListOf<String>()
        for(i in 1..10) {
            datas.add("더미데이터 추가 번호 : $i")
        }

        //출력방향, 리니어로 수직으로...
        val linearLayoutManager = LinearLayoutManager(this)

        // 리사이클러 뷰 속성 옵션에 출력 옵션 붙이기
        binding.viewRecycler.layoutManager = linearLayoutManager

//        val RecyclerViewTest = MyAdapter(datas)
//        binding.viewRecycler.adapter = RecyclerViewTest
        // 리사이클러뷰 속성 옵션에 데이터를 붙이기 , 어댑터 를 연결한다.
        val customAdapter = RecyclerViewTest(datas)
        binding.viewRecycler.adapter = customAdapter

        binding.addBtn.setOnClickListener {
            datas.add("NEW DATA " + newDataNumber++)
            customAdapter.notifyItemInserted(datas.size)
        }

        binding.delBtn.setOnClickListener {
            datas.removeAt(datas.size - 1)
            customAdapter.notifyDataSetChanged() // 만능, 되도록 사용x
        }
    }
    //Oncreate

    //오버플로우 메뉴 이벤트 핸들러 추가
    /*override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId){
        R.id.menu_toolbar1 -> {
            Toast.makeText(this@TestPagerRecyclerActivity, "Toolbar menu1 click", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.menu_toolbar2 -> {
            Toast.makeText(this@TestPagerRecyclerActivity, "Toolbar menu2 click", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.menu_toolbar3 -> {
            Toast.makeText(this@TestPagerRecyclerActivity, "Toolbar menu3 click", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.menu_toolbar4 -> {
            Toast.makeText(this@TestPagerRecyclerActivity, "Toolbar menu4 click", Toast.LENGTH_SHORT).show()
            true
        }
        // 람다식에서 return 사용 안됨
        else -> super.onOptionsItemSelected(item)
    }*/

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //이벤트가 toggle 버튼에서 제공된거라면..
        // 버튼을 열때 이용되는 이벤트 핸들러 부분.
        if(toggle.onOptionsItemSelected(item)){
            return true
            // 오버 플로우 메뉴의 클릭시 이벤트를 ,
        } else if ( R.id.menu_toolbar1 == item.itemId) {
            Toast.makeText(this@TestPagerRecyclerActivity,"툴바메뉴1 클릭됨", Toast.LENGTH_SHORT).show()
            true
        }
        else if ( R.id.menu_toolbar2 == item.itemId) {
            Toast.makeText(this@TestPagerRecyclerActivity,"툴바메뉴2 클릭됨", Toast.LENGTH_SHORT).show()
            true
        }
        else if ( R.id.menu_toolbar3 == item.itemId) {
            Toast.makeText(this@TestPagerRecyclerActivity,"툴바메뉴3 클릭됨", Toast.LENGTH_SHORT).show()
            true
        }

        return super.onOptionsItemSelected(item)
    }

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
                Toast.makeText(this@TestPagerRecyclerActivity,"검색어가 전송됨 : ${query}", Toast.LENGTH_SHORT).show()
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

}