package com.dby.jweb.app;

import java.net.InetAddress;

public class NetHello {

  public static void main(String[] args) {
    System.out.println(getHostAddress("www.baidu.com"));
    System.out.println(getHostAddress("www.duobeiyun.com"));
    System.out.println(getHostAddress("weibo.com"));
    System.out.println(getHostAddress("google.com"));
  }

  private static String getHostAddress(String host) {
    try {
      InetAddress address = InetAddress.getByName(host);
      return address.getHostAddress();
    } catch (Throwable e) {
      e.printStackTrace();
      return null;
    }
  }
}
