package com.gohb.manage.prod.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gohb.convert.BoToDtoUtils;
import com.gohb.manage.prod.ProdPropManage;
import com.gohb.params.bo.prod.ProdPropBO;
import com.gohb.params.dto.prod.ProdPropDTO;
import com.gohb.service.prod.ProdPropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class ProdPropManageImpl implements ProdPropManage {

    @Autowired
    private ProdPropService prodPropService;

    @Override
    public Boolean saveProdProp(ProdPropBO prodPropBO) {
        boolean save = prodPropService.save(prodPropBO);
        return save;
    }

    @Override
    public Boolean deleteProdProp(Integer id) {
        boolean delete = prodPropService.removeById(id);
        return delete;
    }

    @Override
    public Boolean updateProdProp(ProdPropBO prodPropBO) {
        boolean update = prodPropService.updateById(prodPropBO);
        return update;
    }

    @Override
    public List<ProdPropDTO> listProdProp(ProdPropBO prodPropBO) {
        List<ProdPropBO> prodPropBOS = prodPropService.list(new LambdaQueryWrapper<ProdPropBO>()
                .like(StringUtils.hasText(prodPropBO.getPropName()), ProdPropBO::getPropName, prodPropBO.getPropName()));
        List<ProdPropDTO> prodPropDTOS = new ArrayList<>();
        for (ProdPropBO propBO : prodPropBOS) {
            ProdPropDTO prodPropDTO = BoToDtoUtils.prodPropBOTOProdPropDTO(propBO);
            prodPropDTOS.add(prodPropDTO);
        }
        return prodPropDTOS;
    }
}
