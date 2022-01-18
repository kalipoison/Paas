package com.gohb.manage.Client.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gohb.convert.BoToDtoUtils;
import com.gohb.convert.DateTimeUtils;
import com.gohb.manage.Client.UserProdManage;
import com.gohb.params.bo.client.MyUserBO;
import com.gohb.params.bo.client.UserProdBO;
import com.gohb.params.bo.prod.ProductBO;
import com.gohb.params.dto.client.UserProdDTO;
import com.gohb.params.dto.notify.NotifyDTO;
import com.gohb.service.client.MyUserService;
import com.gohb.service.client.UserProdService;
import com.gohb.service.prod.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import java.util.*;
import java.util.stream.Collectors;

@ManagedBean
public class UserProdManageImpl implements UserProdManage {

    @Autowired
    private UserProdService userProdService;

    @Autowired
    private MyUserService myUserService;

    @Autowired
    private ProductService productService;

    @Override
    public Boolean saveUserProd(UserProdBO userProdBO) {
        MyUserBO myUserBO = myUserService.getByName(userProdBO.getUsername());
        userProdBO.setUserId(myUserBO.getUserId());
        userProdBO.setCreateTime(new Date());
        userProdBO.setExpireTime(this.getExpireTime(new Date(), userProdBO.getValidateDay()));
        boolean save = userProdService.save(userProdBO);
        return save;
    }

    private Date getExpireTime(Date date, Integer validateDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);   //设置当前时间
        cal.add(Calendar.DATE, validateDay);
        return new Date(cal.getTimeInMillis());
    }

    @Override
    public Boolean deleteUserProd(Integer id) {
        boolean delete = userProdService.removeById(id);
        return delete;
    }

    @Override
    public Boolean updateUserProd(UserProdBO userProdBO) {
        if (userProdBO.getValidateDay() != null) {
            UserProdBO userProd = userProdService.getOne(new LambdaQueryWrapper<UserProdBO>()
                    .eq(UserProdBO::getUserProdId, userProdBO.getUserProdId()));
            Date expireTime = this.getExpireTime(userProd.getExpireTime(), userProdBO.getValidateDay());
            userProdBO.setExpireTime(expireTime);
        }
        boolean update = userProdService.updateById(userProdBO);
        return update;
    }

    @Override
    public List<UserProdDTO> listUserProd(UserProdBO userProdBO) {
        if (userProdBO != null && userProdBO.getUsername() != null && !"".equals(userProdBO.getUsername())) {
            MyUserBO myUserBO = myUserService.getByName(userProdBO.getUsername());
            Long userId = myUserBO.getUserId();
            List<UserProdBO> userProdBOS = userProdService.list(new LambdaQueryWrapper<UserProdBO>()
                    .eq(userId != null, UserProdBO::getUserId, userId));
            List<Long> prodIdList = userProdBOS.stream().map(UserProdBO::getProdId).collect(Collectors.toList());
            List<ProductBO> productBOS = productService.list(new LambdaQueryWrapper<ProductBO>()
                    .in(ProductBO::getProdId, prodIdList));
            Map<Long, ProductBO> productBOMap = new HashMap<>();
            productBOS.forEach(productBO -> productBOMap.put(productBO.getProdId(), productBO));
            List<UserProdDTO> userProdDTOS = new ArrayList<>();
            for (UserProdBO userProd : userProdBOS) {
                UserProdDTO userProdDTO = BoToDtoUtils.UserProdBoTOUserProdDTO(userProd);
                userProdDTO.setUsername(myUserBO.getEmail()); // email is userAccount username
                userProdDTO.setProductName(productBOMap.get(userProd.getProdId()).getProdName());
                userProdDTOS.add(userProdDTO);
            }
            return userProdDTOS;
        } else {
            List<UserProdBO> userProdBOS = userProdService.list();
            List<UserProdDTO> userProdDTOS = new ArrayList<>();
            if (userProdBOS.size() == 0) {
                return userProdDTOS;
            }
            Map<Long, MyUserBO> myUserBOMap = new HashMap<>();
            Map<Long, ProductBO> productBOMap = new HashMap<>();
            userProdBOS.forEach(userProd -> {
                myUserBOMap.put(userProd.getUserId(), new MyUserBO());
                productBOMap.put(userProd.getProdId(), new ProductBO());
            });
            List<MyUserBO> myUserBOS = myUserService.list(new LambdaQueryWrapper<MyUserBO>()
                    .in(MyUserBO::getUserId, new ArrayList<>(myUserBOMap.keySet())));
            myUserBOS.forEach(myUserBO -> myUserBOMap.put(myUserBO.getUserId(), myUserBO));
            List<ProductBO> productBOS = productService.list(new LambdaQueryWrapper<ProductBO>()
                    .in(ProductBO::getProdId, new ArrayList<>(productBOMap.keySet())));
            productBOS.forEach(productBO -> productBOMap.put(productBO.getProdId(), productBO));
            for (UserProdBO userProd : userProdBOS) {
                UserProdDTO userProdDTO = BoToDtoUtils.UserProdBoTOUserProdDTO(userProd);
                userProdDTO.setUsername(myUserBOMap.get(userProd.getUserId()).getEmail());
                userProdDTO.setProductName(productBOMap.get(userProd.getProdId()).getProdName());
                userProdDTOS.add(userProdDTO);
            }
            return userProdDTOS;
        }
    }

    @Override
    public UserProdDTO detailUserProd(Integer id) {
        UserProdBO userProdBO = userProdService.getOne(new LambdaQueryWrapper<UserProdBO>()
                .eq(UserProdBO::getUserProdId, id));
        MyUserBO myUserBO = myUserService.getOne(new LambdaQueryWrapper<MyUserBO>()
                .eq(MyUserBO::getUserId, userProdBO.getUserId()));
        ProductBO productBO = productService.getOne(new LambdaQueryWrapper<ProductBO>()
                .eq(ProductBO::getProdId, userProdBO.getProdId()));
        UserProdDTO userProdDTO = BoToDtoUtils.UserProdBoTOUserProdDTO(userProdBO);
        userProdDTO.setUsername(myUserBO.getEmail());
        userProdDTO.setProductName(productBO.getProdName());
        return userProdDTO;
    }
}
