package com.gohb.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

public class SnowUtil {

    public static Snowflake snowflake = IdUtil.createSnowflake(1, 1);

}
