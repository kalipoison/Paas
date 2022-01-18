package com.gohb.manage.Client;

import com.gohb.params.bo.client.MyUserBO;
import com.gohb.params.dto.client.MyUserDTO;

import java.util.List;

public interface MyUserManage {

    /**
     * 前台用户注册
     * @param myUserBO
     * @return
     */
    Boolean saveMyUser(MyUserBO myUserBO);

    /**
     * 根据id 删除 myUser
     * @param id
     * @return
     */
    Boolean deleteMyUser(Integer id);

    /**
     * 更新 myUser
     * @param myUserBO
     * @return
     */
    Boolean updateMyUser(MyUserBO myUserBO);

    /**
     * 查询所有 myUser，模糊查询
     * @return
     */
    List<MyUserDTO> listMyUser(MyUserBO myUserBO);

    /**
     * 查询 myUser 详情
     * @param id
     * @return
     */
    MyUserDTO detailMyUser(Integer id);

}
