package com.gohb.manage.Client.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gohb.manage.Client.ClientManage;
import com.gohb.params.bo.client.MyUserBO;
import com.gohb.params.bo.prod.ProdPropBO;
import com.gohb.params.bo.prod.ProdPropValueBO;
import com.gohb.params.bo.prod.ProductBO;
import com.gohb.params.bo.prod.SkuBO;
import com.gohb.params.bo.voucher.VoucherBO;
import com.gohb.params.dto.client.ProdDetailDTO;
import com.gohb.params.exception.ClientException;
import com.gohb.service.client.MyUserService;
import com.gohb.service.prod.ProdPropService;
import com.gohb.service.prod.ProdPropValueService;
import com.gohb.service.prod.ProductService;
import com.gohb.service.prod.SkuService;
import com.gohb.service.voucher.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import java.util.*;
import java.util.stream.Collectors;

@ManagedBean
public class ClientManageImpl implements ClientManage {

    @Autowired
    private MyUserService myUserService;

    @Autowired
    private VoucherService voucherService;

    @Autowired
    private ProductService productService;

    @Autowired
    private SkuService skuService;

    @Autowired
    private ProdPropValueService prodPropValueService;

    @Autowired
    private ProdPropService prodPropService;

    @Override
    public Boolean useVoucher(String voucherCode, String email) throws ClientException{
        MyUserBO myUserBO = myUserService.getOne(new LambdaQueryWrapper<MyUserBO>()
                .eq(MyUserBO::getEmail, email));
        VoucherBO voucherBO = voucherService.getOne(new LambdaQueryWrapper<VoucherBO>()
                .eq(VoucherBO::getVoucherCode, voucherCode));
        if (voucherBO == null || voucherBO.getUserId() != null) {
            throw new ClientException("兑换码失效");
        }
        if (voucherBO.getExpireTime().after(new Date())) {
            throw new ClientException("兑换码已过期");
        }
        myUserBO.setMember(0);
        boolean saveUser = myUserService.save(myUserBO);
        if (saveUser) {
            voucherBO.setUserId(myUserBO.getUserId());
            boolean save = voucherService.updateById(voucherBO);
            return save;
        }
        return false;
    }

    public List<ProdDetailDTO> listProd() {
        List<ProductBO> productBOS = productService.list();
        Map<Long, ProductBO> productBOMap = new HashMap<>();
        productBOS.forEach(productBO -> productBOMap.put(productBO.getProdId(), productBO));
        List<SkuBO> skuBOS = skuService.list(new LambdaQueryWrapper<SkuBO>()
                .in(SkuBO::getProdId, new ArrayList<>(productBOMap.keySet())));
        List<Long> valueIds = skuBOS.stream().map(SkuBO::getValueId).collect(Collectors.toList());
        Map<Long, List<Long>> skuMap = new HashMap<>(); // Map<productId, List<valueId>>
        skuBOS.forEach(skuBO -> {
            List<Long> valueIdList = skuMap.getOrDefault(skuBO.getProdId(), new ArrayList<Long>());
            valueIdList.add(skuBO.getValueId());
            skuMap.put(skuBO.getProdId(), valueIdList);
        });
        List<ProdPropValueBO> prodPropValueBOS = prodPropValueService.list(new LambdaQueryWrapper<ProdPropValueBO>()
                .in(ProdPropValueBO::getValueId, valueIds));
        Map<Long, ProdPropValueBO> prodPropValueBOMap = new HashMap<>();
        Map<Long, ProdPropBO> prodPropBOMap = new HashMap<>();
        prodPropValueBOS.forEach(prodPropValueBO -> {
            prodPropValueBOMap.put(prodPropValueBO.getValueId(), prodPropValueBO);
            prodPropBOMap.put(prodPropValueBO.getPropId(), new ProdPropBO());
        });
        List<ProdPropBO> prodPropBOS = prodPropService.list(new LambdaQueryWrapper<ProdPropBO>()
                .in(ProdPropBO::getPropId, new ArrayList<>(prodPropBOMap.keySet())));
        prodPropBOS.forEach(prodPropBO -> prodPropBOMap.put(prodPropBO.getPropId(), prodPropBO));
        // 组装数据
        List<ProdDetailDTO> prodDetailDTOS = new ArrayList<>();
        for (Map.Entry<Long, ProductBO> entry : productBOMap.entrySet()) {
            ProdDetailDTO prodDetailDTO = new ProdDetailDTO();
            Long productId = entry.getKey();
            prodDetailDTO.setProductId(productId);
            ProductBO productBO = entry.getValue();
            prodDetailDTO.setPrice(String.valueOf(productBO.getPrice()));
            prodDetailDTO.setProdName(productBO.getProdName());
            List<String> props = new ArrayList<>();
            List<Long> valueIdList = skuMap.get(productId);
            for (Long valueId : valueIdList) {
                ProdPropValueBO propValueBO = prodPropValueBOMap.get(valueId);
                ProdPropBO prodPropBO = prodPropBOMap.get(propValueBO.getPropId());
                String prop = prodPropBO.getPropName() + " : " + propValueBO.getPropValue();
                props.add(prop);
            }
            prodDetailDTO.setProps(props);
            prodDetailDTOS.add(prodDetailDTO);
        }
        return prodDetailDTOS;
    }



}
