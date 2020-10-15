package com.xgw.core5.protectedTest;

public class Woman extends Person {
    public String address;

    public Man husband;

    public Woman(String name, int age, String address, String id) {
        super(name, age, id);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getHusbandId(){
        return husband.id;
    }
}
