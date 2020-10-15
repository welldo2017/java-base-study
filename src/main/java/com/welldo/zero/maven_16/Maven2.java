package com.welldo.zero.maven_16;

/**
 * 构建流程
 *
 * 0. * Maven不但有标准化的项目结构，而且还有一套标准化的构建流程，
 *
 * 1.使用Maven时，我们首先要了解什么是Maven的生命周期（lifecycle）。 生命周期由一系列阶段（phase）构成，
 *
 * Maven有三套相互独立的生命周期(Lifecycle )： 参考 https://www.cnblogs.com/zhaiqianfeng/p/4620138.html
 *      Clean ：做一些清理工作；
 *          包含phase: pre-clean, clean, post-clean
 *      Default ：构建的核心部分、编译、测试、打包、部署等；
 *          包含phase: 见1.1
 *      Site ：生成项目报告、站点、发布站点；
 *          包含phase: pre-site, site, post-site, site-deploy
 *
 * 1.1 以内置的生命周期default为例，它包含以下phase：
 *      validate
 *      initialize
 *      generate-sources
 *      process-sources
 *      generate-resources
 *      process-resources
 *      compile
 *      process-classes
 *      generate-test-sources
 *      process-test-sources
 *      generate-test-resources
 *      process-test-resources
 *      test-compile
 *      process-test-classes
 *      test
 *      prepare-package
 *      package
 *      pre-integration-test
 *      integration-test
 *      post-integration-test
 *      verify(只需创建包并将其安装到本地存储库中以供其他项目重用，就可以使用这个)
 *      install
 *      deploy
 *
 * 如果我们运行mvn package，Maven就会执行 default 生命周期，它会从开始一直运行到package这个phase为止：
 *      validate
 *      ...
 *      package
 *
 * 如果我们运行mvn compile，Maven也会执行default生命周期，但这次它只会运行到compile，即以下几个phase：
 *      validate
 *      ...
 *      compile
 *
 * 1.2
 * Maven另一个常用的生命周期是clean，它会执行3个phase：
 *      pre-clean
 *      clean （注意这个clean不是lifecycle而是phase）
 *      post-clean
 * 所以，我们使用mvn这个命令时，后面的参数是phase，Maven根据生命周期运行到指定的phase。
 * (因为没有重名的phase, 所以不用指定lifecycle)
 *
 *
 *
 * 2.
 * 更复杂的例子是指定多个phase，例如，运行mvn clean package，
 * Maven先执行clean生命周期并运行到clean这个phase，然后执行default生命周期并运行到package这个phase，实际执行的phase如下：
 *      pre-clean
 *      clean （注意这个clean是phase）
 *      validate
 *      ...
 *      package
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
 *  compile：编译
 *   test：运行测试
 *    package：打包
 *
 *
 * 3.GOAL
 * 执行一个phase又会触发一个或多个goal (是真正干活的, 是最小任务单元)
 *
 * |---------------------------------
 * | 执行的Phase	    对应执行的Goal
 * |---------------------------------
 * |  compile	    compiler:compile
 * |---------------------------------
 * |  test	        compiler:testCompile
 * |                 surefire:test
 * |---------------------------------
 *
 * 所以, goal的命名总是abc:xyz这种形式。
 * 大多数情况，我们只要指定phase，就默认执行这些phase默认绑定的goal, 因此不必指定goal。
 *
 * 总结:
 * 使用Maven构建项目就是执行lifecycle，执行到指定的phase为止。每个phase会执行自己默认的一个或多个goal。
 *
 *
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
