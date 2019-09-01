package com.example.wangjingyang.ipc_aidl;

import java.io.Serializable;

public class Custom implements Serializable {
private  static  final long serialVersionUID=12341234;
//transient 修饰不会序列化
//    public transient  String name;
    public   String name;
    //static 修饰的便令不能序列化
//    public static int age;
    public  int age;
    //book 也会被序列化
//    private Book book;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}