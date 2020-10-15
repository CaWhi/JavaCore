package com.xgw.core5.protectedTest;

public class TestMain {
    public static void main(String[] args){
        Man man = new Man("tom",25,"man","123");

        Woman woman = new Woman("jessy",23,"HeFei","456");

        man.wife = woman;

        System.out.println(man.getId());

        int salary = 12;
        man.setSalary(salary);

        Person person = man;
        System.out.println(person.getId());
    }
}
