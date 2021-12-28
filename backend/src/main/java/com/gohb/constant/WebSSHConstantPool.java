package com.gohb.constant;

/**
 * webssh 常量池
 */
public interface WebSSHConstantPool {
    /**
     * 随机生成uuid的key名
     */
    String USER_UUID_KEY = "user_uuid";
    /**
     * 发送指令：连接
     */
    String WEBSSH_OPERATE_CONNECT = "connect";
    /**
     * 发送指令：命令
     */
    String WEBSSH_OPERATE_COMMAND = "command";
}
