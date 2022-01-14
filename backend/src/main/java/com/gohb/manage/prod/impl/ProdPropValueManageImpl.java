package com.gohb.manage.prod.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gohb.convert.BoToDtoUtils;
import com.gohb.manage.prod.ProdPropManage;
import com.gohb.manage.prod.ProdPropValueManage;
import com.gohb.params.bo.prod.ProdPropBO;
import com.gohb.params.bo.prod.ProdPropValueBO;
import com.gohb.params.dto.prod.ProdPropValueDTO;
import com.gohb.service.prod.ProdPropService;
import com.gohb.service.prod.ProdPropValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ManagedBean
public class ProdPropValueManageImpl implements ProdPropValueManage {

    @Autowired
    private ProdPropValueService prodPropValueService;

    @Autowired
    private ProdPropService prodPropService;

    @Override
    public Boolean saveProdPropValue(ProdPropValueBO prodPropValueBO) {
        boolean save = prodPropValueService.save(prodPropValueBO);
        return save;
    }

    @Override
    public ProdPropValueDTO detailProdPropValue(Integer valueId) {
        ProdPropValueBO prodPropValueBO = prodPropValueService.getOne(new LambdaQueryWrapper<ProdPropValueBO>()
                .eq(ProdPropValueBO::getValueId, valueId));
        ProdPropValueDTO prodPropValueDTO = BoToDtoUtils.prodPropValueBOTOProdPropValueDTO(prodPropValueBO);
        ProdPropBO prodPropBO = prodPropService.getOne(new LambdaQueryWrapper<ProdPropBO>()
                .eq(ProdPropBO::getPropId, prodPropValueDTO.getPropId()));
        prodPropValueDTO.setPropName(prodPropBO.getPropName());
        return prodPropValueDTO;
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
        List<Long> propIdList = new ArrayList<>();
        for (ProdPropValueBO propValueBO : prodPropValueBOS) {
            propIdList.add(propValueBO.getPropId());
            ProdPropValueDTO prodPropValueDTO = BoToDtoUtils.prodPropValueBOTOProdPropValueDTO(propValueBO);
            prodPropValueDTOS.add(prodPropValueDTO);
        }

        List<ProdPropBO> prodPropBOS = prodPropService.list(new LambdaQueryWrapper<ProdPropBO>()
                .in(ProdPropBO::getPropId, propIdList));
        Map<Long, String> prodPropMap = new HashMap<>();
        for (ProdPropBO prodBO : prodPropBOS) {
            prodPropMap.put(prodBO.getPropId(), prodBO.getPropName());
        }
        for (ProdPropValueDTO prodPropValueDTO : prodPropValueDTOS) {
            prodPropValueDTO.setPropName(prodPropMap.get(prodPropValueDTO.getPropId()));
        }
        return prodPropValueDTOS;
    }
}
