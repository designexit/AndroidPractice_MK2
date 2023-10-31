package com.example.myapplication_test5

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputBinding
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication_test5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var imgCheck : String
    lateinit var TAG : String

    // 뷰 바인딩 설정 1
    lateinit var binding:AppCompatActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        imgCheck="Y"
        TAG = "MainActivity"

        super.onCreate(savedInstanceState)

        // 뷰 바인딩 설정 2
        val binding = ActivityMainBinding.inflate(layoutInflater)

        // 뷰 바인딩 설정 3
        setContentView(binding.root)
        // setContentView(R.layout.activity_main)

        // 기본 뷰 선택 방법 1
        val loginBtn : Button = findViewById(R.id.loginBtn)

        // 회원가입 폼으로 이동
        // val joinBtn : Button = findViewById(R.id.joinBtn)
        binding.joinBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, JoinActivity::class.java)
            startActivity(intent)
        }



        // 메인 버튼으로 뷰 show/hide
        val shBtn : Button = findViewById(R.id.shBtn)
        shBtn.setOnClickListener {
            Log.d(TAG, "버튼을 클릭 시 확인")
            if (imgCheck.equals("Y")) {
                val imgView: ImageView = findViewById(R.id.m_img1)
                imgView.visibility = View.VISIBLE
                imgCheck = "N"
            } else {
                val imgView: ImageView = findViewById(R.id.m_img1)
                imgView.visibility = View.INVISIBLE
                imgCheck = "Y"
            }
        }


        // 기본 뷰 선택 방법 2
        loginBtn.setOnClickListener{
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}