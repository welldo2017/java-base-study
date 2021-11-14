package com.welldo.zero.a16_maven;

/**
 * Maven介绍
 * (教程: http://www.itsoku.com/article/235 )
 *
 * 0.
 * Maven是专门为Java项目打造的管理和构建工具，它的主要功能有：
 *      提供了一套标准化的项目结构；
 *      提供了一套标准化的构建流程（编译，测试，打包，发布……）；
 *      提供了一套依赖管理机制。
 *
 *
 * 1.核心配置文件
 * 运行的时候,会加载启动的配置文件settings.xml，这个文件默认在M2_HOME/conf目录，
 * 一般我们会拷贝一个放在~/.m2目录中，
 * 前者是全局范围的配置文件，整个机器上所有用户都会受到该配置的影响，
 * 而后者是用户范围级别的，只有当前用户才会受到该配置的影响。推荐使用用户级别的，将其放在~/.m2目录，
 *
 *========================================================================================
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
 *          ├── classes         (编译输出目录)
 *          ├── test-classes	(测试编译输出目录)
 *
 *
 *========================================================================================
 * 1.5 最关键的一个项目描述文件pom.xml，POM(Project Object Model，项目对象模型)是Maven工程的基本工作单元
 *
 *  <modelVersion>4.0.0 模型版本</modelVersion>
 *
 * 	<groupId>com.jd 类似于Java的包名，通常是公司或组织名称</groupId>
 * 	<artifactId>sso 类似于Java的类名，是项目名称，唯一id </artifactId>
 * 	<version>1.1.0 -SNAPSHOT 版本号 </version>
 *
 *  package：打包方式，可选（jar、war、ear、pom、maven-plugin），(可以省略，默认为jar)
 *  如果需要把项目打成jar包，采用java -jar去运行这个jar包，那这个值为jar；
 *  若当前是一个web项目，需要打成war包部署到tomcat中，那这个值就是war
 * 	<packaging>jar</packaging>
 *
 * 	<properties>   ...	</properties>
 *
 * 	<dependencies>
 *         <dependency>
 *             声明一个依赖后，Maven就会自动下载这个依赖包并把它放到classpath中。
 *             <groupId>commons-logging</groupId>
 *             <artifactId>commons-logging</artifactId>
 *             <version>1.2</version>
 *         </dependency>
 * 	</dependencies>
 *
 *
 * ========================================================================================
 * 0. dependencies中每个标签的解释
 *      <dependencies>
 *         <dependency>
 *             <groupId></groupId>
 *             <artifactId></artifactId>
 *             <version></version>
 *
 *             <packaging></packaging>
 *
 *             <type></type>
 *             <scope></scope>
 *             <optional>true</optional>
 *             <exclusions>
 *                 <exclusion></exclusion>
 *                 <exclusion></exclusion>
 *             </exclusions>
 *         </dependency>
 *     </dependencies>
 * dependencies元素中可以包含多个dependency，每个dependency就表示当前项目需要依赖的一个构件的信息
 * groupId、artifactId、version是定位一个构件必须要提供的信息，这3个是必须的
 * packaging：打包方式，jar、war、ear、pom、maven-plugin，(可以省略，默认为jar)
 * type：表示所要依赖的构件的类型，对应于被依赖的构件的 packaging
 *      大部分情况下，该元素不被声明，默认值为jar，表示被依赖的构件是一个jar包。
 * scope：依赖的范围，见下文
 * optional：标记依赖是否可选，后面详解 {@link Maven1_5}
 * exclusions：用来排除传递性的依赖,后面详解 {@link Maven1_5}
 *
 *
 * ========================================================================================
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
 *   |system	谨慎使用，不推荐，不举例    	                               |
 *   |import	比较特殊,后面详讲                                         |
 *   -------------------------------------------------------------------
 *
 * 2.1 默认的compile是最常用的，Maven会把这种类型的依赖直接放入classpath。
 * 编译源代码,运行源代码;编译测试代码，运行测试代码,都可以用到
 *
 * 2.2 test依赖表示仅在测试时使用，线上正常运行时并不需要。
 * (即: 此依赖only for编译测试代码，运行测试代码；编译源代码/运行源代码 无法使用此依赖)
 * 比如JUnit.jar：
 *      <dependency>
 *          <groupId>org.junit.jupiter</groupId>
 *          <artifactId>junit-jupiter-api</artifactId>
 *          <version>5.3.2</version>
 *          <scope>test</scope>
 *      </dependency>
 *
 * 2.3 runtime依赖表示编译时不需要，但运行时需要。最典型的runtime依赖是JDBC驱动，例如MySQL驱动：
 *      编译时，只编译jdbc接口，和具体的jdbc驱动实现无关；运行的时候才需要具体的jdbc驱动实现，比如mysql
 *      <dependency>
 *          <groupId>mysql</groupId>
 *          <artifactId>mysql-connector-java</artifactId>
 *          <version>5.1.48</version>
 *          <scope>runtime</scope>
 *      </dependency>
 *
 * 2.4 provided依赖,表示项目的运行环境中(通常是linux)已经提供了此构件 *     所以打包时,不会将此依赖包打入到最终jar包中
 *    (provided依赖表示编译时需要，但运行时不需要。这句话的意思是，编译时需要“本地”的此依赖，运行时不需要“本地”的此依赖，因为在linux中运行，想用也用不上)
 *     最典型的provided依赖是Servlet API，编译的时候需要，但是运行时，Servlet服务器内置了相关的jar，所以运行期不需要
 *       <dependency>
 *           <groupId>javax.servlet</groupId>
 *           <artifactId>javax.servlet-api</artifactId>
 *           <version>4.0.0</version>
 *           <scope>provided</scope>
 *       </dependency>
 *
 * 2.5 system
 * 谨慎使用，不推荐，不举例
 *
 * 2.6 import
 * 这个比较特殊，后面详讲，springboot和springcloud中用到的比较多。
 *
 * @author welldo
 * @date 2020/9/9
 */
public class Maven1 {
}
