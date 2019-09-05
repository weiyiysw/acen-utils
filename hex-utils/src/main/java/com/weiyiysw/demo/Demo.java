package com.weiyiysw.demo;

import java.util.Date;

public class Demo {
    public static void main(String[] args) {
        Date d = new Date();
        System.out.println(d.getTime());
        System.out.println(System.currentTimeMillis());

//        while (true) {
//            System.out.println("你好，世界！HelloWorld!");
//            try {
//             Thread.sleep(5000);
//            } catch(InterruptedException ex) {
//                ex.printStackTrace();
//            }
//        }
    }
}
