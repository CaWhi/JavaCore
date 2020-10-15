package com.xgw.core5.reflect;

import com.xgw.core5.protectedTest.Man;
import com.xgw.core5.protectedTest.Woman;

import java.lang.reflect.Method;

public class ReflectTestMain {

    public static void main(String[] args){
//        try{
//            Scanner scanner = new Scanner(System.in);
//            String name = scanner.next();
//
//            AnalysisClass.analysis(name);
//        }
//        catch (ClassNotFoundException e){
//            e.printStackTrace();
//        }

        Man man = new Man("tom",25,"man","123");
        Woman woman = new Woman("jessy",23,"HeFei","456");

        man.wife = woman;
        woman.husband = man;

        System.out.println(new AnalysisClass().analysisObject(man));

        try{
            Method m = Man.class.getDeclaredMethod("getId");

            System.out.println(m.invoke(man));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
