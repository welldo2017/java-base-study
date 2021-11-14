package com.welldo.zero.a11_io;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * File对象
 *
 * 0.
 * Java的标准库java.io提供了File对象来操作文件和目录。
 *
 * 1.构造File对象
 * 构造File对象时，既可以传入绝对路径，也可以传入相对路径
 * 注意Windows平台使用\(需要转义为\\)作为路径分隔符. Linux平台使用/作为路径分隔符：
 *
 * File对象有3种形式表示的路径，
 *  一种是getPath()，返回构造方法传入的路径，
 *  一种是getAbsolutePath()，返回绝对路径，
 *  一种是getCanonicalPath，它和绝对路径类似，但是返回的是规范路径。
 *      绝对路径可以表示成C:\Windows\System32\..\notepad.exe，
 *      而规范路径就是把.和..转换成标准的绝对路径后的路径：C:\Windows\notepad.exe。
 *
 * 当前目录 + 相对路径 = 绝对路径
 * (java工程里, 当前目录就是当前工程这个文件夹目录, 本工程当前目录: D:\xxx\xxxxx\java-study )
 * 可以用.表示当前目录，..表示上级目录。
 *
 *
 * 2.
 * 因为Windows和Linux的路径分隔符不同，File对象有一个静态变量用于表示当前平台的系统分隔符：
 *
 * 3.文件和目录
 * File对象既可以表示文件，也可以表示目录。
 * 构造一个File对象，即使传入的文件或目录不存在，代码也不会出错，因为构造一个File对象，并不会导致任何磁盘操作。
 * 只有当我们调用File对象的某些方法的时候，才真正进行磁盘操作。
 *      例如，调用isFile() / isDirectory()，判断该File对象是否是一个已存在的文件/已存在的目录：
 *
 * 4.文件的一般api
 * canRead()：是否可读； * boolean canWrite()：是否可写；
 * boolean canExecute()：是否可执行；(对目录而言，是否可执行表示能否列出它包含的文件和子目录)
 * long length()：文件字节大小 * createNewFile()创建一个新文件
 * delete()删除该文件
 *
 *
 * 5.有时，程序需要读写一些临时文件，
 * File对象提供了createTempFile()来创建一个临时文件，以及deleteOnExit()在JVM退出时自动删除该文件。
 *
 *
 * 6.遍历
 * 当File对象表示一个目录时，可以使用list()和listFiles()列出目录下的文件和子目录名。
 * listFiles()提供了一系列重载方法，可以过滤不想要的文件和目录：
 *
 *
 * 7.目录
 * 如果需要对目录进行复杂的拼接、遍历等操作，使用Path对象更方便。
 *
 * @author welldo
 * @date 2020/8/25
 */
public class FileObject2 {
    public static void main(String[] args) throws IOException, InterruptedException {
        //1.
        File f1 = new File("D:\\hello.txt");//绝对路径
        System.out.println(f1.getPath());
        System.out.println(f1.getAbsolutePath());
        System.out.println(f1.getCanonicalPath());

        System.out.println("11**************");
        File f11 = new File(".\\hello.txt");//相对路径, 可以写成 .\hello.txt 也可以写成 hello.txt
        System.out.println(f11.getPath());
        System.out.println(f11.getAbsolutePath());
        System.out.println(f11.getCanonicalPath());

        System.out.println("12**************");
        File f12 = new File("..\\hello.txt");
        System.out.println(f12.getPath());
        System.out.println(f12.getAbsolutePath());
        System.out.println(f12.getCanonicalPath());


        //2.
        System.out.println("2**************");
        System.out.println(File.separator);


        //3.
        System.out.println("3**************");
        System.out.println(f1.isFile());
        System.out.println(f1.isDirectory());

        //4.
        // System.out.println("4**************");
        // File f4 = new File("D:\\hellotemp.txt");
        // if (f4.createNewFile()) {
        //     System.out.println("创建成功");
        //     Thread.sleep(5000);
        //     if (f4.delete()) {
        //         System.out.println("删除成功");
        //     }
        // }

        //5.
        // System.out.println("5**************");
        // // 提供临时文件的前缀和后缀, 不用提供路径, 它会去专门的临时目录下创建
        // File f5 = File.createTempFile("tmp-", ".txt");
        // f5.deleteOnExit(); // JVM退出时自动删除
        // System.out.println(f5.isFile());
        // System.out.println(f5.getAbsolutePath());
        // Thread.sleep(5000);

        //6.
        System.out.println("6**************");
        File f6 = new File("D:\\MavenRepository");

        File[] fs6 = f6.listFiles();//列出所有文件及子目录
        printFiles(fs6);

        File[] fs61 = f6.listFiles(new FilenameFilter() { // 仅列出.exe文件
            public boolean accept(File dir, String name) {
                return name.endsWith(".bat"); // 返回true表示接受该文件
            }
        });
        printFiles(fs61);



    }

    /**
     * File对象表示目录时,列出下面的文件及子目录, 不递归
     */
    static void printFiles(File[] files) {
        System.out.println("==========");
        if (files != null) {
            for (File f : files) {
                System.out.println(f);
            }
        }
        System.out.println("==========");
    }
}
