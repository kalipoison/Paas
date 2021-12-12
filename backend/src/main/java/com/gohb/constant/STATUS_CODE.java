package com.gohb.constant;

public interface STATUS_CODE {

    /**
     * k8s 操作已存在
     */
    public Integer isExist = 600;

    /**
     * k8s 不存在
     */
    public Integer isNotExist = 601;

    /**
     * 新增操作失败
     */
    public Integer createFail = 700;

    /**
     * 删除操作失败
     */
    public Integer deleteFail = 701;

    /**
     * 更新操作失败
     */
    public Integer updateFail = 702;

}
