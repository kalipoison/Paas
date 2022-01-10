package com.gohb.manage.prod.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gohb.convert.BoToDtoUtils;
import com.gohb.manage.prod.ProdPropManage;
import com.gohb.manage.prod.ProdPropValueManage;
import com.gohb.params.bo.prod.ProdPropBO;
import com.gohb.params.bo.prod.ProdPropValueBO;
import com.gohb.params.dto.prod.ProdPropValueDTO;
import com.gohb.service.prod.ProdPropValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class ProdPropValueManageImpl implements ProdPropValueManage {

    @Autowired
    private ProdPropValueService prodPropValueService;


    @Override
    public Boolean saveProdPropValue(ProdPropValueBO prodPropValueBO) {
        boolean save = prodPropValueService.save(prodPropValueBO);
        return save;
    }

    @Override
    public Boolean deleteProdPropValue(Integer id) {
        boolean delete = prodPropValueService.removeById(id);
        return delete;
    }

    @Override
    public Boolean updateProdPropValue(ProdPropValueBO prodPropValueBO) {
        boolean update = prodPropValueService.updateById(prodPropValueBO);
        return update;
    }

    @Override
    public List<ProdPropValueDTO> listProdPropValue(ProdPropValueBO prodPropValueBO) {
        List<ProdPropValueBO> prodPropValueBOS = prodPropValueService.list(new LambdaQueryWrapper<ProdPropValueBO>()
                .like(StringUtils.hasText(prodPropValueBO.getPropValue()), ProdPropValueBO::getPropValue, prodPropValueBO.getPropValue()));
        List<ProdPropValueDTO> prodPropValueDTOS = new ArrayList<>();
        for (ProdPropValueBO propValueBO : prodPropValueBOS) {
            ProdPropValueDTO prodPropValueDTO = BoToDtoUtils.prodPropValueBOTOProdPropValueDTO(propValueBO);
            prodPropValueDTOS.add(prodPropValueDTO);
        }
        return prodPropValueDTOS;
    }
}
