package com.example.myapplication_test6_7_8_9_10_11_12.ch7_test

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.widget.Toast
import com.example.myapplication_test6_7_8_9_10_11_12.R
import com.example.myapplication_test6_7_8_9_10_11_12.databinding.ActivityTest7Binding

class TestActivity_7 : AppCompatActivity() {
    lateinit var activityTest7Binding: ActivityTest7Binding
    lateinit var TAG : String

    /* ====================================================================== */
    override fun onCreate(savedInstanceState: Bundle?) {
        TAG = "KMK"
        super.onCreate(savedInstanceState)

        //setContentView(R.layout.activity_test7)
        // 모든 뷰 인스턴스들을 하나의 인스턴스에 모두담기
        activityTest7Binding = ActivityTest7Binding.inflate(layoutInflater)

        //모두 담은 인스턴스의 부모 레이아웃 출력
        setContentView(activityTest7Binding.root)

        // test버튼 클릭 리스너 이벤트 처리 SAM기법
        // 보통 자바에서 람다식을 열결할 때 시용하는 인터페이스
        // 함수형 인터페이스, 추상 메서드가 하나인 인터페이스
        activityTest7Binding.testBtn.setOnClickListener {
            Toast.makeText(this@TestActivity_7, "test!!!", Toast.LENGTH_SHORT).show()
        }
        activityTest7Binding.longBtn.setOnLongClickListener {
            Toast.makeText(this@TestActivity_7, "Long Click!!!", Toast.LENGTH_SHORT).show()
            true
        }

    }
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action){
            MotionEvent.ACTION_DOWN -> {
                // x : 이벤트가 발생한 뷰의 x좌표
                // rawX : 화면의 x좌표
                Log.d(TAG, "Touch down event x:${event.x}, event rawX:${event.rawX}" +
                "event y:${event.y}, event rawY:${event.rawY}")
                Toast.makeText(this@TestActivity_7, "Touch down event", Toast.LENGTH_SHORT).show()
            }
            MotionEvent.ACTION_UP -> {
                Log.d(TAG, "Touch up event x:${event.x}, event rawX:${event.rawX}" +
                        "event y:${event.y}, event rawY:${event.rawY}")
                Toast.makeText(this@TestActivity_7, "Touch up event", Toast.LENGTH_SHORT).show()
            }
            MotionEvent.ACTION_MOVE -> {
                Log.d(TAG, "Touch up event x:${event.x}, event rawX:${event.rawX}" +
                        "event y:${event.y}, event rawY:${event.rawY}")
                //Toast.makeText(this@TestActivity_7, "Touch move", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onTouchEvent(event)
    }
    /* ====================================================================== */

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when(keyCode) {
            KeyEvent.KEYCODE_0 -> Log.d(TAG, "0키 누름")
            KeyEvent.KEYCODE_A -> Log.d(TAG, "A키 누름")
            KeyEvent.KEYCODE_ENTER -> Log.d(TAG, "ENTER키 누름")
        }
        return super.onKeyDown(keyCode, event)
    }



}