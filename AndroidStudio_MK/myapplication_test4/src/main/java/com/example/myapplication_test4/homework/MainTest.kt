package com.example.myapplication_test4.homework

import com.example.myapplication_test4.homework.model.LunchMenu
import com.example.myapplication_test4.homework.model.Member
import com.example.myapplication_test4.homework.service.LoginTest
import com.example.myapplication_test4.homework.service.MenuTest
import java.util.Scanner

class MainTest {
}


fun main() {
    val scanner: Scanner = Scanner(System.`in`)

    println("name : 입력해 주세요 :")
    val name = scanner.nextLine()

    println("password : 입력해 주세요 :")
    val password = scanner.nextLine()

    val member = Member(name, password)

    //println("member(더미데이터)의 입력된 값 : ${member.name}, ${member.password}")
    LoginTest.loginLogic(member)

    println("menu : 점심메뉴를 입력해 주세요")
    val menu = scanner.nextLine()

    println("price : 가격을 입력해 주세요")
    val price = scanner.nextLine()

    val lunch = LunchMenu(menu, price)
    MenuTest.lunchMenu(lunch)





}