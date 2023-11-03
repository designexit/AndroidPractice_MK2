package com.example.myapplication_test6_7_8_9_10_11_12.ch17_test.PreferenceTest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication_test6_7_8_9_10_11_12.databinding.ActivitySharedPreferDetailTestBinding


class SharedPreferDetailTestActivity : AppCompatActivity() {
    //2번 화
    //공유 프리퍼런스 파일에서 데이터 가져오기

    lateinit var binding: ActivitySharedPreferDetailTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySharedPreferDetailTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getSharedPreferBtn.setOnClickListener {
            //값 가져오기
            //저장된 파일 명 : userInfo
            //key, value형태로 저장됨, key로 해당값 가져오기

            val pref = getSharedPreferences("userInfo", MODE_PRIVATE)
            val email = pref.getString("emaill", "Default EMAIL")
            val password = pref.getString("password", "Default PASSWORD")

        }


    }
}