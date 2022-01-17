package com.gohb.manage.notify;


import com.gohb.params.bo.notify.NotifyBO;
import com.gohb.params.dto.notify.NotifyDTO;

import java.util.List;

public interface NotifyManage {

    /**
     * 新增 notify
     * @param notifyBO
     * @return
     */
    Boolean saveNotify(NotifyBO notifyBO);

    /**
     * 根据id 删除 notify
     * @param id
     * @return
     */
    Boolean deleteNotify(Integer id);

    /**
     * 更新 Notify
     * @param notifyBO
     * @return
     */
    Boolean updateNotify(NotifyBO notifyBO);

    /**
     * 查询所有 Notify，模糊查询
     * @return
     */
    List<NotifyDTO> listNotify(NotifyBO notifyBO);

    /**
     * 查询 notify 详情
     * @param notifyId
     * @return
     */
    NotifyDTO detailNotify(Integer notifyId);

}
