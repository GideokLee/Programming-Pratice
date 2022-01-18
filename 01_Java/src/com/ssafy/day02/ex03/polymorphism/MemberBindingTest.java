package com.ssafy.day02.ex03.polymorphism;

public class MemberBindingTest {

    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        // TODO: 참조 객체의 레벨에 따라 어떤 멤버 변수 또는 메서드가 동작하는지 확인하시오.
        // END:
    }

}

class SuperClass{
    String x = "super";
    
    public void method() {
        System.out.println("super class method");
    }
}

class SubClass extends SuperClass{
    String x = "sub";
    
    @Override
    public void method() {
        System.out.println("sub class method");
    }
}