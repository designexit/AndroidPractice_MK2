package com.example.myapplication_test6_7_8_9_10_11_12.ch11_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication_test6_7_8_9_10_11_12.R
import com.example.myapplication_test6_7_8_9_10_11_12.databinding.ActivityTestActivity11FragmentBinding

class TestActivity_11_Fragment : AppCompatActivity() {

    //프래그먼트를 출력하기 위한 베이스 캔버스
    lateinit var binding : ActivityTestActivity11FragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestActivity11FragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 프래그먼트 출력하기
        val fragmentManager : FragmentManager = supportFragmentManager
        val transaction : FragmentTransaction = fragmentManager.beginTransaction()

        // 첫번째 프래그먼트
        val onefragment = OneFragment()

        // 두번째 프래그먼트
        val twofragment = TwoFragment()

        // 세번째 프래그먼트
        val threefragment = ThreeFragment()

        // 네번째 프래그먼트
        val fourfragment = FourFragment()

        // 다섯번째 프래그먼트
        val fivefragment = FiveFragment()



        // 첫번째 프래그먼트 붙이기
        transaction.add(R.id.fragment1,onefragment)

        // 두번째 프래그먼트 붙이기
        transaction.add(R.id.fragment2,twofragment)

        // 세번째 프래그먼트 붙이기
        transaction.add(R.id.fragment3,threefragment)

        // 네번째 프래그먼트 붙이기
        transaction.add(R.id.fragment4,fourfragment)

        // 다섯번째 프래그먼트 붙이기
        transaction.add(R.id.fragment5,fivefragment)

        // 백스텍 화면에 출력 시 task 라는 공간을 사용해서 (메모리 사용함) 출력하고
        // 화면 전환이 발생할 경우 매번 프래그먼트 소멸 시키고, 생성하고를 반복하게 되면 자원이 비효율적 임
        // 그래서 잠시 keep 하고 있다가 다시 그려주기
        // 엑티비티에서도 기존 엑티비티를 최대한 활용하는 방안
        transaction.addToBackStack(null)

        // 뷰 페이져 등을 이용해서 프래그먼트 전환활 때 다시 재 확인하기



        // 화면에 출력
        transaction.commit()


    }
}