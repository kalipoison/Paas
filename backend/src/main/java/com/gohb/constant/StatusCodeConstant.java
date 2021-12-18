package com.gohb.constant;

public interface StatusCodeConstant {

    /**
     * 登录失败
     */
    Integer loginFail = 201;

    /**
     * k8s 操作已存在
     */
    Integer isExist = 600;

    /**
     * k8s 不存在
     */
    Integer isNotExist = 601;

    /**
     * 新增操作失败
     */
    Integer createFail = 700;

    /**
     * 删除操作失败
     */
    Integer deleteFail = 701;

    /**
     * 更新操作失败
     */
    Integer updateFail = 702;

}
