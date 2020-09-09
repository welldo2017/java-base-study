package com.welldo.zero.maven_16;

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
 *  <groupId>com.welldo.learnjava</groupId>
 *  <artifactId>parent</artifactId>
 *  <version>1.0</version>
 *  <packaging>pom</packaging>
 *
 * 注意到parent的<packaging>是pom而不是jar，因为parent本身不含任何Java代码。编写parent的pom.xml只是为了在各个模块中减少重复的配置。
 *
 * 模块A就可以简化为：
 *  <parent>
 *      <groupId>com.itranswarp.learnjava</groupId>
 *      <artifactId>parent</artifactId>
 *      <version>1.0</version>
 *      <relativePath>../parent/pom.xml</relativePath>
 *  </parent
 *  <artifactId>module-a</artifactId>
 *  <packaging>jar</packaging>
 *
 *
 * 2.
 * @author welldo
 * @date 2020/9/9
 */
public class Maven5 {
}
