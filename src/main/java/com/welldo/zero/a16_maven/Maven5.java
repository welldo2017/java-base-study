package com.welldo.zero.a16_maven;

/**
 * 模块管理
 *
 * 1.
 * 在软件开发中，把一个大项目分拆为多个模块是降低软件复杂度的有效方法：
 * 对于Maven工程来说，原来是一个大项目：
 *      single-project
 *      ├── pom.xml
 *      └── src
 * 现在可以分拆成3个模块：
 *      mutiple-project
 *      ├── module-a
 *      │   ├── pom.xml
 *      │   └── src
 *      ├── module-b
 *      │   ├── pom.xml
 *      │   └── src
 *      └── module-c
 *          ├── pom.xml
 *          └── src
 *
 *
 * 提取出共同部分作为parent：
 *  <modelVersion>4.0.0</modelVersion>
 *  <groupId>com.welldo.maven</groupId>
 *  <artifactId>parent</artifactId>
 *  <version>1.0</version>
 *  <packaging>pom</packaging>
 *
 * 注意到parent的<packaging>是pom而不是jar，因为parent本身不含任何Java代码。编写parent的pom.xml只是为了在各个模块中减少重复的配置。
 *
 * 模块A就可以简化为：
 *  <parent>
 *      <groupId>com.welldo.maven</groupId>
 *      <artifactId>parent</artifactId>
 *      <version>1.0</version>
 *      <relativePath>../parent/pom.xml</relativePath>  (表示父构件pom.xml相对路径, 可能没有)
 *  </parent>
 *  <modelVersion>4.0.0</modelVersion>
 *
 *  <artifactId>module-a</artifactId>
 *  <packaging>jar</packaging>
 *  他们的groupId、version都是一样的，子构件可以从父pom.xml中继承这些内容，所以如果是一样的情况，可以不写
 *
 *
 * ========================================================================================
 * 2. * 依赖管理(dependencyManagement)
 *
 * 上面的继承存在的一个问题，如果新增一个子构件，都会默认从父构件中继承依赖的一批构建，
 * 父pom.xml中配置的这些构建,可能某个子项目只是想使用其中一个构件，
 * 但是上面的继承关系却把所有的依赖都给传递到子构件中了，这种显然是不合适的。
 *
 * maven中也考虑到了这种情况，可以使用dependencyManagement元素来解决这个问题。
 *  父项目中声明
 *  <dependencyManagement>
 *         <dependencies>
 *             <dependency>
 *                g
 *                a
 *                v
 *             </dependency>
 *             <dependency>
 *                 g
 *                 a
 *                 v
 *             </dependency>
 *         </dependencies>
 *     </dependencyManagement>
 *
 * 在子项目中,按需取用,用哪个,就写哪个,不用写版本
 * <dependencies>
 *     <dependency>
 *         g
 *         a
 *     </dependency>
 * </dependencies>
 *
 *
 * mvn dependency:tree 这个插件可以根据pom.xml的配置，列出构件的依赖树信息。
 *
 *========================================================================================
 * 3.单继承(很少使用)
 * 上面讲解了DM的使用，但是有个问题，只有使用继承的时候，DM中声明的依赖才可能被子项目的pom.xml用到
 *
 * 如果项目有父pom.xml了，但是我想使用另外一个项目dependencyManagement中声明的依赖，此时我们怎么办？
 * 这就是单继承的问题，这种情况在spring-boot、spring-cloud中会遇到
 *
 * 当我们想在项目中使用另外一个构件中dependencyManagement声明的依赖，而又不想继承这个项目的时候，可以在我们的项目中使用加入下面配置：
 * <dependencyManagement>
 *     <dependencies>
 *         <dependency>
 *             <groupId>com.javacode2018</groupId>
 *             <artifactId>javacode2018-parent</artifactId>
 *             <version>1.0-SNAPSHOT</version>
 *             <type>pom</type>
 *             <scope>import</scope>
 *         </dependency>
 *         <dependency>构件2</dependency>
 *         <dependency>构件3</dependency>
 *         <dependency>构件n</dependency>
 *     </dependencies>
 * </dependencyManagement>
 * 上面有两个关键元素：type的值必须是pom，scope元素的值必须是import
 *
 * 这个配置会将 javacode2018-parent 构件中dependencyManagement元素中声明的所有依赖导入到当前pom.xml的dependencyManagement中
 * (javacode2018-parent 需要在pom.xml中执行mvn install将其安装到本地仓库)
 *
 *
 * ========================================================================================
 * 4. 插件管理(dependencyManagement)
 * maven中提供了dependencyManagement来解决继承的问题，同样也提供了解决插件继承问题的pluginManagement元素
 *
 *
 *
 *
 * @author welldo
 * @date 2020/9/9
 */
public class Maven5 {
}
