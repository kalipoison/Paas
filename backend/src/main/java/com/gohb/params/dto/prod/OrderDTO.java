package com.gohb.params.dto.prod;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderDTO implements Serializable {
    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 订购用户ID
     */
    private Long userId;

    /**
     * 产品名
     */
    private String productName;

    /**
     * 产品ID
     */
    private Long prodId;

    /**
     * 订购流水号
     */
    private String orderNumber;

    /**
     * 总值
     */
    private BigDecimal total;

    /**
     * 实际总值
     */
    private BigDecimal actualTotal;

    /**
     * 支付方式 0 手动代付 1 微信支付 2 支付宝
     */
    private Integer payType;

    /**
     * 订单状态 1:成功 0:失败
     */
    private Integer status;

    /**
     * 订购时间
     */
    private Date createTime;

    /**
     * 订单更新时间
     */
    private Date updateTime;

    /**
     * 付款时间
     */
    private Date payTime;

    /**
     * 完成时间
     */
    private Date finallyTime;

    /**
     * 取消时间
     */
    private Date cancelTime;

    /**
     * 是否已经支付，1：已经支付过，0：，没有支付过
     */
    private Boolean isPayed;

    /**
     * 用户订单删除状态，0：没有删除， 1：回收站， 2：永久删除
     */
    private Integer deleteStatus;

    /**
     * 0:默认,1:在处理,2:处理完成
     */
    private Integer refundSts;

    /**
     * 优惠总额
     */
    private BigDecimal reduceAmount;

    /**
     * 订单关闭原因 1-超时未支付 2-退款关闭 4-买家取消
     */
    private Byte closeType;

    private static final long serialVersionUID = 1L;


}
