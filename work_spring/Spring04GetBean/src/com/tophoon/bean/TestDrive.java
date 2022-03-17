package com.tophoon.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestDrive {

	public static void main(String[] args) {
		System.out.println("프로그램 시작 ...");
		ApplicationContext context = new FileSystemXmlApplicationContext("./src/com/tophoon/config/config.xml");
		System.out.println("context created ...");
		Person bean1 = (Person) context.getBean("person");
		Person bean2 = context.getBean("person", Person.class);
		IPerson bean3 = context.getBean(IPerson.class);
		IPerson bean4 = context.getBean(Person.class);

		System.out.println(bean1 == bean2); //config.xml의 scope 속성에 따라 결과가 달라짐

		bean1.sayHello();
		bean2.sayHello();
		bean3.sayHello();
		bean4.sayHello();

		System.out.println(bean1);
		System.out.println(bean2);
		System.out.println(bean3);
		System.out.println(bean4);
	}
}
