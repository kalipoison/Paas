package com.gohb.manage.Client.impl;

import com.gohb.manage.Client.MyUserManage;
import com.gohb.params.bo.client.MyUserBO;
import com.gohb.params.exception.ClientException;
import com.gohb.service.client.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import java.util.Date;

@ManagedBean
public class MyUserManageImpl implements MyUserManage {

    @Autowired
    private MyUserService myUserService;


    @Override
    public Boolean register(MyUserBO myUserBO) {
        myUserBO.setCreateTime(new Date());
        try {
            boolean save = myUserService.save(myUserBO);
            return save;
        } catch (Exception e) {
            throw new ClientException(e.getMessage());
        }
    }
}
