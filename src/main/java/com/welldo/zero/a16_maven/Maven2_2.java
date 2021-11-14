package com.welldo.zero.a16_maven;

/**
 * 生命周期和插件详解,高手必备！
 *
 * 插件仓库
 * 与其他maven构件一样，插件构件也是基于坐标存储在maven仓库中，有需要的时候，maven会从本地查找插件，
 * 如果不存在，则到远程仓库查找，找到了以后下载到本地仓库，然后使用。
 *
 * 插件的是在pluginRepositories->pluginRepository元素中配置的，如下：
 *
 * <pluginRepositories>
 *     <pluginRepository>
 *         <id>myplugin-repository</id>
 *         <url>http://repo1.maven.org/maven2/</url>
 *         <releases>
 *             <enabled>true</enabled>
 *         </releases>
 *     </pluginRepository>
 * </pluginRepositories>
 *
 *
 *
 *
 *
 * @author welldo
 * @date 2020/9/9
 */
public class Maven2_2 {
}
