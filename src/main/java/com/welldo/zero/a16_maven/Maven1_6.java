package com.welldo.zero.a16_maven;

/**
 * 仓库管理
 *
 * 总结:
 * 中央仓库(maven官方):  https://repo.maven.apache.org/maven2
 * 检索站点(maven官方)： https://search.maven.org/
 *
 *
 *========================================================================================
 * 仓库的分类 * 主要分为2大类：
 *      1.本地仓库
 *      2.远程仓库: 中央仓库、私服(公司搭建的)、其他远程仓库(互联网巨头搭建的)
 *
 *========================================================================================
 * 1. 本地仓库
 * maven本地仓库默认地址是 ~/.m2/respository 目录，这个默认我们也可以在~/.m2/settings.xml文件中进行修改,
 *
 * 默认情况下，~/.m2/settings.xml这个文件是不存在的,
 * 我们需要从Maven安装目录中拷贝 conf/settings.xml 文件(不建议直接使用)，将M2_HOME/conf/settings.xml 拷贝到 ~/.m2 目录中
 *
 *
 *========================================================================================
 * 2. 远程仓库
 *
 * 2.1 中央仓库(maven官方, 国外)
 * Maven从何处下载所需的依赖? 答案是中央仓库
 * Maven维护了一个中央仓库（ https://repo.maven.apache.org/maven2），
 * 这个地址是maven社区为我们提供的，是maven内置的一个默认的远程仓库地址，不需要用户去配置。
 *
 * 这个地址在maven安装包的什么地方呢？
 * 比如, 我们使用的是3.6.1，在下面这个位置
 * apache-maven-3.6.1\lib\maven-model-builder-3.6.1.jar\org\apache\maven\model\pom-4.0.0.xml
 * 在pom-4.0.0.xml中，如下：
 * <repositories>
 *     <repository>
 *       <id>central</id>
 *       <name>Central Repository</name>
 *       <url>https://repo.maven.apache.org/maven2</url>
 *       <layout>default</layout>
 *       <snapshots>
 *         <enabled>false</enabled>
 *       </snapshots>
 *     </repository>
 *   </repositories>
 *
 * 所有第三方库将自身的jar以及相关信息上传至中央仓库，Maven就可以从中央仓库把所需依赖下载到你的电脑.
 *     某jar包一旦被下载过，就会被Maven自动缓存在本地仓库
 *     注：以-SNAPSHOT结尾的版本号会被Maven视为开发版本，开发版本每次都会重复下载，
 *
 * 2.2 私服(后面详讲) {@link Maven1_7}
 * 私服也是远程仓库中的一种，我们为什么需要私服呢？
 * 如果一个团队中有几百个人在开发，都使用maven，那么每个人都需要从远程仓库中下载， 这对公司的网络要求也比较高，
 * 为了节省这个宽带和加快下载速度，
 * 在公司内部架设一台服务器，这台服务器起到一个代理的作用，公司里所有开发者去访问这个服务器，
 * 这台服务器将需要的构件返回给我们，
 * 如果这台服务器中没有我们需要的构件，那么这个代理服务器会去远程仓库中查找，然后将其先下载到代理服务器中，然后再返回给开发者本地的仓库。
 *
 * 2.3 其他远程仓库(互联网巨头搭建的)  / 国内中央仓库
 *  中央仓库在国外，访问速度不是特别快，所以有很多大公司,自己搭建了maven仓库服务器，公开出来给其他开发者使用，比如像阿里、网易等等
 *
 *
 *========================================================================================
 * 3. 版本
 * 在发布稳定版之前，会有多个不稳定的测试版本，称为快照版本，用SNAPSHOT表示，
 * 去看看pom.xml文件，默认是快照版本的，如下： * <version>1.0-SNAPSHOT</version>
 * 最终发布的时候，我们需要将-SNAPSHOT去掉，表示这个版本是稳定的，可以直接使用. 这种稳定的版本叫做release版
 *
 *
 *========================================================================================
 * 4. 配置仓库的方式,两种方式
 *
 * 4.1 pom.xml中配置远程仓库，语法如下：(只对当前项目起效)
 * <project>
 *     <repositories>
 *         <repository>                                              (可以使用repository元素声明一个或者多个远程仓库;)
 *             <id>aliyun-releases</id>                              (可以自定义)
 *             <url>https://maven.aliyun.com/repository/public</url>(远程仓库地址,这里是阿里云的maven仓库地址)
 *             <releases>   <enabled>true</enabled>  </releases> (只允许从这个仓库中下载稳定版本)
 *             <snapshots>  <enabled>false</enabled> </snapshots>(不允许从这个仓库中下载快照版本)
 *         </repository>
 *     </repositories>
 * </project>
 *
 * id：远程仓库的标识;中央仓库的id是central，所以添加远程仓库的时候，id不要和中央仓库的id重复，会把中央仓库覆盖掉
 * (!!! 默认会有一个ID是central的官方远程仓库, 即不用手动pom.xml中写出来,见2.1)
 *
 *  编译的时候, 如果某个jar包需要下载, 控制台则会打印出
 *  Downloading from aliyun-releases: https://maven.aliyun.com/repository/public/$groupId/$artifactId/$version/$artifactId-$version.jar
 *
 *
 *
 *
 * 4.2 settings.xml中进行配置镜像，语法如下：(对所有项目起效)
 * <mirror>
 *   <id>mirrorId</id>
 *   <name>Human Readable Name for this Mirror.</name>
 *   <mirrorOf>repositoryId</mirrorOf>
 *   <url>http://my.xxx.com/dd/dds</url>
 * </mirror>
 *
 * id：镜像的id，标识
 * name：镜像的名称，这个相当于一个描述信息，方便大家查看,不重要
 * url：镜像对应的远程仓库的地址
 * mirrorOf：
 * 指定哪些远程仓库的id使用这个镜像，mirrorOf的值对应pom.xml文件中repository元素的id，
 * 就是表示这个镜像是给pom.xml 文件中的哪些远程仓库使用的，
 * 这里面需要列出远程仓库的id，多个之间用逗号隔开，*表示给所有远程仓库做镜像
 *(上面 4.1 在项目中定义远程仓库的时候，pom.xml文件的repository元素中有个id，这个id就是远程仓库的id，
 * 而mirrorOf就是用来配置,哪些远程仓库会走这个镜像去下载构件。)
 *
 *
 * 编译的时候, 如果某个jar包需要下载, 控制台则会打印出
 * Downloading from $id : $url/$groupId/$artifactId/$version/$artifactId-$version.jar
 *
 * mirrorOf的配置有以下几种:
 *      <mirrorOf>*</mirrorOf>
 *      匹配所有远程仓库id，所有远程仓库都会走这个镜像下载构件
 *
 *      <mirrorOf>远程仓库1的id,远程仓库2的id</mirrorOf>
 *      匹配指定的仓库，这些指定的仓库会走这个镜像下载构件
 *
 *      <mirrorOf>*,! repo1</mirrorOf>
 *      匹配所有远程仓库，但是repo1除外，使用感叹号将仓库从匹配中移除。
 *
 * 注意: 镜像仓库完全屏蔽了被镜像的仓库，所以当镜像仓库无法使用的时候，maven是无法自动切换到被镜像的仓库的.* 此时下载构件会失败。
 * (意思是: aliyun仓库完全屏蔽了maven官方仓库,所以当aliyun仓库无法使用的时候，maven是无法自动切换到maven官方仓库的.* 此时下载构件会失败)
 *
 *
 *========================================================================================
 *
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
 * @author welldo
 * @date 2020/9/9
 */
public class Maven1_6 {
}
