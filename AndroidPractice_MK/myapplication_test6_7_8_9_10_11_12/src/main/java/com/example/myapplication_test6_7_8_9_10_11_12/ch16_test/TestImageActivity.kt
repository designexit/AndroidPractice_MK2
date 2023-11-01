package com.example.myapplication_test6_7_8_9_10_11_12.ch16_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Media
import androidx.activity.result.contract.ActivityResultContracts
import com.example.myapplication_test6_7_8_9_10_11_12.R
import com.example.myapplication_test6_7_8_9_10_11_12.databinding.ActivityTestImageBinding

class TestImageActivity : AppCompatActivity() {
    //갤러리, 카메라 앱 연동해서 데이터 가져오기
    lateinit var binding : ActivityTestImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //작업구성 2가지
        //1) 갤러리 앱을 호출하는 작업
        //2) 갤러리 앱 데이터를 가져와 처리하는 작업

        //버튼 클릭 시 갤러리 앱 호출, 갤러리에서 선택된 사진을 출력하는 뷰 하나 생성

        // 갤러리 앱 데이터를 가져와 처리하는 작업
        val requestGalleryLauncher = registerForActivityResult(

            ActivityResultContracts.StartActivityForResult()
        ) {
            //it 여기에 갤러리에서 선택된 사진의 데이터가 들어있음

        }

        //버튼 클릭시 갤러리 앱
        binding.galleryBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            //data type지정
            intent.type = "image/*"
            requestGalleryLauncher.launch(intent)
        }



    }
}