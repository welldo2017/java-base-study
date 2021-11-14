package com.welldo.zero.a8_annotation;

import java.lang.annotation.*;
import java.lang.reflect.Field;

/**
 * 处理注解
 *
 * @author welldo
 * @date 2020/8/17
 */
public class Use4 {
    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException {

    }

    /**
     * 我们编写一个Person实例的检查方法，它可以检查Person实例的String字段长度是否满足@Range的定义：
     * 这样一来，我们通过@Range注解，配合check()方法，就可以完成Person实例的检查。
     *
     * !!! 注意检查逻辑完全是我们自己编写的，JVM不会自动给注解添加任何额外的逻辑。
     */
    void check(Person person) throws IllegalArgumentException, ReflectiveOperationException {
        // 遍历所有Field:
        for (Field field : person.getClass().getFields()) {
            // 获取Field定义的@Range:
            Range range = field.getAnnotation(Range.class);
            // 如果@Range存在:
            if (range != null) {
                // 获取Field的值:
                Object value = field.get(person);
                // 如果值是String:
                if (value instanceof String) {
                    String s = (String) value;
                    // 判断值是否满足@Range的min/max:
                    if (s.length() < range.min() || s.length() > range.max()) {
                        throw new IllegalArgumentException("Invalid field: " + field.getName());
                    }
                }
            }
        }
    }

}


//我们来看一个@Range注解，我们希望用它来定义一个String字段的规则：字段长度满足@Range的参数定义：
//定义了注解，本身对程序逻辑没有任何影响。我们必须自己编写代码来使用注解
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Range {
    int min() default 0;

    int max() default 255;
}

class Person {
    @Range(min = 1, max = 20)
    public String name;

    @Range(max = 10)
    public String city;
}