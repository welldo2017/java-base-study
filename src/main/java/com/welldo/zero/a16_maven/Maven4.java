package com.welldo.zero.a16_maven;

/**
 *
 * pom中的一些基础配置
 *
 * <properties>
 * 		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
 * 		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
 * 		<maven.compiler.source>11</maven.compiler.source>
 * 		<maven.compiler.target>11</maven.compiler.target>
 * 		<java.version>11</java.version>
 * 	</properties>
 *
 * compiler 的两行, 是告诉maven在compiler这个phase，调用原生插件把这个项目编译成java 11版本的项目。
 * https://maven.apache.org/plugins/maven-compiler-plugin/
 * 繁琐的写法是：
 *
 * <build>
 *  <finalName>file_name</finalName>
 *  <plugins>
 *      <plugin>
 *          <groupId>org.apache.maven.plugins</groupId>
 *          <artifactId>maven-compiler-plugin</artifactId>
 *          <version>3.8.1</version>
 *          <configuration>
 *                <source>${java.version}</source>
 *                <target>${java.version}</target>
 *          </configuration>
 *      </plugin>
 *  </plugins>
 * </build>
 *
 *
 * @author welldo
 * @date 2020/9/9
 */
public class Maven4 {
}
