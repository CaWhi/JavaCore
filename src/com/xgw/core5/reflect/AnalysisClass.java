package com.xgw.core5.reflect;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class AnalysisClass {
    public static void analysis(String className) throws ClassNotFoundException{
        Class c1 = Class.forName(className);
        Class f1 = c1.getSuperclass();

        String modifier = Modifier.toString(c1.getModifiers());
        if(modifier.length()>0){
            System.out.print(modifier+" ");
        }
        System.out.print(c1.getName());

        if(f1!=null && f1!=Object.class){
            System.out.print(" extends " + f1.getName());
        }

        System.out.println(" { \n");

        Field[] fields = c1.getDeclaredFields();
        for(Field field : fields){
            modifier = Modifier.toString(field.getModifiers());
            if(modifier.length()>0)System.out.print(" "+modifier+" "+field.getName()+";\n");
        }
        System.out.println();
        Constructor[] constructors = c1.getDeclaredConstructors();
        for(Constructor constructor : constructors){
            modifier = Modifier.toString(constructor.getModifiers());
            if(modifier.length()>0)System.out.print(" "+modifier+" "+constructor.getName()+"(");

            Class[] paramTypes = constructor.getParameterTypes();
            String paramString = "";
            for(Class param:paramTypes){
                paramString+=param.getName()+", ";
            }
            if(paramString.length()>0)
                paramString=paramString.substring(0,paramString.length()-2);
            System.out.print(paramString+");\n");
        }
        System.out.println();
        Method[] methods = c1.getDeclaredMethods();
        for(Method method : methods){
            modifier = Modifier.toString(method.getModifiers());
            if(modifier.length()>0)System.out.print(" "+modifier+" "+method.getName()+"(");

            Class[] paramTypes = method.getParameterTypes();
            String paramString = "";
            for(Class param:paramTypes){
                paramString+=param.getName()+", ";
            }
            if(paramString.length()>0)
                paramString=paramString.substring(0,paramString.length()-2);
            System.out.print(paramString+");\n");
        }

        System.out.println("} ");
    }

    private List<Object> visted = new ArrayList<>();

    public String analysisObject(Object o){
        if(o==null)return "null";
        if(visted.contains(o)) return "...";

        visted.add(o);

        Class c1 = o.getClass();
        if(c1==String.class)return (String)o;

        if(c1.isArray()){
            String r =c1.getComponentType().getName()+"[]{";
            for(int i = 0; i< Array.getLength(o); i++){
                if(i>0)r+=",";
                Object val = Array.get(o,i);
                if(c1.getComponentType().isPrimitive())r+=val;
                else r+=analysisObject(val);
            }

            return r+"}";
        }

        String r = c1.getName();

        do{
            r+="[";

            Field[] fields = c1.getDeclaredFields();
            AccessibleObject.setAccessible(fields,true);

            for(Field f : fields){
                if(Modifier.isStatic(f.getModifiers())){
                    continue;
                }

                if(!r.endsWith("["))r+=",";

                r+=f.getName()+" = ";
                try{
                    Class type = f.getType();
                    Object val = f.get(o);
                    if(type.isPrimitive())r+=val;
                    else r+=analysisObject(val);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            r+="]";
            c1=c1.getSuperclass();
        }
        while (c1!=null&&c1!=Object.class);

        return r;

    }
}
