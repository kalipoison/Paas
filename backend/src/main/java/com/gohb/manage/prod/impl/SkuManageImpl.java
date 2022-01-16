package com.gohb.manage.prod.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gohb.convert.BoToDtoUtils;
import com.gohb.manage.prod.SkuManage;
import com.gohb.params.bo.prod.ProdPropBO;
import com.gohb.params.bo.prod.ProdPropValueBO;
import com.gohb.params.bo.prod.ProductBO;
import com.gohb.params.bo.prod.SkuBO;
import com.gohb.params.dto.prod.ProdPropValueDTO;
import com.gohb.params.dto.prod.SkuDTO;
import com.gohb.service.prod.ProdPropService;
import com.gohb.service.prod.ProdPropValueService;
import com.gohb.service.prod.ProductService;
import com.gohb.service.prod.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.annotation.ManagedBean;
import java.util.*;

@ManagedBean
public class SkuManageImpl implements SkuManage {

    @Autowired
    private SkuService skuService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProdPropService prodPropService;

    @Autowired
    private ProdPropValueService prodPropValueService;

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
        List<SkuBO> skuBOS = skuService.list();
        if (skuBOS == null && skuBOS.size() == 0) {
            return new ArrayList<>();
        }
        Map<Long, ProductBO> prodIdsMap = new HashMap<>();
        Map<Long, ProdPropValueBO> valueIdsMap = new HashMap<>();
        Map<Long, ProdPropBO> propIdsMap = new HashMap<>();
        skuBOS.forEach(sku -> {
            prodIdsMap.put(sku.getProdId(), new ProductBO());
            valueIdsMap.put(sku.getValueId(), new ProdPropValueBO());
        });
        List<ProductBO> productBOS = productService.list(new LambdaQueryWrapper<ProductBO>()
                .in(ProductBO::getProdId, new ArrayList<>(prodIdsMap.keySet())));
        productBOS.forEach(productBO -> prodIdsMap.put(productBO.getProdId(), productBO));
        List<ProdPropValueBO> prodPropValueBOS = prodPropValueService.list(new LambdaQueryWrapper<ProdPropValueBO>()
                .in(ProdPropValueBO::getValueId, new ArrayList<>(valueIdsMap.keySet())));
        prodPropValueBOS.forEach(prodPropValueBO -> {
            valueIdsMap.put(prodPropValueBO.getValueId(), prodPropValueBO);
            propIdsMap.put(prodPropValueBO.getPropId(), new ProdPropBO());
        });
        List<ProdPropBO> prodPropBOS = prodPropService.list(new LambdaQueryWrapper<ProdPropBO>()
                .in(ProdPropBO::getPropId, new ArrayList<>(propIdsMap.keySet())));
        prodPropBOS.forEach(prodPropBO -> propIdsMap.put(prodPropBO.getPropId(), prodPropBO));
        List<SkuDTO> skuDTOS = new ArrayList<>();
        for (SkuBO sku : skuBOS) {
            SkuDTO skuDTO = BoToDtoUtils.skuBoTOSkuDTO(sku);
            skuDTO.setProductName(prodIdsMap.get(skuDTO.getProdId()).getProdName());
            ProdPropValueBO propValueBO = valueIdsMap.get(sku.getValueId());
            skuDTO.setPropValue(propValueBO.getPropValue());
            skuDTO.setPropId(propValueBO.getPropId());
            skuDTO.setPropName(propIdsMap.get(propValueBO.getPropId()).getPropName());
            skuDTOS.add(skuDTO);
        }
        if (skuBO != null && StringUtils.hasText(skuBO.getProductName())) {
            skuDTOS.stream().filter(skuDTO -> skuDTO.getProductName().equals(skuBO.getProductName()));
        }
        return skuDTOS;
    }
}
