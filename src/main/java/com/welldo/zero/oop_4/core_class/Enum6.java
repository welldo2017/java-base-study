package com.welldo.zero.oop_4.core_class;

/**
 * 枚举
 *
 * 为了让编译器能自动检查某个值在枚举的集合内，并且，不同用途的枚举需要不同的类型来标记，不能混用，
 * 我们可以使用enum来定义枚举类：
 *
 *
 * 1.
 * 使用enum定义的枚举类是一种引用类型。
 * 前面我们讲到，引用类型比较，要使用equals()方法， * 如果使用==比较，它比较的是两个变量的地址值。
 * 因此，引用类型比较，要始终使用equals()方法，但enum类型可以例外。
 * 这是因为enum类型的每个常量在JVM中只有一个唯一实例，所以可以直接用==比较：
 *
 *
 * 2.
 * 通过enum定义的枚举类，和其他的class有什么区别？
 * 答案是没有任何区别。enum定义的类型就是class，只不过它有以下几个特点：
 *
 * 定义的enum类型总是继承自java.lang.Enum，且无法被继承；
 * 只能预先定义出 enum 的实例，而无法通过 new 操作符创建 enum 的实例；
 * 定义的每个实例都是引用类型的唯一实例；
 * 可以将enum类型用于switch语句。
 *
 *
 * 3.因为enum是一个class，每个枚举的值都是class实例，因此，这些实例有一些方法：
 * -返回常量名: name(), 返回值string类型
 *      默认情况下，对枚举常量调用toString()会返回和name()一样的字符串
 *      但是，toString()可以被覆写，而name()则不行。我们可以给Weekday添加toString()方法：
 * -返回定义的常量的顺序，从0开始计数: ordinal()
 *
 *
 * 4.
 * 因为enum本身是class，所以我们可以定义private的构造方法，并且，给每个枚举常量添加字段
 *
 *
 * 5.
 * 枚举类可以应用在switch语句中。因为枚举类天生具有类型信息和有限个枚举常量
 *
 * @author welldo
 * @date 2020/8/11
 */
public class Enum6 {
    public static void main(String[] args) {
        Weekday day = Weekday.SUN;

        //1.
        if (day == Weekday.SUN)
            System.out.println("ok");
        if (day.equals(Weekday.SUN))
            System.out.println("ok, but more code");


        //3.
        System.out.println(Weekday.TUE.name());
        System.out.println(Weekday.TUE.toString());
        System.out.println(Weekday.TUE.ordinal());


        //5.
        switch(day) {
            case MON:
            case TUE:
            case WED:
            case THU:
            case FRI:
                System.out.println("Today is " + day + ". Work at office!");
                break;
            case SAT:
            case SUN:
                System.out.println("Today is " + day + ". Work at home!");
                break;
            default:
                throw new RuntimeException("cannot process " + day);
        }

    }
}



enum Weekday {
    MON(1, "星期一"), TUE(2, "星期二"), WED(3, "星期三"), THU(4, "星期四"), FRI(5, "星期五"), SAT(6, "星期六"), SUN(0, "星期日");

    public final int dayValue;
    private final String chinese;

    private Weekday(int dayValue, String chinese) {
        this.dayValue = dayValue;
        this.chinese = chinese;
    }

    @Override
    public String toString() {
        return this.chinese;
    }
}


//4.
enum WeekdayHasValue {
    MON(1), TUE(2), WED(3), THU(4), FRI(5), SAT(6), SUN(0);

    //枚举类的字段也可以是非final类型，即可以在运行期修改，但是不推荐这样做！
    public final int dayValue;

    private WeekdayHasValue(int dayValue) {
        this.dayValue = dayValue;
    }
}


// 最简单的枚举
enum EnumColor6 {
    RED,    GREEN,    BLUE;
}

