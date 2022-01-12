package com.gohb.manage.prod.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gohb.convert.BoToDtoUtils;
import com.gohb.manage.prod.SkuManage;
import com.gohb.params.bo.prod.SkuBO;
import com.gohb.params.dto.prod.ProdPropValueDTO;
import com.gohb.params.dto.prod.SkuDTO;
import com.gohb.service.prod.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class SkuManageImpl implements SkuManage {

    @Autowired
    private SkuService skuService;

    @Override
    public Boolean saveSku(SkuBO skuBO) {
        boolean save = skuService.save(skuBO);
        return save;
    }

    @Override
    public Boolean deleteSku(Integer id) {
        boolean delete = skuService.removeById(id);
        return delete;
    }

    @Override
    public Boolean updateSku(SkuBO skuBO) {
        boolean update = skuService.updateById(skuBO);
        return update;
    }

    @Override
    public List<SkuDTO> listSku(SkuBO skuBO) {
        List<SkuBO> skuBOS = skuService.list(new LambdaQueryWrapper<SkuBO>()
                .like(StringUtils.hasText(skuBO.getProperties()), SkuBO::getProperties, skuBO.getProperties()));
        List<SkuDTO> skuDTOS = new ArrayList<>();
        for (SkuBO sku : skuBOS) {
            skuDTOS.add(BoToDtoUtils.skuBoTOSkuDTO(sku));
        }
        return skuDTOS;
    }
}
