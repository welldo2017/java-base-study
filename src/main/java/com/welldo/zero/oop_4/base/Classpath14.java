package com.welldo.zero.oop_4.base;

import com.welldo.zero.maven_16.Maven1;

/**
 * 0.
 * 到底什么是classpath？
 *
 * 1.
 * classpath是JVM用到的一个环境变量，它用来指示JVM如何搜索class。
 *
 * 假如源码文件是 *.java，而编译后的 *.class文件才是真正可以被JVM执行的字节码。
 * 因此，JVM需要知道，如果要加载一个abc.xyz.Hello的类，应该去哪搜索对应的Hello.class文件。
 * 所以，classpath就是一组目录的集合
 * 它设置的搜索路径与操作系统相关。
 *      在Windows系统上，用;分隔，带空格的目录用""括起来，可能长这样：
 *      C:\work\project1\bin;C:\shared;"D:\My Documents\project1\bin"
 *
 *      在Linux系统上，用:分隔，可能长这样：
 *      /usr/shared:/usr/local/bin:/home/liaoxuefeng/bin
 *
 *
 * 2.
 * 假设,我们现在的classpath是 .;C:\work\project1\bin;C:\shared，
 * 当JVM在加载abc.xyz.Hello这个类时，会依次查找：
 *      <当前目录>\abc\xyz\Hello.class
 *      C:\work\project1\bin\abc\xyz\Hello.class
 *      C:\shared\abc\xyz\Hello.class
 * 如果JVM在某个路径下找到了对应的class文件，就不再往后继续搜索。如果所有路径下都没有找到，就报错。
 *
 *
 * 3.
 * a. 在系统环境变量中设置classpath环境变量;  !!! 不推荐,那样会污染整个系统环境
 * b. 在启动JVM时设置classpath变量，推荐。
 *      实际上就是给java命令传入-classpath或-cp参数：
 *      java -classpath .;C:\work\project1\bin;C:\shared abc.xyz.Hello
 * c. 如果上面两个都没有设置，那么JVM默认的classpath为.，即当前目录
 *
 * 在IDE中运行Java程序，IDE自动传入的-cp参数是当前工程的bin目录(或者target/classes目录)和引入的jar包。
 *
 *
 * 4.
 * 通常，我们在自己编写的代码中，会引用Java核心库的class，例如，String、ArrayList等。这些class应该上哪去找？
 * 有很多“如何设置classpath”的文章会告诉你把JVM自带的rt.jar放入classpath，
 * 但事实上，根本不需要任何设置，JVM怎么可能笨到连自己的核心库在哪都不知道？
 * so, 不要设置classpath！默认的当前目录.对于绝大多数情况都够用了。
 *
 *
 * 5. jar包
 * (jar包实际上就是一个zip格式的压缩文件)
 * 如果有很多.class文件，散落在各层目录中，肯定不便于管理。如果能把目录打一个包，变成一个文件，就方便多了。
 * jar包就是干这个事的，它可以把某个目录，以及该目录下的所有文件（包括.class文件和其他文件）都打到一个jar文件中，这样一来，无论是备份，还是发给客户，就简单多了。
 *
 * 如何创建jar包？
 *      jar包就是zip包，所以，找到正确的目录，右键，“发送到”，“压缩(zipped)文件夹”，就制作了一个zip文件。然后，把后缀从.zip改为.jar
 *
 *      !!! 特别注意:jar包里的第一层目录
 *      假如一个类的全类名是 com.welldo.zero.oop_4.base.Classpath14
 *      那么,jar包里的第一层目录,应该是com
 *
 * 如何运行?
 *      没有设置主类的情况下:   java -cp ./hello.jar abc.xyz.Hello
 *      如何设置主类? 看6
 *
 * 6.如何设置主类?
 * jar包还可以包含一个特殊的/META-INF/MANIFEST.MF文件，MANIFEST.MF是纯文本，可以指定Main-Class和其它信息。
 * JVM会自动读取这个MANIFEST.MF文件，
 * 如果存在Main-Class，我们就不必在命令行指定启动的类名，而是用更方便的命令：
 *      java -jar abc.xyz.hello.jar
 * 请查看pom文件中的栗子
 *
 * 7.
 * jar包还可以包含其它jar包，这个时候，就需要在MANIFEST.MF文件里配置classpath了。
 * 在大型项目中，不可能手动编写MANIFEST.MF文件，再手动创建zip包。
 * Java社区提供了大量的开源构建工具，例如Maven，可以非常方便地创建jar包。{@link Maven1}
 *
 * @author welldo
 * @date 2020/8/12
 */
public class Classpath14 {
    public static void main(String[] args) {
        System.out.println("i can set the classpath");
    }
}
