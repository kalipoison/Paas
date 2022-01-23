package com.gohb.manage.prod.impl;

import cn.hutool.core.date.DateUtil;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.StringUtils;

import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.DelayQueue;

@ManagedBean
public class OrderManageImpl implements OrderManage {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private MyUserService myUserService;

    @Value("${order.cancel.delay.time}")
    private long orderCancelDelayTime;

    /**
     * 延迟队列，用来存放订单对象
     */
    DelayQueue<OrderBO> orderDelayQueue = new DelayQueue<>();

    @Resource
    private ThreadPoolTaskExecutor executorService;


    @Override
    public String submitOrder(OrderBO orderBO) {
        ProductBO productBO = productService.getProductById(orderBO.getProdId());
        MyUserBO myUserBO = myUserService.getByName(orderBO.getEmail());
        orderBO.setUserId(myUserBO.getUserId());
        Snowflake snowflake = SnowUtil.snowflake;
        orderBO.setOrderNumber(snowflake.nextIdStr());
        orderBO.setTotal(productBO.getPrice());
        orderBO.setActualTotal(productBO.getPrice());
        orderBO.setIsPayed(0);
        orderBO.setDeleteStatus(0);
        orderBO.setCreateTime(new Date());
        orderBO.setTime(new Date(new Date().getTime() + orderCancelDelayTime));
        orderBO.setRefundSts(0);
        boolean save = orderService.save(orderBO);
        if (save) {
            this.writeToDelayQueue(orderBO);
            this.DelayQueueCancelOrder();
            return orderBO.getOrderNumber();
        }
        return "";
    }

    private DelayQueue<OrderBO> writeToDelayQueue(OrderBO orderBO) {
        executorService.submit(()->{
            try {
                orderDelayQueue.add(orderBO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return orderDelayQueue;
    }

    /**
     * 自动取消订单
     */
    private void DelayQueueCancelOrder() {
        //新建一个线程，用来模拟定时取消订单job
        executorService.submit(()->{
            try {
                System.out.println("开启自动取消订单job,当前时间：" + DateUtil.date());
                OrderBO orderBO = orderDelayQueue.take();
                OrderBO order = orderService.getOne(new LambdaQueryWrapper<OrderBO>()
                        .eq(OrderBO::getOrderNumber, orderBO.getOrderNumber()));
                if (orderBO.getCancelTime() == null && orderBO.getFinallyTime() == null &&
                        orderBO.getStatus() != null && orderBO.getStatus() == 1 &&
                        orderBO.getIsPayed() != null && orderBO.getIsPayed() == 1) {
                    order.setFinallyTime(new Date());
                } else {
                    Date cancelTime = orderBO.getCancelTime();
                    order.setCancelTime(cancelTime == null ? new Date() : cancelTime);
                }
                orderService.updateById(order);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    @Override
    public Boolean deleteOrder(Integer id) {
        boolean delete = orderService.removeById(id);
        return null;
    }

    @Override
    public Boolean updateOrder(OrderBO orderBO) {
        boolean update = orderService.updateById(orderBO);
        return update;
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
        order.setStatus(1);
        boolean update = orderService.updateById(order);
        return update;
    }


    @Override
    public List<OrderDTO> listOrder(OrderBO orderBO) {
        List<OrderBO> orderBOS = orderService.list(new LambdaQueryWrapper<OrderBO>()
                .like(StringUtils.hasText(orderBO.getOrderNumber()),OrderBO::getOrderNumber, orderBO.getOrderNumber()));
        if (orderBOS == null || orderBOS.size() == 0) {
            return new ArrayList<OrderDTO>();
        }
        Map<Long, ProductBO> productBOMap = new HashMap<>();
        Map<Long, MyUserBO> myUserBOMap = new HashMap<>();
        orderBOS.forEach(order -> {
            productBOMap.put(order.getProdId(), new ProductBO());
            myUserBOMap.put(order.getUserId(), new MyUserBO());
        });
        List<ProductBO> productBOS = productService.list(new LambdaQueryWrapper<ProductBO>()
                .in(ProductBO::getProdId, new ArrayList<>(productBOMap.keySet())));
        productBOS.forEach(productBO -> productBOMap.put(productBO.getProdId(), productBO));
        List<MyUserBO> myUserBOS = myUserService.list(new LambdaQueryWrapper<MyUserBO>()
                .in(MyUserBO::getUserId, new ArrayList<>(myUserBOMap.keySet())));
        myUserBOS.forEach(myUserBO -> myUserBOMap.put(myUserBO.getUserId(), myUserBO));
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for (OrderBO order : orderBOS) {
            OrderDTO orderDTO = BoToDtoUtils.OrderBoTOOrderDTO(order);
            orderDTO.setProductName(productBOMap.get(order.getProdId()).getProdName());
            orderDTO.setEmail(myUserBOMap.get(order.getUserId()).getEmail());
            orderDTOS.add(orderDTO);
        }
        return orderDTOS;
    }

    @Override
    public OrderDTO detailOrder(Integer orderId) {
        OrderBO orderBO = orderService.getOne(new LambdaQueryWrapper<OrderBO>()
                .eq(OrderBO::getOrderId, orderId));
        OrderDTO orderDTO = BoToDtoUtils.OrderBoTOOrderDTO(orderBO);
        MyUserBO myUserBO = myUserService.getById(orderBO.getUserId());
        orderDTO.setEmail(myUserBO.getEmail());
        ProductBO productBO = productService.getProductById(orderBO.getProdId());
        orderDTO.setProductName(productBO.getProdName());
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
