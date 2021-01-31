package com.welldo.zero.maven_16;

/**
 * 依赖管理
 *
 * ========================================================================================
 * 1. maven依赖调解功能
 * 现实中可能存在这样的情况，A->B->C->Y(1.0)，A->D->Y(2.0)，此时Y出现了2个版本，1.0和2.0，此时maven会选择Y的哪个版本？
 * 解决这种问题，maven有2个原则：
 *  1.路径最近原则
 *      上面A->B->C->Y(1.0)，A->D->Y(2.0)，Y的2.0版本距离A更近一些，所以maven会选择2.0。
 *      但是如果出现了路径是一样的，如：A->B->Y(1.0)，A->D->Y(2.0)，
 *      此时maven又如何选择呢？
 *  2.最先声明原则
 *      如果出现了路径一样的，此时会看A的pom.xml中所依赖的B、D在dependencies中的位置，
 *      谁的声明在最前面，就以谁的为主，
 *      比如A->B在前面，那么最后Y会选择1.0版本。
 *
 *
 * ========================================================================================
 * 2. 可选依赖（optional元素）
 * 有这么一种情况：
 * A->B中scope:compile * B->C中scope:compile
 *
 * A的pom
 * <dependency>
 *     <groupId>com.javacode2018</groupId>
 *     <artifactId>B</artifactId>
 *     <version>1.0</version>
 * </dependency>
 *
 * B的pom
 * <dependency>
 *     <groupId>com.javacode2018</groupId>
 *     <artifactId>C</artifactId>
 *     <version>1.0</version>
 * </dependency>
 * 按照上面介绍的依赖传递性，C会传递给A，被A依赖。
 *
 * 假如B不想让C被A自动依赖，可以怎么做呢？
 * dependency元素下面有个optional，是boolean值，表示是可选依赖，B->C时将这个值置为true，那么C不会被A自动引入。
 * <dependency>
 *     <groupId>com.javacode2018</groupId>
 *     <artifactId>C</artifactId>
 *     <version>1.0</version>
 *     <optional>true</optional>
 * </dependency>
 *
 *
 * ========================================================================================
 * 3. 排除依赖
 *  上面第2点,也可以使用"排除依赖"来解决
 *  上面A->B的1.0版本，B->C的1.0版本，而scope都是默认的compile，
 *  根据前面讲的依赖传递性，C会传递给A，会被A自动依赖，
 *  但是C此时有个更新的版本2.0，A想使用2.0的版本，此时A的pom.xml中可以这么写：
 *
 * <dependency>
 *     <groupId>com.javacode2018</groupId>
 *     <artifactId>B</artifactId>
 *     <version>1.0</version>
 *     <exclusions>
 *         <exclusion>
 *             <groupId>com.javacode2018</groupId>
 *             <artifactId>C</artifactId>
 *         </exclusion>
 *     </exclusions>
 * </dependency>
 * 上面使用使用exclusions元素排除了B->C依赖的传递，也就是B->C不会被传递到A中。
 * exclusions中可以有多个exclusion元素，可以排除一个或者多个依赖的传递，
 * 声明exclusion时只需要写上groupId、artifactId就可以了，version可以省略。
 *
 *
 * @author welldo
 * @date 2020/9/9
 */
public class Maven1_5 {
}
