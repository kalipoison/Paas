package com.gohb.convert;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;

/**
 * k8s api 自带 datetime 转换为utils.Date
 */
public class KubeDateTimeUtils {

    public static String kubeDateTimeToString(DateTime dateTime) {
        String s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateTime.toDate()).toString();
        return s;
    }

}
