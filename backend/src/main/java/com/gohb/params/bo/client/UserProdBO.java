package com.gohb.params.bo.client;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName user_prod
 */
@TableName(value ="user_prod")
@Data
public class UserProdBO implements Serializable {
    /**
     *
     */
    @TableId(value = "user_prod_id", type = IdType.AUTO)
    private Long userProdId;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 用户名
     */
    @TableField(exist = false)
    private String username;

    /**
     * 产品id
     */
    @TableField(value = "prod_id")
    private Long prodId;

    /**
     * 镜像
     */
    @TableField(value = "image")
    private String image;

    /**
     * 容器名
     */
    @TableField(value = "container_name")
    private String containerName;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 到期时间
     */
    @TableField(value = "expire_time")
    private Date expireTime;

    /**
     * 有效天数
     */
    @TableField(exist = false)
    private Integer validateDay;

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
        UserProdBO other = (UserProdBO) that;
        return (this.getUserProdId() == null ? other.getUserProdId() == null : this.getUserProdId().equals(other.getUserProdId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getProdId() == null ? other.getProdId() == null : this.getProdId().equals(other.getProdId()))
            && (this.getImage() == null ? other.getImage() == null : this.getImage().equals(other.getImage()))
            && (this.getContainerName() == null ? other.getContainerName() == null : this.getContainerName().equals(other.getContainerName()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getExpireTime() == null ? other.getExpireTime() == null : this.getExpireTime().equals(other.getExpireTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserProdId() == null) ? 0 : getUserProdId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getProdId() == null) ? 0 : getProdId().hashCode());
        result = prime * result + ((getImage() == null) ? 0 : getImage().hashCode());
        result = prime * result + ((getContainerName() == null) ? 0 : getContainerName().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getExpireTime() == null) ? 0 : getExpireTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userProdId=").append(userProdId);
        sb.append(", userId=").append(userId);
        sb.append(", prodId=").append(prodId);
        sb.append(", image=").append(image);
        sb.append(", containerName=").append(containerName);
        sb.append(", createTime=").append(createTime);
        sb.append(", expireTime=").append(expireTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
