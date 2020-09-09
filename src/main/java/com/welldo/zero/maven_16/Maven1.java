package com.welldo.zero.maven_16;

/**
 * Maven介绍
 *
 * 0.
 * Maven是专门为Java项目打造的管理和构建工具，它的主要功能有：
 *      提供了一套标准化的项目结构；
 *      提供了一套标准化的构建流程（编译，测试，打包，发布……）；
 *      提供了一套依赖管理机制。
 *
 *
 * 1.一个使用Maven管理的普通的Java项目，它的目录结构默认如下：
 *      a-maven-project         (项目名)
 *      ├── pom.xml             (项目描述文件)
 *      ├── src
 *      │   ├── main
 *      │   │   ├── java        (存放Java源码)
 *      │   │   └── resources   (存放资源文件)
 *      │   └── test
 *      │       ├── java        (存放测试源码)
 *      │       └── resources   (存放测试资源)
 *      └── target              (编译、打包生成的文件)
 *
 *
 * 2.依赖关系
 * Maven定义了几种依赖关系, 如下
 *
 *   -------------------------------------------------------------------
 *   |scope	         说明	                                 示例       |
 *   |-----------------------------------------------------------------|
 *   |compile	编译时需要用到该jar包（默认）	                commons-logging|
 *   |test	    编译Test时需要用到该jar包	                junit          |
 *   |runtime	编译时不需要，但运行时需要用到	            mysql          |
 *   |provided	编译时需要用到，但运行时由JDK或某个服务器提供	servlet-api    |
 *   -------------------------------------------------------------------
 *
 * 2.1 默认的compile是最常用的，Maven会把这种类型的依赖直接放入classpath。
 *
 * 2.2 test依赖表示仅在测试时使用，正常运行时并不需要。最常用的test依赖就是JUnit：
 *      <dependency>
 *          <groupId>org.junit.jupiter</groupId>
 *          <artifactId>junit-jupiter-api</artifactId>
 *          <version>5.3.2</version>
 *          <scope>test</scope>
 *      </dependency>
 *
 * 2.3 runtime依赖表示编译时不需要，但运行时需要。最典型的runtime依赖是JDBC驱动，例如MySQL驱动：
 *      <dependency>
 *          <groupId>mysql</groupId>
 *          <artifactId>mysql-connector-java</artifactId>
 *          <version>5.1.48</version>
 *          <scope>runtime</scope>
 *      </dependency>
 *
 * 2.4 provided依赖表示编译时需要，但运行时不需要。
 *     最典型的provided依赖是Servlet API，编译的时候需要，但是运行时，Servlet服务器内置了相关的jar，所以运行期不需要
 *       <dependency>
 *           <groupId>javax.servlet</groupId>
 *           <artifactId>javax.servlet-api</artifactId>
 *           <version>4.0.0</version>
 *           <scope>provided</scope>
 *       </dependency>
 *
 *
 *
 * 3.Maven从何处下载所需的依赖?
 *     答案是Maven维护了一个中央仓库（ https://repo1.maven.org），
 *     所有第三方库将自身的jar以及相关信息上传至中央仓库，Maven就可以从中央仓库把所需依赖下载到你的电脑
 *
 *     Maven并不会每次都从中央仓库下载jar包。
 *     一个jar包一旦被下载过，就会被Maven自动缓存在本地目录（用户主目录的.m2目录），
 *     所以，除了第一次编译时因为下载需要时间会比较慢，后续过程因为有本地缓存，并不会重复下载相同的jar包。
 *
 *     注：只有以-SNAPSHOT结尾的版本号会被Maven视为开发版本，开发版本每次都会重复下载，
 *     这种SNAPSHOT版本只能用于内部私有的Maven repo，公开发布的版本不允许出现SNAPSHOT。
 *
 *
 * 4.Maven镜像
 *   如果访问Maven的中央仓库非常慢，我们可以选择一个速度较快的Maven的镜像仓库。Maven镜像仓库定期从中央仓库同步：
 *               slow     ┌───────────────────┐
 *         ┌─────────────>│Maven Central Repo.│
 *         │              └───────────────────┘
 *         │                        │sync
 *         │                        ▼
 *     ┌───────┐  fast    ┌───────────────────┐
 *     │ User  │─────────>│Maven Mirror Repo. │
 *     └───────┘          └───────────────────┘
 *
 *     中国用户可以使用阿里云提供的Maven镜像仓库。在用户主目录下进入.m2目录，创建一个settings.xml配置文件，内容如下：
 *     <settings>
 *         <mirrors>
 *             <mirror>
 *                 <id>aliyun</id>
 *                 <name>aliyun</name>
 *                 <mirrorOf>central</mirrorOf>
 *                 <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
 *             </mirror>
 *         </mirrors>
 *     </settings>
 *
 *
 *
 * 5. 搜索第三方组件
 *     最后一个问题：如果我们要引用一个第三方组件，比如okhttp，如何确切地获得它的groupId、artifactId和version？
 *     方法是通过 search.maven.org 搜索关键字，找到对应的组件后，直接复制
 *
 *
 *
 * @author welldo
 * @date 2020/9/9
 */
public class Maven1 {
}
