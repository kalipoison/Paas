package com.gohb.manage.Client;

import com.gohb.params.bo.client.MyUserBO;

public interface MyUserManage {

    /**
     * 前台用户注册
     * @param myUserBO
     * @return
     */
    Boolean register(MyUserBO myUserBO);



}
