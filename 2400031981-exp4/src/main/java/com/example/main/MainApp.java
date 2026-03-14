package com.example.main;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.model.CourseRegistration;

public class MainApp {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    ApplicationContext context=new ClassPathXmlApplicationContext("bean.xml");
        CourseRegistration cr=(CourseRegistration)context.getBean("courseReg");
        cr.display();

  }

}