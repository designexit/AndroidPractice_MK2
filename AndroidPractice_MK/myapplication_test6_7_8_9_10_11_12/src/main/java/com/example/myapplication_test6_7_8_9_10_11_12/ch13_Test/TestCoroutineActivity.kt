package com.example.myapplication_test6_7_8_9_10_11_12.ch13_Test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import com.example.myapplication_test6_7_8_9_10_11_12.R
import com.example.myapplication_test6_7_8_9_10_11_12.databinding.ActivityTestCoroutineBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

class TestCoroutineActivity : AppCompatActivity() {
    lateinit var binding : ActivityTestCoroutineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestCoroutineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.testCoBtn.setOnClickListener {
            // 오래 걸리는 작업 추가 -> 화면에서 처리하기 보다는 비동기식으로 처리
            // 비동기식으로 처리하는 방법이 기존 스래드 (핸들러 작업), 코루틴으로 작업
           /* var sum = 0L
            var time = measureTimeMillis {
                for (i in 1..2_000_000_000){
                    sum += i
                }
            }
            Log.d("kmk", "time:$time")
            binding.resultCoView.text = "sum : $sum"

            var handler = object : Handler(){
                override fun handleMessage(msg: Message) {
                    super.handleMessage(msg)
                    binding.resultCoView.text = "time : ${msg.arg2}, sum : ${msg.arg1}"
                }
            }
            thread {
                var sum = 0L
                var time = measureTimeMillis {
                    for (i in 1..2_000_000_000){
                        sum += i
                    }
                    val message = Message()
                    message.arg1 = sum.toInt()
                    message.arg2 = time.toInt()
                    handler.sendMessage(message)
                }
                Log.d("kmk", "time:$time")
                binding.resultCoView.text = "sum : $sum"
            }*/

            //방법2) 코루틴 이용해서 비동기 처리 해 보기
            //무거운 작업의 결과값을 전달하는 도구
            val channel = Channel<Int> {  }
            //무거운 작업을 수행하는 로직
            val backgroundScope = CoroutineScope(Dispatchers.Default + Job())
            backgroundScope.launch {
                var sum = 0L
                var time = measureTimeMillis {
                    for (i in 1..2_000_000_000){
                        sum += i
                    }
                }
                Log.d("kmk", "time:$time")
                channel.send(sum.toInt())
                channel.send(time.toInt())
            }

            //무거운 작업에서 결과값을 받아 출력을 함
            val mainScope = GlobalScope.launch(Dispatchers.Main) {
                channel.consumeEach {
                    binding.resultCoView.text = "sum : $it"
                }
            }
        }

    }
}