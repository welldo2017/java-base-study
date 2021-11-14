package com.welldo.zero.a3_flow3;

/**
 * 无论是while循环还是for循环，有两个特别的语句可以使用，就是break语句和continue语句。
 *
 * break会跳出当前循环，也就是整个循环都不会执行了
 * 而continue则是提前结束本次循环，直接继续执行下次循环
 *
 *
 * @author welldo
 * @date 2020/8/7
 */
public class Break2 {
    public static void main(String[] args) {

        //1. 在循环过程中，可以使用break语句跳出当前循环。
        int sum = 0;
        for (int i=1; ; i++) {  // 不给for循环设置 "循环检测条件"
            sum = sum + i;
            if (i == 100) {
                break;
            }
        }
        System.out.println(sum);

        //break语句通常都是配合if语句使用。要特别注意，break语句总是跳出自己所在的那一层循环。例如：
        for (int i=1; i<=10; i++) {
            System.out.println("i = " + i);
            for (int j=1; j<=10; j++) {
                System.out.println("j = " + j);
                if (j >= i) {
                    break;
                }
            }
            // break跳到这里
            System.out.println("breaked");
        }


    }
}
