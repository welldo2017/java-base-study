package com.welldo.zero.log6;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Commons Logging 和 log4j
 * Commons Logging是一个第三方日志库，它是由Apache创建的日志模块
 *
 * 1.
 * 特色是，它可以挂接不同的日志系统，并通过配置文件指定挂接的日志系统。
 * 默认情况下，Commons Loggin自动搜索并使用Log4j（Log4j是另一个流行的日志系统），
 * 如果没有找到Log4j，再使用JDK Logging。
 *
 * 2.
 * Commons Logging定义了6个日志级别：
 * FATAL * ERROR * WARNING * INFO(默认) * DEBUG * TRACE *
 *
 * 3.引用
 * 在静态方法中引用Log，static final Log log = LogFactory.getLog(xxxx.class);
 * 在实例方法中引用Log，通常定义一个实例变量, protected final Log log = LogFactory.getLog(getClass());
 *
 * 4. 引入
 * <dependency>
 *     <groupId>commons-logging</groupId>
 *     <artifactId>commons-logging</artifactId>
 *     <version>1.2</version>
 * </dependency>
 *
 *
 * @author welldo
 * @date 2020/8/12
 */
public class Commons_Logging_6 {

    //静态引用
    static final Log log_static = LogFactory.getLog(Commons_Logging_6.class);

    public static void main(String[] args) {
        log_static.error("error");
        log_static.warn("warn");
        log_static.info("info");
        log_static.debug("debug");//默认级别以下的，不会被打印
        log_static.trace("trace");//默认级别以下的，不会被打印

        try {
            int y = 5/0;
        } catch (Exception e) {
            log_static.error("got exception!", e);
        }
    }
}


// 在实例方法中引用Log:
class Person {
    //此种方式有个非常大的好处，就是子类可以直接使用该log实例。
    protected final Log log = LogFactory.getLog(getClass());

    void foo() {
        log.info("foo");
    }
}

// 在子类中使用父类实例化的log:
class Student extends Person {
    void bar() {
        log.info("bar");
    }
}
