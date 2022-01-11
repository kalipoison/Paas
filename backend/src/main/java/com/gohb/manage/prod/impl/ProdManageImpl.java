package com.gohb.manage.prod.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gohb.convert.BoToDtoUtils;
import com.gohb.manage.prod.ProdManage;
import com.gohb.params.bo.prod.ProdBO;
import com.gohb.params.dto.prod.ProdDTO;
import com.gohb.params.dto.prod.ProdPropDTO;
import com.gohb.service.prod.ProdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class ProdManageImpl implements ProdManage {

    @Autowired
    private ProdService prodService;


    @Override
    public Boolean saveProd(ProdBO prodBO) {
        boolean save = prodService.save(prodBO);
        return save;
    }

    @Override
    public Boolean deleteProd(Integer id) {
        boolean delete = prodService.removeById(id);
        return delete;
    }

    @Override
    public Boolean updateProd(ProdBO prodBO) {
        boolean update = prodService.updateById(prodBO);
        return update;
    }

    @Override
    public List<ProdDTO> listProd(ProdBO prodBO) {
        List<ProdBO> prodBOS = prodService.list(new LambdaQueryWrapper<ProdBO>()
                .like(StringUtils.hasText(prodBO.getProdName()), ProdBO::getProdName, prodBO.getProdName()));
        List<ProdDTO> prodDTOS = new ArrayList<>();
        for (ProdBO prod : prodBOS) {
            prodDTOS.add(BoToDtoUtils.prodBOTOProdDTO(prod));
        }
        return prodDTOS;
    }
}
