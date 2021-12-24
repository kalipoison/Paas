package com.gohb.convert;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * k8s api 自带 datetime 转换为utils.Date
 */
public class DateTimeUtils {

    public static String kubeDateTimeToString(DateTime dateTime) {
        return DateToString(dateTime.toDate());
    }

    public static String DateToString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date).toString();
    }

}
