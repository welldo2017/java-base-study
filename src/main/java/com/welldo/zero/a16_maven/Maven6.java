package com.welldo.zero.a16_maven;

/**
 *
 * 6.多环境构建
 *
 * <properties>
 *         <!-- maven中不需要自定义的5类属性。可以直接拿来使用的。
 *          1.内置属性 ${basedir}：${version}
 *          2.POM属性
 *          3.Settings属性, 这种属性以settings.开头来引用~/.m2/settings.xml中的内容
 *          4.java系统属性
 *          5.环境变量属性,所有的环境变量都可以使用env.开头的方式来进行引用，如：${env.JAVA_HOME}
 *
 *          输入命令 mvn help:effective-pom -pl com.welldo:maven-test > 1.xml
 *          将会输出下列所有属性的值.
 *          -->
 *
 *         <!-- 1.内置属性 ${basedir}, ${version}
 *         两者都有等价属性,不做演示-->
 *
 *
 *
 *
 *         <!-- 2.pom属性 -->
 *         <!-- 表示项目根目录，即包含pom.xml文件的目录, 与内置属性 ${basedir}等价 -->
 *         <inside11>${project.basedir}</inside11>
 *
 *
 *
 *         <!-- 项目的主源码目录，默认为 全路径的 src/main/java/ -->j
 *         <a>${project.build.sourceDirectory}</a>
 *         <!-- 项目的测试源码目录，默认为 全路径的 src/test/java/ -->
 *         <b>${project.build.testSourceDirectory}</b>
 *         <!-- 项目构建输出目录，默认为 全路径的 target/ -->
 *         <c>${project.build.directory}</c>
 *         <!-- 项目主代码编译输出目录，默认为 全路径的 target/classes -->
 *         <d>${project.build.outputDirectory}</d>
 *         <!-- 项目测试代码编译输出目录，默认为 全路径的 target/test-classes -->
 *         <e>${project.build.testOutputDirectory}</e>
 *
 *         <!-- 项目的groupId -->
 *         <f>${project.groupId}</f>
 *         <!-- 项目的artifactId -->
 *         <g>${project.artifactId}</g>
 *         <!-- 项目的version，与 内置属性${version}等价-->
 *         <h>${project.version}</h>
 *         <!-- 项目打包输出文件的名称，默认为${project.artifactId}-${project.version} -->
 *         <i>${project.build.finalName}</i>
 *
 *
 *         <!-- 3.setting属性 -->
 *         <!-- 获取本地仓库地址, 无法获取, 为什么-->
 *         <a1>${settings.localRepository}</a1>
 *
 *
 *         <!-- 4.系统属性 -->
 *         <!-- 用户目录, c盘用户的家目录 -->
 *         <a2>${user.home}</a2>
 *
 *
 *         <!-- 5.环境变量属性，获取环境变量JAVA_HOME的值 -->
 *         <a3>${env.JAVA_HOME}</a3>
 *
 *
 *         <!-- 资源文件复制过程中, 开启动态替换配置, -->
 *         <jdbc.url>url from properties</jdbc.url>
 *         <jdbc.username>root from properties</jdbc.username>
 *         <jdbc.password>root from properties</jdbc.password>
 *         <!-- 指定资源文件复制过程中采用的编码方式 -->
 *         <encoding>UTF-8</encoding>
 *
 *
 *     </properties>
 *
 *
 *     <dependencies>
 *         <dependency>
 *             <groupId>com.alibaba</groupId>
 *             <artifactId>fastjson</artifactId>
 *             <version>1.2.62</version>
 *         </dependency>
 *     </dependencies>
 *
 *
 *
 *     <!-- 将此jar包, 放到私服中去-->
 *     <distributionManagement>
 *         <repository>
 *             <id>maven-releases</id>
 *             <url>http://192.168.34.141:8081/repository/maven-releases/</url>
 *             <name>存放/下载稳定版本的构件</name>
 *         </repository>
 *         <snapshotRepository>
 *             <id>maven-snapshots</id>
 *             <url>http://192.168.34.141:8081/repository/maven-snapshots/</url>
 *             <name>下载快照版本的构件</name>
 *         </snapshotRepository>
 *     </distributionManagement>
 *
 *
 *     <!-- =====================  使用profiles 处理多环境构建问题 ===================== -->
 *     <!-- profiles的properties 和 顶级properties 中,如果定义了同样的属性, 谁激活了,就用谁的值 -->
 *     <!-- 选择环境4种方式
 *       1.开启默认环境配置
 *       2.在ide的maven工具栏中选中指定的profile即可,
 *
 *       3.命令行方式: 使用-P 命令, 后面跟的是pom.xml中profile的id，表示需要使用那套环境进行构建,
 *       比如 mvn clean package -pl com.welldo:maven-test -Pwd-prod (没有空格)
 *
 *       可以在-P参数后跟多个环境的id，逗号隔开; 当使用多套环境的时候，多套环境中的maven属性会进行合并，如果属性重复，后面的会覆盖前面的。
 *
 *       4.命令行方式: 使用-D命令, 通过maven属性来控制环境的开启,
 *       比如 mvn clean package -pl com.welldo:maven-test -Denv=env_test(没有空格)
 *       -->
 *     <profiles>
 *         <!-- profiles 也可以写在setting文件中,一样生效 -->
 *         <!-- 开发环境使用的配置 -->
 *         <profile>
 *             <id>wd-dev</id>
 *             <properties>
 *                 <jdbc.url>dev jdbc url from pom profile</jdbc.url>
 *                 <jdbc.username>dev jdbc username from pom profile</jdbc.username>
 *                 <jdbc.password>dev jdbc password from pom profile</jdbc.password>
 *             </properties>
 *
 *             <!-- 选择环境4种方式之1: 如果想要默认, 加上activation标签和activeByDefault子标签 -->
 *             <activation>
 *                 <activeByDefault>false</activeByDefault>
 *             </activation>
 *         </profile>
 *
 *         <!-- 测试环境使用的配置 -->
 *         <profile>
 *             <id>wd-test</id>
 *             <properties>
 *                 <jdbc.url>test jdbc url from pom profile</jdbc.url>
 *                 <jdbc.username>test jdbc username from pom profile</jdbc.username>
 *                 <jdbc.password>test jdbc password from pom profile</jdbc.password>
 *             </properties>
 *
 *
 *             <!-- 选择环境4种方式之4: 通过maven属性来控制环境的开启 -->
 *             <!-- activation中可以添加 一个property-name-value标签,
 *             -使用命令 -D指定一些属性的值，-D后面的属性会和name、value进行匹配，匹配成功的环境都会被开启。
 *              比如 mvn clean package -pl com.welldo:maven-test -Denv=env_test
 *              再查看配置文件, 会发现此环境生效了-->
 *             <activation>
 *                 <property>
 *                     <name>env</name>
 *                     <value>env_test</value>
 *                 </property>
 *             </activation>
 *         </profile>
 *
 *         <!-- 线上环境使用的配置 -->
 *         <profile>
 *             <id>wd-prod</id>
 *             <properties>
 *                 <jdbc.url>prod jdbc url from pom profile</jdbc.url>
 *                 <jdbc.username>prod jdbc username from pom profile</jdbc.username>
 *                 <jdbc.password>prod jdbc password from pom profile</jdbc.password>
 *             </properties>
 *         </profile>
 *
 *
 *         <!-- =====================  使用 外部属性文件xx.properties 处理多环境构建问题 ===================== -->
 *
 *         <!-- 上面的方式,配置是分散在n个pom.xml文件中的,且写死的,
 *         如果运维需要修改数据库的配置的时候，需要去每个模块中去修改pom.xml中的属性，这种操作会让人疯掉的
 *          我们可以将数据库所有的配置放在一个外部文件中,         然后在profile中加入 build>filters>filter 子标签
 *
 *          上面的方式,直接从profile中直接取值, 这种方式,从profile中找配置文件, 再去配置文件中取值,相当于多跳转了一步 -->
 *         <profile>
 *             <id>external</id>
 *             <build>
 *                 <filters>
 *                     <filter>./src/main/config/external.properties</filter>
 *                 </filters>
 *             </build>
 *         </profile>
 *
 *
 *
 *     </profiles>
 *     <!--  查看目前有哪些环境,    命令：mvn help:all-profiles
 *     结果如下:
 *         Profile Id: wd-dev (Active: true , Source: pom)             表示激活
 *         Profile Id: jdk-1.8 (Active: true , Source: settings.xml)   表示激活
 *         Profile Id: wd-test (Active: false , Source: pom)
 *         Profile Id: wd-prod (Active: false , Source: pom)
 *      -->
 *
 *
 *
 *
 *
 *
 *     <!-- 开启动态替换配置，需要在pom.xml中加入下面配置：
 *     注意: 开启动态替换的元素是filtering。 将配置文件中的占位符,替换成 profiles的properties 或者 顶级properties  中定义的值-->
 *     <build>
 *         <resources>
 *             <resource>
 *                 <!-- 指定资源文件的目录 -->
 *                 <directory>${project.basedir}/src/main/resources</directory>
 *                 <!-- 是否开启过滤替换配置，默认是不开启的 -->
 *                 <filtering>true</filtering>
 *
 *                 <!-- 如果有多个资源文件,如果不想让 const 被复制到target/classes目录，需要在资源构建的过程中排除他
 *                 **匹配任意深度的文件路径，*匹配任意个字符。-->
 *                 <includes>
 *                     <include>星星/jdbc.properties</include>
 *                 </includes>
 *                 <excludes>
 *                     <exclude>星星/const.properties</exclude>
 *                 </excludes>
 *             </resource>
 *
 *             <!-- 如果只想要某个文件被复制到target下面，但是不要去替换里面的内容，此时需要配置多个resouce元素了, 并且不开启filtering -->
 *         </resources>
 *
 *         <testResources>
 *             <testResource>
 *                 <!-- 指定资源文件的目录 -->
 *                 <directory>${project.basedir}/src/test/resources</directory>
 *                 <!-- 是否开启过滤替换配置，默认是不开启的 -->
 *                 <filtering>true</filtering>
 *             </testResource>
 *         </testResources>
 *     </build>
 *
 *
 * @author welldo
 * @date 2020/9/9
 */
public class Maven6 {
}
