package com.welldo.zero.oop_4.core_class;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 * java bean
 * 要枚举一个JavaBean的所有属性，可以直接使用Java核心库提供的Introspector：
 *
 *
 * @author welldo
 * @date 2020/8/10
 */
public class JavaBean5 {
    public static void main(String[] args) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(Person5.class);

        for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {

            //注意: class属性, 是从Object继承的getClass()方法带来的。
            System.out.println(pd.getName());

            System.out.println(pd.getReadMethod());
            System.out.println(pd.getWriteMethod());
        }
    }
}


class Person5 {
    private String name;
    private int age;

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