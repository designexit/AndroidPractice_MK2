package com.example.myapplication_test3

class Test {

}

val test1 = "test";
var test2 = null;
val test5 : String = "";
//var test6 : String;

val data1 : Int by lazy {
    println("in lazy")
    10
}
fun main () {
    println("순서1 main 먼저 실행")
    println("순서2 lazy 호출 확인 : " + data1)
    val test3 : String; // 상수 변경 안됨 Java -> constance, final
    var test4 : String;
    // ex) 변수명 : 타입 = 값;
    // 기본은 변경이 되지 않는 상수를 주로 사용하되, 만약 변경이 되는 부분이면 var를 쓰면 됨
    // 코틀린에서는 모든 타입이 참조형(객체)
    // 자바는 기본(형)타입 8가지, 그 외 형(참조형)
    var name = "kmk";
    var name2 : String = "kmk2";
    var age : Int = 10;
    var age2 = 10;
    println("이름 : " + name)
    println("Hello Android by Kotlin")
}