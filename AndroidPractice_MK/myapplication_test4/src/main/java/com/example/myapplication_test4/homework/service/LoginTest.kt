package com.example.myapplication_test4.homework.service

import com.example.myapplication_test4.homework.model.Member

class LoginTest {
    companion object {
        fun loginLogic(member: Member) {
            if (member.name.equals("admin") && member.password.equals("1234")) {
                println("로그인 성공");
            } else {
                println("아이디와 패쓰워드를 확인해 주세요.")
            }
        }
    }
}