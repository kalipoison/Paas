package com.gohb.manage.prod;

import com.gohb.params.bo.prod.OrderBO;
import com.gohb.params.dto.notify.NotifyDTO;
import com.gohb.params.dto.prod.OrderDTO;

import java.util.List;

public interface OrderManage {

    /**
     * 提交 订单
     * @param orderBO
     * @return
     */
    Boolean submitOrder(OrderBO orderBO);

    /**
     * 根据id 删除 订单
     * @param id
     * @return
     */
    Boolean deleteOrder(Integer id);

    /**
     * 修改订单为 已经支付
     * @param orderNum
     * @return
     */
    Boolean isPayOrder(String orderNum);

    /**
     * 取消订单
     * @param
     * @return
     */
    Boolean cancelOrder(String orderNum);

    /**
     * 查询所有 订单，模糊查询
     * @return
     */
    List<OrderDTO> listOrder(OrderBO orderBO);

    /**
     * 查询 订单 详情
     * @param orderId
     * @return
     */
    OrderDTO detailOrder(Integer orderId);

    /**
     * 获取 用户订单
     * @param username
     * @return
     */
    List<OrderDTO> getUserOrder(String username);

}
