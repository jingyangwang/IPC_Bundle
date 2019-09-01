package com.example.wangjingyang.ipc_aidl;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {
    private int age;
    private  String name;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    private  String sex;
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Creator<Person> getCREATOR() {
        return CREATOR;
    }

    public Person(){

    }
    protected Person(Parcel in) {
        name=in.readString();
        age=in.readInt();
        sex=in.readString();

    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeString(sex);
    }
}
