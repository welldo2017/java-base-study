package com.welldo.zero.a16_maven;

/**
 * maven命令
 *
 * -----------------------------------------------------------------
 * maven命令格式:  mvn 插件名:命令
 * 如 mvn help:system
 * 表示运行help插件，然后给help插件发送system命令
 * 命令的结果: 输出了系统里,所有环境变量的信息
 *
 * -----------------------------------------------------------------
 * 我们来详细看一下mvn help:system这个命令的运行过程：
 *      1.系统会去环境变量PATH对应的所有目录中寻找mvn命令，然后在$MAVNE_HOME\bin中找到了可执行的mvn文件
 *      2.运行mvn文件，也就是执行mvn命令
 *      3.命令启动的时候，一般会有一个启动配置文件，maven也有。
 *      mvn命令启动的时候会去 ~/.m2 目录寻找配置文件settings.xml，此配置文件可以对maven进行一些启动设置（如插件缓存放在什么位置等等）
 *      若 ~/.m2 目录中找不到settings.xml文件，那么会去M2_HOME/conf目录找这个配置文件
 *
 *      4.mvn命令后面跟了一个参数：help:sytem，这个表示运行help插件，然后给help插件发送system命令
 *      5.maven查看本地缓存目录（settings.xml中配置的）寻找是否有help插件，如果本地没有继续下面的步骤
 *      6.maven会去你在settings.xml文件中配置的站点里，下载help插件到本地缓存目录
 *          （如果没有在settings.xml配置站点，maven会去官方默认站点[repo.maven.apache.org]下载，这个叫中央仓库）
 *      7.运行help插件，给此插件发送system指令，help收到system指令后，输出了本地环境变量的信息
 *
 * -----------------------------------------------------------------
 * mvn 插件名称:help
 * 上面这种命令，会输出插件的帮助文档，来感受一个：mvn clean:help
 * 插件详讲：{@link Maven2 }
 *
 *
 * @author welldo
 * @date 2020/9/9
 */
public class Maven1_3 {
}
