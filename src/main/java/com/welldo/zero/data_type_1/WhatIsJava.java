package com.welldo.zero.data_type_1;

/**
 * 0.简介
 * Java是编译型语言, 确切地说,是介于编译型语言和解释型语言之间。
 *
 *  编译型语言如C、C++，代码是直接编译成机器码执行，但是不同的平台（x86、ARM等）CPU的指令集不同，因此，需要编译出每一种平台的对应机器码。
 *
 *  解释型语言如Python、Ruby没有这个问题，可以由解释器直接加载源码然后运行，代价是运行效率太低。
 *
 *  而Java是将代码编译成一种“字节码”，它类似于抽象的CPU指令，
 *  然后，针对不同平台编写虚拟机，不同平台的虚拟机负责加载字节码并执行，这样就实现了“一次编写，到处运行”的效果。
 *
 *  对于虚拟机，需要为每个平台分别开发。
 *  为了保证不同平台、不同公司开发的虚拟机都能正确执行Java字节码，SUN公司制定了一系列的Java虚拟机规范。
 *
 *  从实践的角度看，JVM的兼容性做得非常好，低版本的Java字节码完全可以正常运行在高版本的JVM上。
 *
 *
 * 1.名词解释
 *
 * JRE：Java Runtime Environment = JVM + 运行时库
 *      Java源码，要编译成Java字节码，就需要JDK，因为JDK除了包含JRE，还提供了编译器、调试器等开发工具。
 *
 * JDK：Java Development Kit
 *
 * 二者关系如下：
 *   ┌─    ┌──────────────────────────────────┐
 *   │     │     Compiler, debugger, etc.     │
 *   │     └──────────────────────────────────┘
 *  JDK ┌─ ┌──────────────────────────────────┐
 *   │  │  │                                  │
 *   │ JRE │      JVM + Runtime Library       │
 *   │  │  │                                  │
 *   └─ └─ └──────────────────────────────────┘
 *         ┌───────┐┌───────┐┌───────┐┌───────┐
 *         │Windows││ Linux ││ macOS ││others │
 *         └───────┘└───────┘└───────┘└───────┘
 *
 * JSR规范：Java Specification Request(不需要知道)
 * JCP组织：Java Community Process(不需要知道)
 * RI：Reference Implementation(不需要知道)
 * TCK：Technology Compatibility Kit(不需要知道)
 *
 *
 *
 * 3. 安装jdk后,需要配置环境变量
 * 把JAVA_HOME的bin目录添加到PATH中是为了在任意文件夹下都可以运行 'java'命令
 * 参考 https://www.java.com/zh_CN/download/help/path.xml
 *
 * 4.JDK
 * JAVA_HOME的bin目录下找到很多可执行文件：
 *      javac：这是Java的编译器，它用于把Java源码文件（以.java后缀结尾）编译为Java字节码文件（以.class后缀结尾）；
 *      java：这个可执行程序其实就是JVM，运行Java程序，就是启动JVM，然后让JVM执行指定的编译后的代码；
 *      jar：用于把一组.class文件打包成一个.jar文件，便于发布；
 *      javadoc：用于从Java源码中自动提取注释并生成文档；
 *      jdb：Java调试器，用于开发阶段的运行调试。
 *
 * 5.如何运行Java程序
 * ┌──────────────────┐
 * │    Hello.java    │(源码)
 * └──────────────────┘
 *           │ compile, 先用javac把Hello.java编译成字节码文件Hello.class
 *           ▼
 * ┌──────────────────┐
 * │   Hello.class    │(字节码)
 * └──────────────────┘
 *           │ execute, 用java命令执行这个字节码文件
 *           ▼
 * ┌──────────────────┐
 * │    Run on JVM    │
 * └──────────────────┘
 *
 * @author welldo
 * @date 2020/9/15
 */
public class WhatIsJava {
}
