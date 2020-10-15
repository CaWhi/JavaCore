package com.xgw.core5.protectedTest;

public class Person {
    private String name;

    private int age;

    private double salary;

    protected String id;

    public Person(String name, int age, String id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        System.out.println("from super");
        return id;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
