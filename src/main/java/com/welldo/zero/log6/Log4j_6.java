package com.welldo.zero.log6;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;

/**
 * Commons Logging 和 log4j
 * 前面介绍了Commons Logging，可以作为“日志接口”来使用。而真正的“日志实现”可以使用非常流行的日志框架 Log4j。
 * <p>
 * 1. Log4j是一个组件化设计的日志系统，它的架构大致如下：
 * log.info("User signed in.");
 * │   ┌──────────┐    ┌──────────┐    ┌──────────┐    ┌──────────┐
 * ├──>│ Appender │───>│  Filter  │───>│  Layout  │───>│ Console  │
 * │   └──────────┘    └──────────┘    └──────────┘    └──────────┘
 * │   ┌──────────┐    ┌──────────┐    ┌──────────┐    ┌──────────┐
 * ├──>│ Appender │───>│  Filter  │───>│  Layout  │───>│   File   │
 * │   └──────────┘    └──────────┘    └──────────┘    └──────────┘
 * │   ┌──────────┐    ┌──────────┐    ┌──────────┐    ┌──────────┐
 * └──>│ Appender │───>│  Filter  │───>│  Layout  │───>│  Socket  │
 *     └──────────┘    └──────────┘    └──────────┘    └──────────┘
 * <p>
 * 当我们使用Log4j输出一条日志时，Log4j自动通过不同的Appender把同一条日志输出到不同的目的地。例如：
 * console：输出到屏幕； * file：输出到文件； * socket：通过网络输出到远程计算机； * jdbc：输出到数据库
 * <p>
 * 2. Filter
 * 过滤哪些log需要被输出，哪些log不需要被输出。例如，仅输出ERROR级别的日志。
 * <p>
 * 3. Layout
 * 格式化日志信息，例如，自动添加日期、时间、方法名称等信息。
 * <p>
 * <p>
 * 4.用Log4j只需要把log4j2.xml和相关jar放入classpath；
 * 如果要更换Log4j，只需要移除log4j2.xml和相关jar；
 * <p>
 *
 * 5.引入 2.x 版本,三者缺一不可
 * <dependency>
 *      <groupId>org.apache.logging.log4j</groupId>
 *      <artifactId>log4j-api</artifactId>
 *      <version>${log4j.version}</version>
 * </dependency>
 * <dependency>
 *      <groupId>org.apache.logging.log4j</groupId>
 *      <artifactId>log4j-core</artifactId>
 *      <version>${log4j.version}</version>
 * </dependency>
 * <dependency>
 *      <groupId>org.apache.logging.log4j</groupId>
 *      <artifactId>log4j-jcl</artifactId>
 *      <version>${log4j.version}</version>
 * </dependency>
 *
 * @author welldo
 * @date 2020/8/12
 */
public class Log4j_6 {

    //静态引用
    static final Log log_static = LogFactory.getLog(Log4j_6.class);

    public static void main(String[] args) {
        log_static.info("结构虽然复杂，但我们在实际使用的时候，并不需要关心Log4j的API，而是通过 xml 来配置它");
        log_static.info("详情请参照classpath下的 log4j2.xml 文件");

        log_static.info("Start process...");
        try {
            "".getBytes("invalidCharsetName");
        } catch (UnsupportedEncodingException e) {
            log_static.error("Invalid encoding.", e);
        }
        log_static.info("Process end.");
    }
}


