package com.tophoon.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.tophoon.bean.MyBean;

public class TestDrive {

	/*
	 * IoC : BeanFactory, ApplicationContext
	 */

	public static void main(String[] args) {
		/*FileSystemResource resource = new FileSystemResource("./src/config/config.xml");
		BeanFactory factory = new XmlBeanFactory(resource);*/

		//ApplicationContext factory = new FileSystemXmlApplicationContext("./src/config/**/*.xml");
		//ApplicationContext factory = new FileSystemXmlApplicationContext("./src/config/config.xml");
		//ApplicationContext factory = new ClassPathXmlApplicationContext("/config/config.xml");
		//ApplicationContext factory = new ClassPathXmlApplicationContext("config/config.xml", MyBean.class);
		ApplicationContext factory = new GenericXmlApplicationContext("/com/tophoon/config/database/config.xml");

		MyBean bean = factory.getBean("mybean", MyBean.class);
		bean.sayHello();

		MyBean yBean = factory.getBean("yourbean", MyBean.class);
		yBean.sayHello();
	}
}
