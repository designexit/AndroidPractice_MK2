package com.example.myapplication_test4.homework.service

import com.example.myapplication_test4.homework.model.LunchMenu

class MenuTest {
    companion object {
        fun lunchMenu(menu:LunchMenu) {
            if(menu.menu.equals("sandwich") && menu.price.equals("7000")){
                println("맛있게 드셈");
            } else {
                println("아직 안 정했나요?")
            }
        }
    }
}