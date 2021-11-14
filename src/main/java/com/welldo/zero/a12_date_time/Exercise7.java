package com.welldo.zero.a12_date_time;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 最佳实践, 新旧API之间的互相转换
 *
 * 0.
 * 处理日期和时间时，尽量使用新的java.time包；
 * 在数据库中存储时间戳时，尽量使用long型时间戳，它具有省空间，效率高，不依赖数据库的优点。
 *
 *
 * 1.旧API转新API
 * 如果要把Date / Calendar转换为新API，可以通过toInstant()方法转换为Instant对象，再+zoneId = ZonedDateTime：
 *
 *
 * 2.新API转旧API
 * 如果要把新的ZonedDateTime转换为旧的API对象，只能借助long型时间戳做一个“中转”：
 *
 *
 * 3.在数据库中存储日期和时间
 * 除了旧式的java.util.Date，我们还可以找到java.sql.Date，它继承自java.util.Date，
 * 但会自动忽略所有时间相关信息。这个奇葩的设计原因要追溯到数据库的日期与时间类型。
 *
 * 在数据库中，也存在几种日期和时间类型：
 * DATETIME：表示日期和时间； * DATE：仅表示日期； * TIME：仅表示时间；
 * TIMESTAMP：和DATETIME类似，但是数据库会在创建或者更新记录的时候同时修改TIMESTAMP。
 *
 * 下表是数据库类型与Java新旧API的映射关系：
 * +───────────+─────────────────────+────────────────+
 * | 数据库     | 旧javaAPI            | 新javaAPI      |
 * +===========+=====================+================+
 * | datetime  | java.util.date      | localDateTime  |
 * | date      | java.sql.date       | localDate      |
 * | time      | java.sql.time       | localTime      |
 * | timestamp | java.sql.timestamp  | localDateTime  |
 * +───────────+─────────────────────+────────────────+
 *
 * 在数据库中，我们需要存储的是时刻（Instant），因为有了时刻信息，就可以根据用户自己选择的时区，显示出正确的本地时间。
 * 所以，最好的方法是直接用长整数long表示时间戳，在数据库中存储为BIGINT类型。 它具有省空间，效率高，不依赖数据库的优点。
 *
 * @author welldo
 * @date 2020/9/1
 */
public class Exercise7 {
    public static void main(String[] args) {

        //1.Date -> Instant -> ZonedDateTime:
        Instant instant = new Date().toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        System.out.println(zdt);

        // Calendar -> Instant -> ZonedDateTime:
        Calendar calendar = Calendar.getInstance();
        Instant instant1 = calendar.toInstant();
        //旧的TimeZone提供了一个toZoneId()，可以把自己变成新的ZoneId
        ZonedDateTime zdt1 = instant1.atZone(calendar.getTimeZone().toZoneId());
        System.out.println(zdt1);


        //2.ZonedDateTime -> long:
        System.out.println("-----2-----");
        ZonedDateTime now = ZonedDateTime.now();
        long ts = now.toEpochSecond() * 1000;

        // long -> Date:
        Date date = new Date(ts);
        System.out.println(date);

        // long -> Calendar:
        Calendar can2 = Calendar.getInstance();
        can2.clear();
        //新的ZoneId转换为旧的TimeZone，需要借助ZoneId.getId()返回的String完成。
        can2.setTimeZone(TimeZone.getTimeZone(now.getZone().getId()));
        can2.setTimeInMillis(ts);


        /*
         * 3.通过存储一个long型时间戳，我们可以编写一个timestampToString()的方法，非常简单地为不同用户以不同的偏好来显示不同的本地时间：
         */
        System.out.println("-----3-----");
        long ts3 = 1574208900000L;
        System.out.println(timestampToString(ts3, Locale.CHINA, "Asia/Shanghai"));
        System.out.println(timestampToString(ts3, Locale.US, "America/New_York"));
    }

    static String timestampToString(long epochMilli, Locale lo, String zoneId) {
        Instant ins = Instant.ofEpochMilli(epochMilli);
        DateTimeFormatter f = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT);
        return f.withLocale(lo).format(ZonedDateTime.ofInstant(ins, ZoneId.of(zoneId)));
    }

}
