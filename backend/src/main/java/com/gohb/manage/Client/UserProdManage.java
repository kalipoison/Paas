package com.gohb.manage.Client;

import com.gohb.params.bo.client.UserProdBO;
import com.gohb.params.dto.client.UserProdDTO;
import com.gohb.params.dto.notify.NotifyDTO;

import java.util.List;

public interface UserProdManage {

    /**
     * 新增 userProd
     * @param userProdBO
     * @return
     */
    Boolean saveUserProd(UserProdBO userProdBO);

    /**
     * 根据id 删除 userProd
     * @param id
     * @return
     */
    Boolean deleteUserProd(Integer id);

    /**
     * 更新 userProd
     * @param userProdBO
     * @return
     */
    Boolean updateUserProd(UserProdBO userProdBO);

    /**
     * 查询所有 userProd，模糊查询
     * @return
     */
    List<UserProdDTO> listUserProd(UserProdBO userProdBO);

    /**
     * 查询 userProd 详情
     * @param id
     * @return
     */
    UserProdDTO detailUserProd(Integer id);

}
