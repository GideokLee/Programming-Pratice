package com.ssafy.day01.ex04.person;

public class Person {
    String name;
    String gender;
    int age;
    
    // Default Constructor를 생략한 것이다.
    // Person() {}
    
    void walk() {
        System.out.println(name + " 걷기");
        return;
    }
    
    void order() {
        System.out.println(name + " 주문하기");
        return;
    }
    
    void eat() {
        System.out.println(name + " 냠냠");
        return;
    }
}
