package com.welldo.zero.date_time_12;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * a. 从Java 8开始，java.time包提供了新的日期和时间API，主要涉及的类型有：
 *      本地日期和时间：LocalDateTime，LocalDate，LocalTime；
 *      带时区的日期和时间：ZonedDateTime；
 *      时刻：Instant；
 *      时区：ZoneId，ZoneOffset；
 *      时间间隔：Duration。
 *      用于取代SimpleDateFormat的 新的格式化类型DateTimeFormatter。
 *
 * b. 和旧的API相比，新API严格区分了时刻、本地日期时间, 带时区的日期时间
 *
 * c. 此外，新API修正了旧API不合理的常量设计：
 *      Month的范围用1~12表示1月到12月；
 *      Week的范围用1~7表示周一到周日。
 *
 * d. 最后，新API的类型几乎全部是不变类型（和String类似），可以放心使用不必担心被修改。
 *
 *
 * 1. LocalDateTime, 它表示一个本地日期和时间
 * LocalDateTime无法与时间戳进行转换，因为LocalDateTime没有时区，无法确定某一时刻。
 * 后面我们要介绍的ZonedDateTime = LocalDateTime + 时区，可以与long表示的时间戳进行转换。
 *
 * 2. 通过指定的日期和时间创建LocalDateTime可以通过of()方法
 *
 * 3.DateTimeFormatter
 * 如果要自定义输出的格式，或者要把一个非ISO 8601格式的字符串解析成LocalDateTime,用这个
 *
 * 8.Duration和Period
 * Duration表示两个时刻之间的时间间隔。另一个类似的Period表示两个日期之间的天数：
 *
 * @author welldo
 * @date 2020/8/31
 */
public class LocalDateTime3 {
    public static void main(String[] args) {

        /*
         * 1.日期和时间,以当前默认时区返回
         * 默认严格按照ISO 8601规定的日期和时间格式进行打印
         */
        System.out.println("-----1-----");
        LocalDateTime dt = LocalDateTime.now(); // 当前日期和时间
        LocalDate d = LocalDate.now(); // 当前日期
        LocalTime t = LocalTime.now(); // 当前时间

        //为了保证毫秒数相同, 代码如下
        LocalDate date = dt.toLocalDate();
        LocalTime time = dt.toLocalTime();
        System.out.println(dt);
        System.out.println(date);
        System.out.println(time);


        /*
         * 2.指定日期和时间
         *
         * 因为严格按照ISO 8601的格式，因此，将字符串写成标准格式, 就可以构造出日期与时间* 标准格式如下：
         * 日期：yyyy-MM-dd
         * 时间：HH:mm:ss
         * 带毫秒的时间：HH:mm:ss.SSS
         * 日期和时间：yyyy-MM-dd'T'HH:mm:ss  (日期和时间分隔符是T)
         * 带毫秒的日期和时间：yyyy-MM-dd'T'HH:mm:ss.SSS
         */
        System.out.println("-----2-----");
        //方法1 & 2
        LocalDate d21 = LocalDate.of(2019, 11, 30); // 2019-11-30, 注意11=11月
        LocalDate d22 = LocalDate.parse("2019-11-30");

        LocalTime t21 = LocalTime.of(15, 16, 17); // 15:16:17
        LocalTime t22 = LocalTime.parse("15:16:17");

        //方法1 & 2 & 3
        LocalDateTime dt21 = LocalDateTime.of(2019, 11, 30, 15, 16, 17);//方法2
        LocalDateTime dt22 = LocalDateTime.parse("2019-11-30T15:16:17");//方法3
        LocalDateTime dt23 = LocalDateTime.of(d21, t21);//方法1

        /*
         * 3.
         */
        System.out.println("-----3-----");
        // 自定义格式化:
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println(dtf.format(LocalDateTime.now()));

        // 用自定义的格式解析字符串(两者格式必须一致),解析成标准格式
        LocalDateTime dt2 = LocalDateTime.parse("2019/11/30 15:16:17", dtf);
        System.out.println(dt2);

        /*
         * 4.LocalDateTime提供了对日期和时间进行加减的非常简单的链式调用：
         */
        System.out.println("-----4-----");
        LocalDateTime dt4 = LocalDateTime.of(2019, 10, 26, 20, 30, 59);
        System.out.println(dt4);
        // 加5天减3小时:
        LocalDateTime dt41 = dt4.plusDays(5).minusHours(3);
        System.out.println(dt41); // 2019-10-31T17:30:59
        // 减1月:
        LocalDateTime dt3 = dt41.minusMonths(1);
        System.out.println(dt3); // 2019-09-30T17:30:59(会自动调整日期，因为9月没有31日。)


        /*
         * 5.对日期和时间进行调整则使用withXxx()方法，例如：withHour(15)会把10:11:12变为15:11:12：
         * 调整年：withYear()         * 调整月：withMonth()         * 调整日：withDayOfMonth()
         * 调整时：withHour()         * 调整分：withMinute()         * 调整秒：withSecond()
         */
        System.out.println("-----5-----");
        LocalDateTime dt5 = LocalDateTime.of(2019, 10, 26, 20, 30, 59);
        System.out.println(dt5);
        // 日期变为31日:
        LocalDateTime dt51 = dt5.withDayOfMonth(31);
        System.out.println(dt51); // 2019-10-31T20:30:59
        // 月份变为9:
        LocalDateTime dt52 = dt51.withMonth(9);
        System.out.println(dt52); // 2019-09-30T20:30:59(会自动调整日期，因为9月没有31日。)


        /*
         * 6. LocalDateTime还有一个通用的with()方法允许我们做更复杂的运算
         * 对于计算某个月第1个周日这样的问题，新的API可以轻松完成。
         */
        System.out.println("-----6-----");
        System.out.println(LocalDate.now().withDayOfMonth(1).atStartOfDay());// 本月第一天0:00时刻:
        System.out.println(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()));        // 本月最后1天:
        System.out.println(LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth()));        // 下月第1天:
        System.out.println(LocalDate.now().with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)));    // 本月第1个周一:

        /*
         * 7.要判断两个LocalDateTime的先后，可以使用isBefore()、isAfter()方法，
         * 对于LocalDate和LocalTime类似：
         */
        System.out.println("-----7-----");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime target = LocalDateTime.of(2019, 11, 19, 8, 15, 0);
        System.out.println(now.isBefore(target));
        System.out.println(LocalDate.now().isBefore(LocalDate.of(2019, 11, 19)));
        System.out.println(LocalTime.now().isAfter(LocalTime.parse("08:15:00")));


        /*
         * 8.注意到两个LocalDateTime之间的差值使用Duration表示，类似PT1235H10M30S，表示1235小时10分钟30秒。
         * 而两个LocalDate之间的差值用Period表示，类似P1M21D，表示1个月21天。
         *
         * Duration和Period的表示方法也符合ISO 8601的格式，它以P...T...的形式表示，P...T之间表示日期间隔，T后面表示时间间隔。
         * 如果是PT...的格式表示仅有时间间隔。
         *
         * 利用ofXxx()或者parse()方法也可以直接创建Duration：
         * Duration d1 = Duration.ofHours(10); // 10 hours
         * Duration d2 = Duration.parse("P1DT2H3M"); // 1 day, 2 hours, 3 minutes
         */
        System.out.println("-----8-----");
        LocalDateTime start = LocalDateTime.of(2019, 11, 19, 8, 15, 0);
        LocalDateTime end = LocalDateTime.of(2020, 1, 9, 19, 25, 30);
        System.out.println(Duration.between(start, end)); // PT1235H10M30S
        System.out.println(LocalDate.of(2019, 11, 19).until(LocalDate.of(2020, 1, 9))); // P1M21D
    }

}
