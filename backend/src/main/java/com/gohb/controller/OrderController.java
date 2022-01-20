package com.gohb.controller;

import com.gohb.aop.anno.Log;
import com.gohb.constant.StatusCodeConstant;
import com.gohb.manage.prod.OrderManage;
import com.gohb.params.bo.notify.NotifyBO;
import com.gohb.params.bo.prod.OrderBO;
import com.gohb.params.dto.Result;
import com.gohb.params.dto.ResultUtils;
import com.gohb.params.dto.notify.NotifyDTO;
import com.gohb.params.dto.prod.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/order")
public class OrderController {

    @Autowired
    private OrderManage orderManage;

    /**
     * 新增 订单
     * @param orderBO
     * @return
     */
    @PostMapping
    public Result submitOrder(OrderBO orderBO) {
        Boolean save = orderManage.submitOrder(orderBO);
        if (!save) {
            return ResultUtils.getFailedResult(StatusCodeConstant.createFail, "新增 订单 失败");
        }
        return ResultUtils.getSuccessResult("新增 订单 成功");
    }

    /**
     * 删除 订单
     * @param id
     * @return
     */
    @DeleteMapping()
    public Result deleteOrder(Integer id) {
        Boolean delete = orderManage.deleteOrder(id);
        if (!delete) {
            return ResultUtils.getFailedResult(StatusCodeConstant.deleteFail, "删除 订单 失败");
        }
        return ResultUtils.getSuccessResult("删除 订单 成功");
    }

    /**
     * 修改 订单支付状态 信息
     * @param orderNum
     * @return
     */
    @PutMapping("/pay")
    @Log(operation = "修改 订单支付状态 信息")
    public Result isPayOrder(String orderNum) {
        Boolean update = orderManage.isPayOrder(orderNum);
        if (!update) {
            return ResultUtils.getFailedResult(StatusCodeConstant.updateFail, "更新 订单支付状态 信息失败");
        }
        return ResultUtils.getSuccessResult("更新 订单支付状态 成功");
    }

    /**
     * 取消 订单
     * @param orderNum
     * @return
     */
    @PutMapping("/cancel")
    @Log(operation = "取消 订单 ")
    public Result cancelOrder(String orderNum) {
        Boolean update = orderManage.cancelOrder(orderNum);
        if (!update) {
            return ResultUtils.getFailedResult(StatusCodeConstant.updateFail, "取消 订单 信息失败");
        }
        return ResultUtils.getSuccessResult("取消 订单 成功");
    }

    /**
     * 获取 订单 列表
     * @param orderBO
     * @return
     */
    @GetMapping("")
    @Log(operation = "查询 订单 列表")
    public Result<List<OrderDTO>> getOrderList(OrderBO orderBO) {
        List<OrderDTO> orderDTOS = orderManage.listOrder(orderBO);
        return ResultUtils.getSuccessResult(orderDTOS);
    }


    /**
     * 获取 订单 详情
     * @param id
     * @return
     */
    @GetMapping("/detail")
    @Log(operation = "查询 订单 详细信息")
    public Result<OrderDTO> getOrderDetail(Integer id) {
        OrderDTO orderDTO = orderManage.detailOrder(id);
        return ResultUtils.getSuccessResult(orderDTO);
    }


}
