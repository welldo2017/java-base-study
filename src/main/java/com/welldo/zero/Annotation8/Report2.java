package com.welldo.zero.Annotation8;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义注解
 *
 * 1. java语言使用@interface语法来定义注解（Annotation）
 *
 * 2.注解没有属性, 只有配置参数, 可以用default设定一个默认值（强烈推荐）。最常用的参数应当命名为value。
 *
 * 3.元注解
 * 有一些注解可以修饰其他注解，这些注解就称为元注解（meta annotation）
 * Java标准库已经定义了一些元注解，我们只需要使用元注解，通常不需要自己去编写元注解。
 *
 * 4.Target
 * 最常用的元注解是@Target。使用@Target可以定义Annotation能够被应用于源码的哪些位置：
 *      类或接口：ElementType.TYPE；
 *      字段：ElementType.FIELD；
 *      方法：ElementType.METHOD；
 *      构造方法：ElementType.CONSTRUCTOR；
 *      方法参数：ElementType.PARAMETER。
 *
 * 例如，定义注解@Report2 可用在方法上，我们必须添加一个@Target(ElementType.METHOD)：
 * 定义注解@Report2 可用在方法或字段上，可以把@Target注解参数变为数组{ ElementType.METHOD, ElementType.FIELD }：
 *
 * 5.@Retention
 * 另一个重要的元注解@Retention定义了Annotation的生命周期：
 *      仅编译期：RetentionPolicy.SOURCE；在编译期就被丢掉了；
 *      仅class文件：RetentionPolicy.CLASS；仅保存在class文件中，它们不会被加载进JVM；
 *      运行期：RetentionPolicy.RUNTIME。会被加载进JVM，并且在运行期可以被程序读取。
 * 如果@Retention不存在，则该Annotation默认为CLASS。
 * 因为通常我们自定义的Annotation都是RUNTIME，所以，务必要加上@Retention(RetentionPolicy.RUNTIME)这个元注解：
 *
 *
 * 6.
 * 总结一下如何定义Annotation
 * 第一步，用@interface定义注解：public @interface Report {* }
 * 第二步，添加配置参数、默认值： int type() default 0;
 *      把最常用的参数定义为value()，推荐所有参数都尽量设置默认值。
 * 第三步，用元注解配置注解：
 *      必须设置@Target和@Retention，@Retention一般设置为RUNTIME，因为我们自定义的注解通常要求在运行期读取
 *
 *
 * @author welldo
 * @date 2020/8/15
 */

@Target({
        ElementType.METHOD,
        ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface Report2 {

    /**
     * 注解没有属性, 只有配置参数,
     * 使用方法, 参考 {@link UseAnnotation1}
     */
    int min() default 1;
    int max() default 100;
    int type() default 10086;
    String level() default "info";

    //大部分注解会有一个名为value的配置参数，对此参数赋值，可以只写常量，相当于省略了value参数。
    String value() default "xxx";

    //如果只写注解，相当于全部的配置参数, 都使用默认值。
}
