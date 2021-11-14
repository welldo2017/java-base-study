package com.welldo.zero.a12_date_time;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * ZonedDateTime
 *
 * 0.
 * LocalDateTime总是表示本地日期和时间，要表示一个带时区的日期和时间，我们就需要ZonedDateTime。
 * ZonedDateTime = LocalDateTime + 时区ZoneId，可以与long表示的时间戳进行转换。 {@link ZonedDateTime}
 *
 * 1.通过now()方法返回当前时刻(本地时间+时区)
 *
 * 2. 构造时刻, 先构造本地时间, 再加上时区
 *
 * 3.时区转换, 通过withZoneSameInstant()将关联时区转换到另一个时区，转换后日期和时间都会相应调整
 *
 * 4.将ZonedDateTime 转换为本地时间, 直接丢弃了时区信息
 *
 *
 * 5.练习
 * 某航线从北京飞到纽约需要13小时20分钟，请根据北京起飞日期和时间计算到达纽约的当地日期和时间。
 *
 * @author welldo
 * @date 2020/8/31
 */
public class ZonedDateTime4 {
    public static void main(String[] args) {
        /*
         * 1.本地时间+时区
         * 2020-09-01T11:06:27.888+08:00[Asia/Shanghai]
         * 2020-08-31T23:06:27.890-04:00[America/New_York]
         *
         * 获取默认时区三种写法(默认时区为北京时间)
         * .now() / .now(ZoneId.systemDefault()) /  .now(ZoneId.of("Asia/Shanghai"))
         */
        System.out.println("-----1-----");
        ZonedDateTime peking = ZonedDateTime.now();//北京
        ZonedDateTime newYork = ZonedDateTime.now(ZoneId.of("America/New_York"));//纽约

        System.out.println(peking);
        System.out.println(newYork);

        /*
         * 2. 构造时刻
         * 场景: 本地时间相同, 时区不同.
         */
        System.out.println("-----2-----");
        LocalDateTime today = LocalDateTime.of(2020, 9, 01, 10, 10, 10);
        ZonedDateTime peking2 = today.atZone(ZoneId.systemDefault());//计算机的默认时区
        ZonedDateTime new_york2 = today.atZone(ZoneId.of("America/New_York"));
        System.out.println(peking2);
        System.out.println(new_york2);

        /*
         * 3.将北京时间转换为纽约时间：(中国北京比美国纽约快12小时)
         *
         * 注意:由于夏令时的存在，不同的日期转换的结果很可能是不同的
         * 这是北京时间11月15日的转换结果：
         * 2019-11-15T21:05:50.187697+08:00[Asia/Shanghai]
         * 2019-11-15T08:05:50.187697-05:00[America/New_York]
         * 两次转换后的纽约时间有1小时的夏令时时差。所以bj比ny快了13小时
         */
        System.out.println("-----3-----");
        ZonedDateTime peking3 = ZonedDateTime.now(ZoneId.systemDefault());
        ZonedDateTime ny3 = peking3.withZoneSameInstant(ZoneId.of("America/New_York"));
        System.out.println(peking3);
        System.out.println(ny3);


        /*
         * 4.有了ZonedDateTime，使用toLocalDateTime 将其转换为本地时间
         * 直接丢弃了时区信息。
         */
        System.out.println("-----4-----");
        ZonedDateTime peking4 = ZonedDateTime.now(ZoneId.systemDefault());
        ZonedDateTime newYork4 = ZonedDateTime.now(ZoneId.of("America/New_York"));//纽约
        System.out.println(peking4.toLocalDateTime());
        System.out.println(newYork4.toLocalDateTime());


        /*
         * 某航线从北京飞到纽约需要13小时20分钟，请根据北京起飞日期和时间计算到达纽约的当地日期和时间。
         */
        System.out.println("-----5-----");
        LocalDateTime departureAtBeijing = LocalDateTime.of(2019, 9, 15, 13, 0, 0);
        int hours = 13;
        int minutes = 20;
        LocalDateTime arrivalAtNewYork = calculateArrivalAtNY(departureAtBeijing, hours, minutes);
        System.out.println(departureAtBeijing + " -> " + arrivalAtNewYork);
        // test:
        if (!LocalDateTime.of(2019, 10, 15, 14, 20, 0)
                .equals(calculateArrivalAtNY(LocalDateTime.of(2019, 10, 15, 13, 0, 0), 13, 20))) {
            System.err.println("测试失败!");
        } else if (!LocalDateTime.of(2019, 11, 15, 13, 20, 0)
                .equals(calculateArrivalAtNY(LocalDateTime.of(2019, 11, 15, 13, 0, 0), 13, 20))) {
            System.err.println("测试失败!");
        }
    }


    static LocalDateTime calculateArrivalAtNY(LocalDateTime bj, int h, int m) {
        //构造时刻
        LocalDateTime endDT = bj.plusHours(h).plusMinutes(m);
        ZonedDateTime endZDT = endDT.atZone(ZoneId.systemDefault());

        //通过时刻,切换时区
        ZonedDateTime nyZDT = endZDT.withZoneSameInstant(ZoneId.of("America/New_York"));
        return nyZDT.toLocalDateTime();
    }


}
