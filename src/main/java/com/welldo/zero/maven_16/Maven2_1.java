package com.welldo.zero.maven_16;

/**
 * 生命周期和插件详解,高手必备！
 *
 * POM.xml插件配置详解
 *
 * 0.更多maven插件的帮助文档可以参考maven的官方网站， * http://maven.apache.org/plugins/
 *
 * 1.插件目标 goal 共享参数配置, 也就是说这个插件中所有的目标goal共享此参数配置。
 * build->plugins->plugin中配置：
 * <configuration>
 *     <目标参数名>参数值</目标参数名>
 * </configuration>
 * 举例，跳过测试
 *         <plugin>
 *             <groupId>org.apache.maven.plugins</groupId>
 *             <artifactId>maven-surefire-plugin</artifactId>
 *             <version>2.12.4</version>
 *             <configuration>              对插件中所有的目标起效
 *                 <skip>true</skip>
 *             </configuration>
 *         </plugin>
 *
 * 3.插件目标参数配置， 只对当前goal有效
 * project->build->plugins->plugin->executions->execution元素中进行配置
 * <configuration>
 *     <目标参数名>参数值</目标参数名>
 * </configuration>
 *
 * 举例，跳过测试，
 * 自定义了一个绑定，在clean生命周期的pre-clean阶段，绑定了插件maven-surefire-plugin的两个目标test和help，
 * execution元素没有指定id，所以默认id是default。
 *      <plugin>
 *             <groupId>org.apache.maven.plugins</groupId>
 *             <artifactId>maven-surefire-plugin</artifactId>
 *             <version>2.12.4</version>
 *             <executions>
 *                 <execution>
 *                     <goals>
 *                         <goal>test</goal>
 *                         <goal>help</goal>
 *                     </goals>
 *                     <phase>pre-clean</phase>
 *                     <configuration>      这个地方配置只对当前任务有效
 *                         <skip>true</skip>
 *                     </configuration>
 *                 </execution>
 *             </executions>
 *         </plugin>
 *
 * 4.插件的默认groupId
 * 在pom.xml中配置插件的时候，如果是官方的插件，可以省略groupId。
 * 即省略： <groupId>org.apache.maven.plugins</groupId>
 *
 *          <plugin>
 *                 <artifactId>maven-compiler-plugin</artifactId>
 *                 <version>3.1</version>
 *                 <configuration>
 *                     <compilerVersion>1.8</compilerVersion>
 *                     <source>1.8</source>
 *                     <target>1.8</target>
 *                 </configuration>
 *             </plugin>
 * 上面这个插件用于编译代码的，编译代码的时候需要指定编译器的版本，源码的版本，目标代码的版本，都是用的是1.8。
 *
 *
 * 5.我们的pom.xml默认会继承maven顶级的一个父类pom.xml，
 * 顶级的pom.xml中指定了很多默认的配置，如生命周期中的阶段和很多插件的绑定，这些如果我们想看到，到哪里看呢？
 * mvn命令在项目中执行的时候，我们的pom.xml和父类的pom.xml最终会进行合并，
 * 当我们的pom.xml写的比较复杂的时候，最终合并之后是什么效果呢，我们可以通过下面这个命令查看：
 * mvn help:effective-pom
 *
 *
 * @author welldo
 * @date 2020/9/9
 */
public class Maven2_1 {
}
