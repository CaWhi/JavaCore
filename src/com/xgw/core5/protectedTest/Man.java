package com.xgw.core5.protectedTest;

public class Man extends Person {
    public String gender;

    public Woman wife;

    public Man(String name, int age, String gender, String id) {
        super(name, age, id);
        this.gender = gender;
    }

    public String getWifeId(){
        return wife.id;
    }

    public String getId(){
        System.out.println("from children");
        return this.id;
    }
}
