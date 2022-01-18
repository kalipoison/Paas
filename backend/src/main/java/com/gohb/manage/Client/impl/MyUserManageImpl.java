package com.gohb.manage.Client.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gohb.convert.BoToDtoUtils;
import com.gohb.manage.Client.MyUserManage;
import com.gohb.params.bo.client.MyUserBO;
import com.gohb.params.dto.client.MyUserDTO;
import com.gohb.params.dto.notify.NotifyDTO;
import com.gohb.params.exception.ClientException;
import com.gohb.service.client.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean
public class MyUserManageImpl implements MyUserManage {

    @Autowired
    private MyUserService myUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Boolean saveMyUser(MyUserBO myUserBO) {
        myUserBO.setPassword(passwordEncoder.encode(myUserBO.getPassword()));
        if (myUserBO.getStatus() == null) {
            myUserBO.setStatus(1); // status： 正常
        }
        if (myUserBO.getMember() == null) {
            myUserBO.setMember(0); // is not member
        }
        myUserBO.setCreateTime(new Date());
        try {
            boolean save = myUserService.save(myUserBO);
            return save;
        } catch (Exception e) {
            throw new ClientException(e.getMessage());
        }
    }

    @Override
    public Boolean deleteMyUser(Integer id) {
        boolean delete = myUserService.removeById(id);
        return delete;
    }

    @Override
    public Boolean updateMyUser(MyUserBO myUserBO) {
        boolean update = myUserService.updateById(myUserBO);
        return update;
    }

    @Override
    public List<MyUserDTO> listMyUser(MyUserBO myUserBO) {
        List<MyUserBO> myUserBOS = myUserService.list(new LambdaQueryWrapper<MyUserBO>()
                .like(StringUtils.hasText(myUserBO.getEmail()), MyUserBO::getEmail, myUserBO.getEmail()));
        List<MyUserDTO> myUserDTOS = new ArrayList<>();
        for (MyUserBO myUser : myUserBOS) {
            myUserDTOS.add(BoToDtoUtils.MyUserBoTOMyUserDTO(myUser));
        }
        return myUserDTOS;
    }

    @Override
    public MyUserDTO detailMyUser(Integer id) {
        MyUserBO myUserBO = myUserService.getOne(new LambdaQueryWrapper<MyUserBO>()
                .eq(MyUserBO::getUserId, id));
        MyUserDTO myUserDTO = BoToDtoUtils.MyUserBoTOMyUserDTO(myUserBO);
        return myUserDTO;
    }


}
