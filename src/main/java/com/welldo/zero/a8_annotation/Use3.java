package com.welldo.zero.a8_annotation;

import com.sun.istack.internal.NotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 处理注解
 *
 * 1. * Java的注解本身对代码逻辑没有任何影响。根据@Retention的配置：
 *      SOURCE类型的注解在编译期就被丢掉了； *          主要由编译器使用，因此我们一般只使用，不编写。
 *
 *      CLASS类型的注解仅保存在class文件中，它们不会被加载进JVM； *          主要由底层工具库使用，涉及到class的加载，一般我们很少用到
 *
 *      RUNTIME类型的注解会被加载进JVM，并且在运行期可以被程序读取。
 *
 * 2. 注解也是一种class，所有的注解都继承自java.lang.annotation.Annotation, 因此，读取注解，需要使用反射API。
 *
 * 3. 判断某个注解是否存在于Class、Field、Method或Constructor：
 *      Class.isAnnotationPresent(Class)
 *      Field.isAnnotationPresent(Class)
 *      Method.isAnnotationPresent(Class)
 *      Constructor.isAnnotationPresent(Class)
 *
 * 4. * 使用反射API读取存在于 类,字段,方法,构造器上面的 Annotation：
 *      Class.getAnnotation(Class)
 *      Field.getAnnotation(Class)
 *      Method.getAnnotation(Class)
 *      Constructor.getAnnotation(Class)
 *
 * 5. 读取类, 方法、字段和构造方法上的 Annotation ,方法都类似。但要读取方法参数的Annotation就比较麻烦一点
 *
 * @author welldo
 * @date 2020/8/17
 */
public class Use3 {
    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException {
        Class cls = UseAnnotation1.class;

        //3. 判断class 是否使用了注解
        boolean b = cls.isAnnotationPresent(Report2.class);
        System.out.println(b);

        //3. 判断某字段 是否使用了注解
        boolean n1 = cls.getField("n").isAnnotationPresent(Report2.class);
        System.out.println(n1);

        //4.先判断, 再读取注解
        if (cls.getField("n").isAnnotationPresent(Report2.class)) {
            Report2 annotation = cls.getField("n").getAnnotation(Report2.class);
            System.out.println(annotation);
            System.out.println(annotation.type());
        }

        //5.
        Method helloMethod = cls.getMethod("hello", String.class,String.class);
        Annotation[][] paramAnno = helloMethod.getParameterAnnotations();
        Annotation[] firstParamAnno = paramAnno[0];//第一个参数的annotation

        System.out.println(paramAnno.length);//一共几个参数
        System.out.println(firstParamAnno.length);//第一个参数有几个注解

        for (Annotation annotation : firstParamAnno) {

            System.out.println(1);
            if (annotation instanceof NotNull) {
                System.out.println("****");
            }
        }
    }

}
