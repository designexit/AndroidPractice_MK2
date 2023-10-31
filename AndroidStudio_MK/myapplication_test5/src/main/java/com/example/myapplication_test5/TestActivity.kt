package com.example.myapplication_test5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.myapplication_test5.databinding.ActivityMainBinding
import com.example.myapplication_test5.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {
    lateinit var activityTestBinding: ActivityTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_test)

        //뷰바인딩 설정2
        activityTestBinding = ActivityTestBinding.inflate(layoutInflater)

        //뷰바인딩 설정3
        setContentView(activityTestBinding.root)

        //showBtn 클릭 시 프로필 이미지 보이게
        //특정 뷰 선택하는 방법 2가지
        //1)-1 findViewById
        val showBtn : Button = findViewById(R.id.showBtn)

        //2)-1 viewBinding
        val showBtn2 = activityTestBinding.showBtn
        showBtn2.setOnClickListener {
            activityTestBinding.profileImg.visibility = View.VISIBLE
        }

        //선택한 뷰를 클릭 이벤트 핸들러 추가
        //1)-2
        /*showBtn.setOnClickListener {
            val profileImg : ImageView = findViewById(R.id.profileImg)
            profileImg.visibility = View.VISIBLE
        }*/

        //hideBtn 클릭 시 프로필 이미지 안 보이게
        //1)-3 findViewById
        val hideBtn : Button = findViewById(R.id.hideBtn)

        //선택한 뷰를 클릭 이벤트 핸들러 추가
        //1)-2
        /*hideBtn.setOnClickListener {
            val profileImg : ImageView = findViewById(R.id.profileImg)
            profileImg.visibility = View.INVISIBLE
        }*/

        //2)-1 viewBinding
        val hideBtn2 = activityTestBinding.hideBtn
        hideBtn2.setOnClickListener {
            activityTestBinding.profileImg.visibility = View.INVISIBLE
        }


       /* val profileImgView : ImageView = findViewById(R.id.profileImg)
        profileImgView.setOnClickListener{
            Toast.makeText(this@TestActivity, "이미지를 클릭했음.", Toast.LENGTH_LONG).show()
        }*/

        activityTestBinding.profileImg.setOnClickListener {
            Toast.makeText(this@TestActivity, "이미지를 클릭했음.", Toast.LENGTH_LONG).show()
        }



        //체크박스 클릭 시 이벤트 핸들러
        activityTestBinding.checkbox1.setOnCheckedChangeListener { buttonView, isChecked ->
            Toast.makeText(this@TestActivity, "check1 선택 됨", Toast.LENGTH_SHORT).show()
        }
        activityTestBinding.checkbox2.setOnCheckedChangeListener { buttonView, isChecked ->
            Toast.makeText(this@TestActivity, "check2 선택 됨", Toast.LENGTH_SHORT).show()
        }

        //라디오 버튼 체크 확인
        activityTestBinding.radio1.setOnCheckedChangeListener { buttonView, isChecked ->
            Toast.makeText(this@TestActivity, "radio1 선택 됨", Toast.LENGTH_SHORT).show()
        }
        activityTestBinding.radio2.setOnCheckedChangeListener { buttonView, isChecked ->
            Toast.makeText(this@TestActivity, "radio1 선택 됨", Toast.LENGTH_SHORT).show()
        }


    }


}