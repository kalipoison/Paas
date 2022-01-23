package com.gohb.params.bo.prod;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import lombok.Data;

/**
 * 订单表
 * @TableName order
 */
@TableName(value ="`order`")
@Data
public class OrderBO implements Serializable, Delayed {
    /**
     * 订单ID
     */
    @TableId(value = "order_id", type = IdType.AUTO)
    private Long orderId;
    /**
     * 延迟队列使用
     */
    @TableField(exist = false)
    private Date time;
    /**
     * 订购用户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 用户名
     */
    @TableField(exist = false)
    private String email;

    /**
     * 产品ID
     */
    @TableField(value = "prod_id")
    private Long prodId;

    /**
     * 订购流水号
     */
    @TableField(value = "order_number")
    private String orderNumber;

    /**
     * 总值
     */
    @TableField(value = "total")
    private BigDecimal total;

    /**
     * 实际总值
     */
    @TableField(value = "actual_total")
    private BigDecimal actualTotal;

    /**
     * 支付方式 0 手动代付 1 微信支付 2 支付宝
     */
    @TableField(value = "pay_type")
    private Integer payType;

    /**
     * 订单状态 1:成功 0:失败
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 订购时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 订单更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 付款时间
     */
    @TableField(value = "pay_time")
    private Date payTime;

    /**
     * 完成时间
     */
    @TableField(value = "finally_time")
    private Date finallyTime;

    /**
     * 取消时间
     */
    @TableField(value = "cancel_time")
    private Date cancelTime;


    /**
     * 是否已经支付，1：已经支付过，0：没有支付过
     */
    @TableField(value = "is_payed")
    private Integer isPayed;

    /**
     * 用户订单删除状态，0：没有删除， 1：回收站， 2：永久删除
     */
    @TableField(value = "delete_status")
    private Integer deleteStatus;

    /**
     * 0:在处理,1:处理完成
     */
    @TableField(value = "refund_sts")
    private Integer refundSts;

    /**
     * 优惠总额
     */
    @TableField(value = "reduce_amount")
    private BigDecimal reduceAmount;

    /**
     * 订单关闭原因 1-超时未支付 2-退款关闭 3-买家取消
     */
    @TableField(value = "close_type")
    private Integer closeType;

    /**
     * 镜像名
     */
    @TableField(value = "image_name")
    private String imageName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        OrderBO other = (OrderBO) that;
        return (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getProdId() == null ? other.getProdId() == null : this.getProdId().equals(other.getProdId()))
            && (this.getOrderNumber() == null ? other.getOrderNumber() == null : this.getOrderNumber().equals(other.getOrderNumber()))
            && (this.getTotal() == null ? other.getTotal() == null : this.getTotal().equals(other.getTotal()))
            && (this.getActualTotal() == null ? other.getActualTotal() == null : this.getActualTotal().equals(other.getActualTotal()))
            && (this.getPayType() == null ? other.getPayType() == null : this.getPayType().equals(other.getPayType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getPayTime() == null ? other.getPayTime() == null : this.getPayTime().equals(other.getPayTime()))
            && (this.getFinallyTime() == null ? other.getFinallyTime() == null : this.getFinallyTime().equals(other.getFinallyTime()))
            && (this.getCancelTime() == null ? other.getCancelTime() == null : this.getCancelTime().equals(other.getCancelTime()))
            && (this.getIsPayed() == null ? other.getIsPayed() == null : this.getIsPayed().equals(other.getIsPayed()))
            && (this.getDeleteStatus() == null ? other.getDeleteStatus() == null : this.getDeleteStatus().equals(other.getDeleteStatus()))
            && (this.getRefundSts() == null ? other.getRefundSts() == null : this.getRefundSts().equals(other.getRefundSts()))
            && (this.getReduceAmount() == null ? other.getReduceAmount() == null : this.getReduceAmount().equals(other.getReduceAmount()))
            && (this.getCloseType() == null ? other.getCloseType() == null : this.getCloseType().equals(other.getCloseType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getProdId() == null) ? 0 : getProdId().hashCode());
        result = prime * result + ((getOrderNumber() == null) ? 0 : getOrderNumber().hashCode());
        result = prime * result + ((getTotal() == null) ? 0 : getTotal().hashCode());
        result = prime * result + ((getActualTotal() == null) ? 0 : getActualTotal().hashCode());
        result = prime * result + ((getPayType() == null) ? 0 : getPayType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getPayTime() == null) ? 0 : getPayTime().hashCode());
        result = prime * result + ((getFinallyTime() == null) ? 0 : getFinallyTime().hashCode());
        result = prime * result + ((getCancelTime() == null) ? 0 : getCancelTime().hashCode());
        result = prime * result + ((getIsPayed() == null) ? 0 : getIsPayed().hashCode());
        result = prime * result + ((getDeleteStatus() == null) ? 0 : getDeleteStatus().hashCode());
        result = prime * result + ((getRefundSts() == null) ? 0 : getRefundSts().hashCode());
        result = prime * result + ((getReduceAmount() == null) ? 0 : getReduceAmount().hashCode());
        result = prime * result + ((getCloseType() == null) ? 0 : getCloseType().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderId=").append(orderId);
        sb.append(", userId=").append(userId);
        sb.append(", prodId=").append(prodId);
        sb.append(", orderNumber=").append(orderNumber);
        sb.append(", total=").append(total);
        sb.append(", actualTotal=").append(actualTotal);
        sb.append(", payType=").append(payType);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", payTime=").append(payTime);
        sb.append(", finallyTime=").append(finallyTime);
        sb.append(", cancelTime=").append(cancelTime);
        sb.append(", isPayed=").append(isPayed);
        sb.append(", deleteStatus=").append(deleteStatus);
        sb.append(", refundSts=").append(refundSts);
        sb.append(", reduceAmount=").append(reduceAmount);
        sb.append(", closeType=").append(closeType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(time.getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return time.compareTo(((OrderBO) o).getTime());
    }
}
