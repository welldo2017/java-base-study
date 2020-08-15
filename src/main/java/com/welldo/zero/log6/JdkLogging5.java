package com.welldo.zero.log6;

import java.util.logging.Logger;

/**
 * 日志就是Logging，它的目的是为了取代System.out.println()。
 * 使用日志最大的好处是，它自动打印了时间、调用类、调用方法等很多有用的信息。
 *
 *
 * 1.
 * 因为Java标准库内置了日志包java.util.logging，我们可以直接用。
 *
 * 2.JDK的Logging定义了7个日志级别，从严重到普通：
 * 默认级别是INFO，默认级别以下的日志，不会被打印出来
 * SEVERE * WARNING * INFO(默认级别) * CONFIG * FINE * FINER * FINEST
 *
 * 3. 缺点
 * Logging系统在JVM启动时, 读取配置文件并完成初始化，一旦开始运行main()方法，就无法修改配置；
 * 配置不太方便，需要在JVM启动时传递参数-Djava.util.logging.config.file=<config-file-name>。
 * 因此，Java标准库内置的Logging使用并不是非常广泛
 *
 * @author welldo
 * @date 2020/8/12
 */
public class JdkLogging5 {
    public static void main(String[] args) {
        Logger logger = Logger.getGlobal();
        logger.fine("ignored.");
        logger.info("start process...");
        logger.warning("memory is running out...");
        logger.severe("process will be terminated...");
    }
}
