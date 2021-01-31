package com.welldo.zero.maven_16;

/**
 * 生命周期和插件详解,高手必备！
 *
 * 0. Maven不但有标准化的项目结构，而且还有一套标准化的构建流程，
 *
 * 1.使用Maven时，我们首先要了解什么是Maven的生命周期（lifecycle）。 生命周期由一系列阶段（phase）构成，
 * 具体包含哪些阶段phase是maven已经约定好的，但是每个阶段phase具体需要做什么，是用户可以自己指定的。
 * 多个阶段是有先后顺序的，并且后面的阶段依赖于前面的阶段
 *
 *
 * Maven有三套相互独立的,没有依赖关系的生命周期(Lifecycle )：
 *      Clean ：做一些清理工作；
 *          包含phase: pre-clean, clean(移除所有上一次构建生成的文件), post-clean
 *      Default ：构建的核心部分、编译、测试、打包、部署等；
 *          包含phase: 见1.1
 *      Site ：生成项目报告、站点、发布站点；(一般用不上)
 *          包含phase: pre-site, site, post-site, site-deploy
 *
 * 1.1 以内置的生命周期default为例，它包含以下23个phase：
 *      validate
 *      initialize          初始化：比如设置属性值。
 *      generate-sources
 *      process-sources
 *      generate-resources
 *      process-resources   复制并处理资源文件，至目标目录，准备打包；
 *      compile             编译项目的源代码；
 *      process-classes     处理编译生成的文件，对class文件做字节码改善优化。
 *      generate-test-sources
 *      process-test-sources    复制并处理资源文件，至目标测试目录；
 *      generate-test-resources
 *      process-test-resources
 *      test-compile
 *      process-test-classes
 *      test                    测试：使用合适的单元测试框架运行测试（Juint是其中之一）
 *      prepare-package
 *      package                 将编译好的代码，打包成可发布的格式，如 JAR，war
 *      pre-integration-test
 *      integration-test
 *      post-integration-test
 *      verify
 *      install         将包安装至本地仓库，以让其它项目依赖；
 *      deploy          将最终的包复制到远程的仓库，以让其它开发人员与项目共享；
 *
 * ========================================================================================
 * mvn命令语法：
 * mvn phase阶段名
 * (因为没有重名的phase, 所以不用指定lifecycle)（在当前项目pom.xml所在目录中执行mvn命令）
 *
 * 如果我们运行mvn package，Maven就会执行 default 生命周期，它会从开始(validate)一直运行到package这个phase为止：
 *      validate *      ... *      package
 *
 * 如果我们运行mvn compile，Maven也会执行default生命周期，但这次它只会运行到compile，即以下几个phase：
 *      validate *      ... *      compile
 *
 * 1.2
 * Maven另一个常用的生命周期是Clean，它会执行3个phase：
 *      pre-clean
 *      clean （注意这个clean不是lifecycle而是phase）
 *      post-clean
 *
 * ========================================================================================
 * 2.
 * 更复杂的例子是指定多个phase，例如，运行mvn clean package，
 * Maven先执行clean生命周期并运行到clean这个phase，然后执行default生命周期并运行到package这个phase，实际执行的phase如下：
 *      pre-clean
 *      clean （注意这个clean是phase）
 *      validate *      ... *      package
 *
 * 在实际开发过程中，经常使用的命令有：
 *      mvn clean：清理所有生成的class和jar；
 *      mvn clean compile：先清理，再执行到compile；
 *      mvn clean test：先清理，再执行到test;
 *      mvn clean package：先清理，再执行到package。
 *
 * 大多数phase在执行过程中，因为我们通常没有在pom.xml中配置相关的设置，所以这些phase什么事情都不做。
 * 经常用到的phase其实只有几个：(见IDEA 的maven侧边栏)
 * clean：清理
 * compile：编译
 * test：运行测试
 * package：打包

 * ========================================================================================
 * 执行一个phase又会触发一个或多个goal (是真正干活的, 是最小任务单元)
 *
 * |---------------------------------
 * | 执行的Phase	    对应执行的Goal, goal的命名总是abc（插件名）:xyz（目标名）这种形式。
 * |---------------------------------
 * |  compile	    compiler:compile
 * |---------------------------------
 * |  test	        compiler:testCompile
 * |                 surefire:test
 * |---------------------------------
 * 大多数情况，我们只要指定phase，就默认执行这些phase默认绑定的goal, 因此不必指定goal。
 *
 * 总结:
 * 使用Maven构建项目就是执行lifecycle，执行到指定的phase为止。每个phase会执行自己默认的一个或多个goal。
 *
 *
 * ========================================================================================
 * 3.GOAL
 * 前面说过，每个阶段具体做的事情是由maven插件来完成的。
 * 执行命令后，输出中有很多类似于maven-xxxx-plugin:版本:xxx 这样的内容， 表示运行这个插件，来完成对应阶段的操作，
 * "mvn 阶段" 明明执行的是阶段，但是实际输出中却是插件在干活，
 * 那么阶段是如何和插件关联起来的呢？插件又是什么呢？(见第5节)
 *
 * maven插件主要是为maven中生命周期中的阶段phase服务的
 * maven中只是定义了3套生命周期，以及每套生命周期中有哪些阶段phase，具体每个阶段中执行什么操作，完全是交给插件去干的。
 *
 * 插件目标（GOAL）
 * maven中的插件以jar的方式存在于仓库中，一样的，也是通过坐标进行访问
 * 一个插件可能包含了一个或者多个功能，比如编译代码的插件，可以编译源代码、也可以编译测试代码；
 * 插件中的每个功能就叫做插件的目标（Plugin Goal）
 *
 * 3.1 列出插件所有目标
 *      mvn 插件goupId:插件artifactId[:插件version]:help，
 *          比如：mvn org.apache.maven.plugins:maven-clean-plugin:help
 *      mvn 插件前缀:help
 *
 * 3.2 查看插件目标的参数列表
 *      mvn 插件goupId:插件artifactId[:插件version]:help -Dgoal=目标名称 -Ddetail
 *          比如：mvn org.apache.maven.plugins:maven-clean-plugin:help -Dgoal=help -Ddetail
 *          （没有-Ddetail，则参数列表不会输出）
 *      mvn 插件前缀:help -Dgoal=目标名称 -Ddetail
 *
 * ========================================================================================
 * 4.实战
 * maven中运行测试用例使用到的插件坐标是：
 * <dependency>
 *     <groupId>org.apache.maven.plugins</groupId>
 *     <artifactId>maven-surefire-plugin</artifactId>
 *     <version>2.12.4</version>
 * </dependency>
 *
 * 4.1 我们看一下这个插件有哪些目标：
 * mvn org.apache.maven.plugins:maven-surefire-plugin:help
 * maven-surefire-plugin插件有2个目标help和test，描述中可以看出test目标是用来运行测试用例的。
 *
 *
 * 4.2 我们看一下test目标对应的参数列表：
 * mvn org.apache.maven.plugins:maven-surefire-plugin:help -Dgoal=test -Ddetail
 *
 * test目标对应的参数太多，我们拿skip举例：
 * 看一下skip这个参数说明，默认是false， * 如果设置为true的时候，项目将跳过测试代码的编译和执行，
 * 可以maven.test.skip这个属性来进行命令行传参， * 将其传递给test目标的skip属性，
 * 这个通过-D传递的参数名称就和目标参数名称不一样了，所以需要注意-D后面并不一定是参数名称。
 *
 * 4.3传参的2种方式：插件传参
 * 先看一下不加参数的效果：
 * mvn org.apache.maven.plugins:maven-surefire-plugin:test
 *
 * 加maven.skip.test=true的效果如下：
 * mvn org.apache.maven.plugins:maven-surefire-plugin:test -Dmaven.test.skip=true
 *
 *
 * 4.4传参的2种方式：pom传参
 * 在pom.xml中properties的用户自定义属性中进行配置，如下：
 * <maven.test.skip>true</maven.test.skip>
 *
 *
 * ========================================================================================
 * 5.那么阶段是如何和插件关联起来的呢？
 * 获取插件目标的另外一种方式（含：详细描述信息）
 * mvn help:describe -Dplugin=插件goupId:插件artifactId[:插件version] -Dgoal=目标名称 -Ddetail
 * mvn help:describe -Dplugin=插件前缀 -Dgoal=目标名称 -Ddetail
 *      比如：mvn help:describe -Dplugin=org.apache.maven.plugins:maven-surefire-plugin -Dgoal=test -Ddetail
 *
 * 详细描述信息中，有这么一行，
 *   Language: java
 *   Bound to phase: test（所以我们得知，test-phase和此插件关联起来了）
 *
 * 并且，参数说明中多了一行User property: 属性名称，所以第二种传参的方式：pom.xml中properties中定义的方式指定。
 *
 * maven内部已经提供了很多默认的插件，并且将一些阶段phase和这些插件目标（goal）绑定好了。我们无需再配置。
 * 查看IDEA 右侧边栏
 *
 *
 * ========================================================================================
 * 6.插件前缀
 * 插件的坐标信息过于复杂，所以maven中给插件定义了一些简捷的插件前缀，可以通过插件前缀来运行指定的插件。
 * 可以通过下面命令查看到插件的前缀：
 * mvn help:describe -Dplugin=插件goupId:插件artifactId[:插件version]
 *
 *      比如：mvn help:describe -Dplugin=org.apache.maven.plugins:maven-surefire-plugin
 *      得知，前缀为：surefire
 *
 * ========================================================================================
 * 4.其实我们类比一下就明白了：
 *      lifecycle相当于Java的package，它包含一个或多个phase；
 *      phase相当于Java的class，它包含一个或多个goal；
 *      goal相当于class的method，它其实才是真正干活的, 是最小任务单元
 *
 *
 * @author welldo
 * @date 2020/9/9
 */
public class Maven2 {
}
