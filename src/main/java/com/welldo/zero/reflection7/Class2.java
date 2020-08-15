package com.welldo.zero.reflection7;

import org.apache.commons.logging.LogFactory;

/**
 * Class类
 * 除了int等基本数据类型外，!!! 引用数据类型(类class,接口interface,数组class[]) 全部都是class。
 *
 * 1.加载
 * class是由JVM在执行过程中 "动态加载" 的。JVM在第一次读取到一种class类型时，将其加载进内存。
 * 每加载一种class，JVM就为其创建一个 'Class类型' 的实例，并关联起来。
 * 注意：这里的Class类型是一个名叫Class的类。它长这样：
 * public final class Class {
 *     private Class() {}   //此构造方法是private，只有JVM能创建Class实例，程序员是无法创建Class实例的。
 * }
 *
 * 以String类为例，当JVM加载String类时，它首先读取String.class文件到内存，然后，为String类创建一个Class实例并关联起来：
 * Class cls = new Class(String);
 *
 * 所以，JVM持有的每个Class实例都指向一个数据类型（class或interface）：
 *
 * ┌───────────────────────────┐
 * │      Class Instance       │──────> String
 * ├───────────────────────────┤
 * │name = "java.lang.String"  │
 * └───────────────────────────┘
 * ┌───────────────────────────┐
 * │      Class Instance       │──────> Random
 * ├───────────────────────────┤
 * │name = "java.util.Random"  │
 * └───────────────────────────┘
 * ┌───────────────────────────┐
 * │      Class Instance       │──────> Runnable
 * ├───────────────────────────┤
 * │name = "java.lang.Runnable"│
 * └───────────────────────────┘
 *
 *
 * 一个Class实例包含了该class的所有完整信息：
 * JVM为每个加载的class创建了对应的Class实例，并在实例中保存了该class的所有信息，包括类名、包名、父类、实现的接口、所有方法、字段等，
 * 因此，如果获取了某个Class实例，我们就可以通过这个Class实例获取到该实例对应的class的所有信息。
 * ┌───────────────────────────┐
 * │      Class Instance       │──────> String
 * ├───────────────────────────┤
 * │name = "java.lang.String"  │
 * ├───────────────────────────┤
 * │package = "java.lang"      │
 * ├───────────────────────────┤
 * │super = "java.lang.Object" │
 * ├───────────────────────────┤
 * │interface = CharSequence...│
 * ├───────────────────────────┤
 * │field = value[],hash,...   │
 * ├───────────────────────────┤
 * │method = indexOf()...      │
 * └───────────────────────────┘
 *
 * !!!
 * 这种通过Class实例获取class信息的方法称为反射（Reflection）。
 *
 * 如何获取一个class的Class实例？有三个方法：
 * 1. 直接通过一个class的静态变量class获取：
 *  Class cls = String.class;
 *
 * 2.方法二：如果我们有一个实例变量，可以通过该实例变量提供的getClass()方法获取：
 * String s = "Hello";
 * Class cls = s.getClass();
 *
 * 3.方法三：如果知道一个class的完整类名，可以通过静态方法Class.forName()获取：
 * Class cls = Class.forName("java.lang.String");
 *
 * !!! 因为Class实例在JVM中是唯一的，所以，上述方法获取的Class实例是同一个实例。可以用==比较两个Class实例：
 *
 * 5. !!!
 * 注意一下Class实例比较和instanceof的差别：
 * 用instanceof不但匹配指定类型，还匹配指定类型的子类。而用==判断class实例可以精确地判断数据类型，但不能作子类型比较。
 *
 * Integer n = new Integer(123);
 * boolean b1 = n instanceof Integer; // true，因为n是Integer类型
 * boolean b2 = n instanceof Number; // true，因为n是Number类型的子类
 * boolean b3 = n.getClass() == Integer.class; // true，因为n.getClass()返回Integer.class
 * boolean b4 = n.getClass() == Number.class; // false，因为Integer.class!=Number.class
 *
 * 通常情况下，我们应该用instanceof判断数据类型，
 * 因为面向抽象编程的时候，我们不关心具体的子类型。
 * 只有在需要精确判断一个类型是不是某个class的时候，我们才使用==判断class实例。
 *
 * 6.
 * 反射的目的是为了获得某个实例的信息。
 * void printObjectInfo(Object obj) {
 *     Class cls = obj.getClass();
 * }
 *
 * 7.
 * 如果获取到了一个Class实例，我们就可以通过该Class实例来创建对应类型的实例：
 * Class cls = String.class;// 获取String的Class实例:
 * String s = (String) cls.newInstance();// 创建一个String实例:
 *
 * 上述代码相当于new String(), 它的局限是：只能调用public的无参数构造方法。
 * !!! 反射调用比正常调用慢2～3倍，能正常写new的绝不用反射
 *
 *
 * 8. 动态加载
 * JVM在执行Java程序的时候，并不是一次性把所有用到的class全部加载到内存，而是第一次需要用到class时才加载。例如：
 * {
 *       People pp = new People(name);
 *       ............
 *       ...........
 *       Person p = new Person(name);
 * }
 * 执行到了第一行,jvm才会把People.class加载到内存, (并不会加载Person.class)
 * 执行到了第4行,JVM发现需要加载Person类时，才会首次加载Person.class
 *
 * 这就是JVM 的非常重要的 '动态加载class特性', 才能在运行期根据条件加载不同的实现类
 * 例如，Commons Logging总是优先使用Log4j，只有当Log4j不存在时，才使用JDK的logging。
 *
 *
 * @author welldo
 * @date 2020/8/14
 */
public class Class2 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //1. 通过类的静态变量'class'获取
        Class<String> stringClass1 = String.class;

        //2. 通过类的实例,调用getClass方法
        String s = "hello";
        Class<? extends String> stringClass2 = s.getClass();

        //3. 通过静态方法
        Class<?> stringClass3 = Class.forName("java.lang.String");

        //上述3个Class的对象,指向同一个地址值,实际上就是一个
        System.out.println(stringClass1 == stringClass2);
        System.out.println(stringClass1 == stringClass3);
        System.out.println(stringClass2 == stringClass3);

        //5.
        Integer n = 123;
        System.out.println(n instanceof Integer);//true
        System.out.println(n instanceof Number);//true

        System.out.println(n.getClass() == Integer.class);//true
        // System.out.println(n.getClass() == Number.class);//无法通过编译

        /*
        注意:
        1. 数组（例如String[]）也是一种Class，而且不同于String.class，它的类名是[Ljava.lang.String
        2. JVM为每一种基本类型如int也创建了Class，通过int.class访问。
         */
        printClassInfo("".getClass());
        printClassInfo(Runnable.class);
        printClassInfo(java.time.Month.class);
        printClassInfo(String[].class);//某个类型的数组
        printClassInfo(int.class);//基本数据类型


        //7.
        Class cls = String.class;
        String s1 = (String) cls.newInstance();
    }

    /**
     * 6.
     * 通过反射,获取Class 实例的完整信息
     */
    static void printClassInfo(Class cls) {
        System.out.println("----------------------------");
        System.out.println("Class name: " + cls.getName());
        System.out.println("Simple name: " + cls.getSimpleName());
        if (cls.getPackage() != null) {
            System.out.println("Package name: " + cls.getPackage().getName());
        }
        System.out.println("is interface: " + cls.isInterface());
        System.out.println("is enum: " + cls.isEnum());
        System.out.println("is array: " + cls.isArray());
        //是否为基本数据类型
        System.out.println("is primitive: " + cls.isPrimitive());
    }


    /*
    8.例如，Commons Logging总是优先使用Log4j，只有当Log4j不存在时，才使用JDK的logging。
    利用JVM动态加载特性，大致的实现代码如下：
    这就是为什么我们只需要把Log4j的jar包放到classpath中，Commons Logging就会自动使用Log4j的原因。
     */
    LogFactory factory = null;
    void findLog4j(){
        if (isClassPresent("org.apache.logging.log4j.Logger")) {
            // factory = createLog4j();//使用log4j
        } else {
            // factory = createJdkLog();//使用jdk logging
        }
    }

    boolean isClassPresent(String name) {
        try {
            Class.forName(name);//如果没有在classpath下找到jar包, 则会抛出异常
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
