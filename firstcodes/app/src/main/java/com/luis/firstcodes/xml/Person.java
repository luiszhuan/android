package com.luis.firstcodes.xml;

import androidx.annotation.NonNull;

public class Person {
    private String id;
    private String name;
    private String age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @NonNull
    @Override
    public String toString() {
        return "id = " + id + ", name = " + name + ", age = " + age;
    }
}
