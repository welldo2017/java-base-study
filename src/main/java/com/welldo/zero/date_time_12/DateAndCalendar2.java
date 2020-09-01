package com.welldo.zero.date_time_12;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 0.
 * Java有两套日期和时间的API：
 *      旧的Date、Calendar和TimeZone；(所在包: java.util.*)
 *      新的LocalDateTime、ZonedDateTime、ZoneId等; (所在包:Java 8引入的 java.time.*)
 * 本节我们快速讲解旧API的常用类型和方法。
 *
 *
 * 1.数据的存储和展
 * 我们看看以下几种日期和时间,
 *      2019-11-20 0:15:00 GMT+00:00
 *      2019年11月20日8:15:00
 *      11/19/2019 19:15:00 America/New_York
 *
 * 它们实际上是数据的展示格式，分别按英国(伦敦)时区、中国时区、纽约时区对同一个时刻进行展示。
 * 而这个“同一个时刻”在计算机中存储的本质上只是一个整数，我们称它为Epoch Time。
 *
 * Epoch Time是计算从1970年1月1日零点（格林威治时区／GMT+00:00）到现在(此时此刻)所经历的秒数，
 * 例如：1574208900 表示从从1970年1月1日零点,到该时刻一共经历了1574208900秒，
 * 换算成伦敦、北京和纽约时间分别是：
 *     = 伦敦时间2019-11-20 0:15:00
 *     = 北京时间2019-11-20 8:15:00
 *     = 纽约时间2019-11-19 19:15:00
 *
 * 因此，在计算机中，只需要存储一个整数(如1574208900)来表示某一时刻。
 * 当需要显示为某一地区的当地时间时，我们就把它格式化为一个字符串：
 *
 *
 *
 * 2.时间戳
 * Epoch Time又称为时间戳，在不同的编程语言中，会有几种存储方式：
 * 以秒为单位的整数,10位：1574208900，缺点是精度只能到秒；
 * 以毫秒为单位的整数,13位：1574208900123，最后3位表示毫秒数；(在Java程序中，使用这种)
 * 以秒为单位的浮点数：1574208900.123，小数点后面表示毫秒数。
 *
 *
 * 3.Date
 * java.util.Date是用于表示一个日期和时间的对象，注意与java.sql.Date区分，后者用在数据库中。
 * Date对象有几个严重的问题：
 * 它不能转换时区，只能使用当前计算机系统的时区;
 * 此外，我们也很难对日期和时间进行加减，计算两个日期相差多少天，计算某个月第一个星期一的日期等。
 *
 *
 * 4.Calendar
 * Calendar和Date比，主要多了一个可以做简单的日期和时间运算的功能。
 *
 *
 * 5. TimeZone
 * Calendar和Date相比，它提供了时区转换的功能。时区用TimeZone对象表示
 *
 * 时区的唯一标识是以字符串表示的ID，我们获取指定TimeZone对象也是以这个ID为参数获取，GMT+09:00、Asia/Shanghai都是有效的时区ID。
 * 要列出系统支持的所有ID，请使用TimeZone.getAvailableIDs()。
 *
 * 注意Date对象无时区信息，时区信息存储在SimpleDateFormat中
 * 因此，本质上时区转换只能通过SimpleDateFormat在显示的时候完成。
 *
 *
 * @author welldo
 * @date 2020/8/29
 */
public class DateAndCalendar2 {
    public static void main(String[] args) {

        //2.
        System.out.println("-----2-----");
        long time = System.currentTimeMillis();
        System.out.println(time);

        /*
         * 3.获取当前时间:
         * 使用SimpleDateFormat对一个Date进行转换。它用预定义的字符串表示格式化：
         * yyyy：年         * MM：月         * dd: 日
         * HH: 小时         * mm: 分钟         * ss: 秒
         * SSS:毫秒
         */
        System.out.println("-----3-----");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        System.out.println(sdf.format(date));


        /*
         * 4.Calendar只有一种方式获取，即Calendar.getInstance()，
         */
        System.out.println("-----41-----");
        Calendar c = Calendar.getInstance();
        int y = c.get(Calendar.YEAR);
        int m = 1 + c.get(Calendar.MONTH);//index从0开始
        int d = c.get(Calendar.DAY_OF_MONTH);

        int hh = c.get(Calendar.HOUR_OF_DAY);
        int mm = c.get(Calendar.MINUTE);
        int ss = c.get(Calendar.SECOND);
        int ms = c.get(Calendar.MILLISECOND);

        int w = c.get(Calendar.DAY_OF_WEEK);//1~7分别表示 周日，周一，……，周六。

        //获取此时刻的本地时间
        System.out.println(y + "-" + m + "-" + d + " " + hh + ":" + mm + ":" + ss + "." + ms + " 周" + w);

        //获取此时刻的其他时区时间
        SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf4.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        System.out.println(sdf4.format(c.getTime()));


        /*
         * 4.Calendar只有一种方式获取，即Calendar.getInstance()，而且一获取到就是当前时间。
         * 如果我们想给它设置成特定的一个日期和时间，就必须先清除所有字段(清除后会变成 1970-01-01 00:00:00)
         * 注意:并不清除时区信息, 所以时区还是 Asia/Shanghai
         *
         * 利用Calendar.getTime()可以将一个Calendar对象转换成Date对象
         */
        System.out.println("-----42-----");
        c.clear();// 清除所有(并不清除时区信息, 所以还是 Asia/Shanghai)
        System.out.println(c.getTimeZone().getDisplayName() );
        c.set(Calendar.YEAR, 2020);
        c.set(Calendar.MONTH, 1);// 设置2月:index从0开始
        c.set(Calendar.DATE, 22);

        c.set(Calendar.HOUR_OF_DAY, 22);
        c.set(Calendar.MINUTE, 22);
        c.set(Calendar.SECOND, 22);
        c.set(Calendar.MILLISECOND, 001);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(c.getTime()));


        /*
         * 5.时区的唯一标识是以字符串表示的ID，我们获取指定TimeZone对象也是以这个ID为参数获取，GMT+09:00、Asia/Shanghai都是有效的时区ID。
         */
        System.out.println("-----51-----");
        TimeZone tzDefault = TimeZone.getDefault(); // 当前时区
        TimeZone tzGMT9 = TimeZone.getTimeZone("GMT+09:00"); // GMT+9:00时区
        TimeZone tzNY = TimeZone.getTimeZone("America/New_York"); // 纽约时区
        System.out.println(tzDefault.getID()); // Asia/Shanghai
        System.out.println(tzGMT9.getID()); // GMT+09:00
        System.out.println(tzNY.getID()); // America/New_York

        /*
         * 5.有了时区，我们就可以对指定时间进行转换。例如，下面的例子演示了如何将北京时间2019-11-20 8:15:00 转换为纽约时间：
         * 注意Date对象无时区信息，时区信息存储在SimpleDateFormat中
         */
        System.out.println("-----52-----");
        Calendar c5 = Calendar.getInstance();
        c5.clear();
        c5.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));// 设置为北京时区, 实际上不设置也行.
        c5.set(2019, 10 /* 11月 */, 20, 8, 15, 0);//设置时区+本地时间= 时刻

        //将时刻 转成其他时区的本地时间
        SimpleDateFormat sdf5 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf5.format(c5.getTime()));// SimpleDateFormat未更改时区

        sdf5.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        System.out.println(sdf5.format(c5.getTime()));// 更改时区后: 2019-11-19 19:15:00


        /*
         * 5.Calendar也可以对日期和时间进行简单的加减
         */
        System.out.println("-----53-----");
        Calendar c53 = Calendar.getInstance();
        c53.clear();
        c53.set(2019, 10 /* 11月 */, 20, 8, 15, 0);

        c53.add(Calendar.DAY_OF_MONTH, 5);// 加5天并减去2小时:
        c53.add(Calendar.HOUR_OF_DAY, -2);

        SimpleDateFormat sdf53 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf53.format(c53.getTime()));// 2019-11-25 6:15:00
    }
}
