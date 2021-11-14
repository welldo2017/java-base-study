package com.welldo.zero.a6_log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SLF4J和Logback
 *
 * 1.
 * SLF4J类似于Commons Logging，也是一个日志接口，
 * 而Logback类似于Log4j，是一个日志的实现。
 *
 * 2.
 * SLF4J对 Commons Logging 接口的改进
 * Commons Logging 中拼接字符串需要 用加号连接;
 * slf4j 使用占位符, 后面的变量自动替换占位符
 *
 *
 * 3.
 * 对比一下Commons Logging和SLF4J的接口：
 *----------------------------------------------------------------
 *          Commons Logging	                | SLF4J
 *----------------------------------------------------------------
 * org.apache.commons.logging.Log	        | org.slf4j.Logger
 * org.apache.commons.logging.LogFactory	| org.slf4j.LoggerFactory
 *----------------------------------------------------------------
 *
 * @author welldo
 * @date 2020/8/14
 */
public class SLF4J_Logback_7 {
    //实例引用
    final Logger logger = LoggerFactory.getLogger(getClass());

    //静态引用
    static final Logger LOGGER_STATIC = LoggerFactory.getLogger(SLF4J_Logback_7.class);


    public static void main(String[] args) {
        String name = "welldo";
        int score = 99;
        LOGGER_STATIC.info("Set score {} for Person {} ok.", score, name);
    }
}
