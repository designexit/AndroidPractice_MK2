package com.example.myapplication_test6_7_8_9_10_11_12.ch17_test.PreferenceTest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication_test6_7_8_9_10_11_12.databinding.ActivitySharedPreferTestBinding


class SharedPreferTestActivity : AppCompatActivity() {
    //1번 화면
    //공유 프리퍼런스 파일에 데이터 담기
    //공유 프리퍼런스 파일은 모든 엑티비티에서 접근가능
    //EditTest뷰에서, 라디오뷰에서, 각 뷰에서 데이터를 가져와서 test에 담기

    lateinit var binding : ActivitySharedPreferTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySharedPreferTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //이벤트 처리
        binding.buttonInsertSP.setOnClickListener {
            val email = binding.userEmail.text
            val password = binding.userPassword.text


            val pref = getSharedPreferences("userInfo", MODE_PRIVATE)
            val editor = pref.edit()
            editor.putString("email", "email")
            editor.putString("password", "password")
            editor.commit()
        }


    }
}