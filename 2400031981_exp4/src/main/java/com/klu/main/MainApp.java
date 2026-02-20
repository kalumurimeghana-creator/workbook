package com.klu.main;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.klu.model.CourswRegistration;

public class MainApp {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    ApplicationContext context=new ClassPathXmlApplicationContext("bean.xml");
        CourswRegistration cr=(CourswRegistration)context.getBean("courseReg");
        cr.display();

  }

}