package com.gohb.manage.notify.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gohb.convert.BoToDtoUtils;
import com.gohb.convert.DateTimeUtils;
import com.gohb.manage.notify.NotifyManage;
import com.gohb.params.bo.notify.NotifyBO;
import com.gohb.params.dto.notify.NotifyDTO;
import com.gohb.service.notify.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean
public class NotifyManageImpl implements NotifyManage {

    @Autowired
    private NotifyService notifyService;

    @Override
    public Boolean saveNotify(NotifyBO notifyBO) {
        notifyBO.setCreateTime(new Date());
        boolean save = notifyService.save(notifyBO);
        return save;
    }

    @Override
    public Boolean deleteNotify(Integer id) {
        boolean delete = notifyService.removeById(id);
        return delete;
    }

    @Override
    public Boolean updateNotify(NotifyBO notifyBO) {
        boolean update = notifyService.updateById(notifyBO);
        return update;
    }

    @Override
    public List<NotifyDTO> listNotify(NotifyBO notifyBO) {
        List<NotifyBO> notifyBOS = notifyService.list(new LambdaQueryWrapper<NotifyBO>()
                .like(StringUtils.hasText(notifyBO.getNotifyContent()), NotifyBO::getNotifyContent, notifyBO.getNotifyContent()));
        List<NotifyDTO> notifyDTOS = new ArrayList<>();
        for (NotifyBO notify : notifyBOS) {
            NotifyDTO notifyDTO = BoToDtoUtils.NotifyBoTONotifyDTO(notify);
            notifyDTO.setCreateTime(DateTimeUtils.DateToString(notify.getCreateTime()));
            notifyDTOS.add(notifyDTO);
        }
        return notifyDTOS;
    }

    @Override
    public NotifyDTO detailNotify(Integer notifyId) {
        NotifyBO notifyBO = notifyService.getOne(new LambdaQueryWrapper<NotifyBO>()
                .eq(NotifyBO::getNotifyId, notifyId));
        NotifyDTO notifyDTO = BoToDtoUtils.NotifyBoTONotifyDTO(notifyBO);
        notifyDTO.setCreateTime(DateTimeUtils.DateToString(notifyBO.getCreateTime()));
        return notifyDTO;
    }
}
