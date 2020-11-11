package com.dby.jweb.app;

import com.dby.jweb.hello.NetUtils;
import com.dby.jweb.libs.Address;
import com.dby.jweb.libs.PersonBean;
import com.dby.jweb.libs.WebUtils;

public class HelloWorld {

  public static void main(String[] args) {
    PersonBean personBean = new PersonBean();
    personBean.setId(1L);
    personBean.setName("张三");
    personBean.setAddress("null");

    System.out.println(personBean.getId());
    System.out.println(personBean.getName());
    System.out.println(personBean.getAge());
    System.out.println(personBean.getAddress());
    PersonBean.Companion.log();
    PersonBean.log();
    System.out.println("-----");
    System.out.println(PersonBean.Companion.getGender());
    System.out.println(PersonBean.getGender());

    WebUtils.a();
    NetUtils.a();
    System.out.println("-----------");

    System.out.println(new Address("www.baidu.com", 80));
  }
}
