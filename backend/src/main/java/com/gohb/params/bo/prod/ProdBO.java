package com.gohb.params.bo.prod;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 商品
 * @TableName prod
 */
@TableName(value ="prod")
@Data
public class ProdBO implements Serializable {
    /**
     * 产品ID
     */
    @TableId(value = "prod_id", type = IdType.AUTO)
    private Long prodId;

    /**
     * 商品名称
     */
    @TableField(value = "prod_name")
    private String prodName;

    /**
     * 现价
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 简要描述,卖点等
     */
    @TableField(value = "brief")
    private String brief;

    /**
     * 详细描述
     */
    @TableField(value = "content")
    private String content;

    /**
     * 默认是1，表示正常状态, -1表示删除, 0下架
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 录入时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 上架时间
     */
    @TableField(value = "putaway_time")
    private Date putawayTime;

    /**
     * 版本 乐观锁
     */
    @TableField(value = "version")
    private Integer version;

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
        ProdBO other = (ProdBO) that;
        return (this.getProdId() == null ? other.getProdId() == null : this.getProdId().equals(other.getProdId()))
            && (this.getProdName() == null ? other.getProdName() == null : this.getProdName().equals(other.getProdName()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getBrief() == null ? other.getBrief() == null : this.getBrief().equals(other.getBrief()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getPutawayTime() == null ? other.getPutawayTime() == null : this.getPutawayTime().equals(other.getPutawayTime()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getProdId() == null) ? 0 : getProdId().hashCode());
        result = prime * result + ((getProdName() == null) ? 0 : getProdName().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getBrief() == null) ? 0 : getBrief().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getPutawayTime() == null) ? 0 : getPutawayTime().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", prodId=").append(prodId);
        sb.append(", prodName=").append(prodName);
        sb.append(", price=").append(price);
        sb.append(", brief=").append(brief);
        sb.append(", content=").append(content);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", putawayTime=").append(putawayTime);
        sb.append(", version=").append(version);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
