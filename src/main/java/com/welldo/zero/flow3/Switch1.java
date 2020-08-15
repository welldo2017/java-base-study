package com.welldo.zero.flow3;

/**
 * switch多重选择
 *
 * 注意:
 * 1. case语句并没有花括号{}
 * 2. case语句具有“穿透性”，漏写break将导致意想不到的结果
 * 3. switch语句还可以匹配字符串。字符串匹配时，是比较“内容相等”(equals), 而不是比较地址值(==)
 *
 *
 * 使用switch时，如果遗漏了break，就会造成严重的逻辑错误
 * java12 的新switch语法,  开始使用模式匹配语法糖, 保证只有一种路径会被执行，并且不需要break语句：
 *          String fruit = "apple";
 *         switch (fruit) {
 *              case "apple" -> System.out.println("Selected apple");
 *              case "pear" -> System.out.println("Selected pear");
 *              case "mango" -> {
 *                  System.out.println("Selected mango");
 *                  System.out.println("Good choice!");
 *              }
 *              default -> System.out.println("No fruit selected");
 *         }
 *
 * java12 的新switch语法, 可以返回一个简单的值
 *          String fruit = "apple";
 *         int opt = switch (fruit) {
 *             case "apple" -> 1;
 *             case "pear", "mango" -> 2;
 *             default -> 0;
 *         };
 *
 *         如果需要复杂的语句, 才能计算出返回值呢? 怎么办?
 *         将很多语句放到{...}里，然后，用yield返回一个值, 作为switch语句的返回值：
 *
 *         String fruit = "orange";
 *         int opt = switch (fruit) {
 *             case "apple" -> 1;
 *             case "pear", "mango" -> 2;
 *             default -> {
 *                 int code = fruit.hashCode();
 *                 yield code; // switch语句返回值
 *             }
 *         };
 *
 *
 * @author welldo
 * @date 2020/8/7
 */
public class Switch1 {
    public static void main(String[] args) {

        //example 1
        int option = 9;
        switch (option) {
            case 1:
                System.out.println("Selected 1");
                break;
            case 2:
                System.out.println("Selected 2");
                break;
            case 3:
                System.out.println("Selected 3");
                break;
            default:
                System.out.println("Not selected");
                break;
        }

        //example 2 比较字符串
        String fruit = "apple";
        switch (fruit) {
            case "apple":
                System.out.println("Selected apple");
                break;
            case "pear":
                System.out.println("Selected pear");
                break;
            case "mango":
                System.out.println("Selected mango");
                break;
            default:
                System.out.println("No fruit selected");
                break;
        }
    }
}
