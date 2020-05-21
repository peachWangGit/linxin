package com.dhlg.utils.common;

import org.springframework.util.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @program: agtproject
 * @description: 日期类型转换工具类
 * @author: HGD
 * @create: 2019-03-22 09:26
 **/
public class DateUtils {

    /**
     * 默认的时间日期样式
     */
    private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static final String DEFAULT_FORMAT = "yyyyMMdd";

    /**
     * 将Long类型的时间戳转换成 String 类型的时间格式
     */
    public static String convertTimestampToString(Long time){
        Assert.notNull(time, "time is null");
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(time),
                ZoneId.systemDefault());
        return localDateTime.format(DateTimeFormatter.ofPattern(DEFAULT_PATTERN));

    }


    /**
     * 将字符串转日期成 Long 类型的时间戳
     */
    public static Long convertTimeStringToTimestamp(String time) {
        Assert.notNull(time, "time is null");
        LocalDateTime localDateTime = LocalDateTime.parse(time, DateTimeFormatter.ofPattern(DEFAULT_PATTERN));
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return instant.toEpochMilli();
    }

    /**
     * 将字符串转日期成 Long 类型的时间戳
     */
    public static Date getCurrentDate() {
       /* ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        Date date = Date.from(zdt.toInstant());*/
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        try {
            return simpleDateFormat.parse(format);
        } catch (ParseException e) {
          return null;
        }
    }

    /**
     * 根据格式返回字符串日期
     * @return
     */
    public static String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DEFAULT_FORMAT);
        String dataTime = now.format(dateTimeFormatter);
        return dataTime;
    }

}