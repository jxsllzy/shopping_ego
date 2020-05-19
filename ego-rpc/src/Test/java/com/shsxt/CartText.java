package com.shsxt;

import org.junit.jupiter.api.Test;

public class CartText {

    @Test
    public void test01(){
        Student student = new Student();
        Student student1 = new Student();
        System.out.println(student.getAge()+student.getAge());
    }
}

class Student{
    Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
