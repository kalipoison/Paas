package com.gohb.manage.prod.impl;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gohb.convert.BoToDtoUtils;
import com.gohb.manage.prod.OrderManage;
import com.gohb.params.bo.client.MyUserBO;
import com.gohb.params.bo.prod.OrderBO;
import com.gohb.params.bo.prod.ProductBO;
import com.gohb.params.dto.notify.NotifyDTO;
import com.gohb.params.dto.prod.OrderDTO;
import com.gohb.service.client.MyUserService;
import com.gohb.service.prod.OrderService;
import com.gohb.service.prod.ProductService;
import com.gohb.utils.SnowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.annotation.ManagedBean;
import java.util.*;
import java.util.stream.Collectors;

@ManagedBean
public class OrderManageImpl implements OrderManage {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private MyUserService myUserService;

    @Override
    public Boolean submitOrder(OrderBO orderBO) {
        ProductBO productBO = productService.getProductById(orderBO.getProdId());
        MyUserBO myUserBO = myUserService.getByName(orderBO.getUsername());
        orderBO.setUserId(myUserBO.getUserId());
        Snowflake snowflake = SnowUtil.snowflake;
        orderBO.setOrderNumber(snowflake.nextIdStr());
        orderBO.setTotal(productBO.getPrice());
        orderBO.setActualTotal(productBO.getPrice());
        orderBO.setIsPayed(0);
        orderBO.setDeleteStatus(0);
        orderBO.setCreateTime(new Date());
        orderBO.setRefundSts(0);
        boolean save = orderService.save(orderBO);
        return save;
    }

    @Override
    public Boolean deleteOrder(Integer id) {
        boolean delete = orderService.removeById(id);
        return null;
    }

    @Override
    public Boolean isPayOrder(String orderNum) {
        OrderBO order = orderService.getOne(new LambdaQueryWrapper<OrderBO>()
                .eq(OrderBO::getOrderNumber, orderNum)
        );
        order.setStatus(1);
        order.setIsPayed(1);
        order.setPayTime(new Date());
        order.setUpdateTime(new Date());
        boolean update = orderService.updateById(order);
        return update;
    }

    @Override
    public Boolean cancelOrder(String orderNum) {
        OrderBO order = orderService.getOne(new LambdaQueryWrapper<OrderBO>()
                .eq(OrderBO::getOrderNumber, orderNum)
        );
        order.setCancelTime(new Date());
        order.setStatus(1);
        boolean update = orderService.updateById(order);
        return update;
    }


    @Override
    public List<OrderDTO> listOrder(OrderBO orderBO) {
        List<OrderBO> orderBOS = orderService.list(new LambdaQueryWrapper<OrderBO>()
                .eq(StringUtils.hasText(orderBO.getOrderNumber()),OrderBO::getOrderNumber, orderBO.getOrderNumber()));
        List<Long> prodIdList = orderBOS.stream().map(OrderBO::getProdId).collect(Collectors.toList());
        List<ProductBO> productBOS = productService.list(new LambdaQueryWrapper<ProductBO>()
                .in(ProductBO::getProdId, prodIdList));
        Map<Long, ProductBO> productBOMap = new HashMap<>();
        productBOS.forEach(productBO -> productBOMap.put(productBO.getProdId(), productBO));
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for (OrderBO order : orderBOS) {
            OrderDTO orderDTO = BoToDtoUtils.OrderBoTOOrderDTO(order);
            orderDTO.setProductName(productBOMap.get(order.getProdId()).getProdName());
            orderDTOS.add(orderDTO);
        }
        return orderDTOS;
    }

    @Override
    public OrderDTO detailOrder(Integer orderId) {
        OrderBO orderBO = orderService.getOne(new LambdaQueryWrapper<OrderBO>()
                .eq(OrderBO::getOrderId, orderId));
        OrderDTO orderDTO = BoToDtoUtils.OrderBoTOOrderDTO(orderBO);
        return orderDTO;
    }

    @Override
    public List<OrderDTO> getUserOrder(String username) {
        MyUserBO myUserBO = myUserService.getByName(username);
        List<OrderBO> orderBOS = orderService.list(new LambdaQueryWrapper<OrderBO>()
                .eq(OrderBO::getUserId, myUserBO.getUserId()));
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for (OrderBO orderBO : orderBOS) {
            OrderDTO orderDTO = BoToDtoUtils.OrderBoTOOrderDTO(orderBO);
            orderDTOS.add(orderDTO);
        }
        return orderDTOS;
    }

}
