package com.gohb.params.bo.prod;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName prod_prop
 */
@TableName(value ="prod_prop")
@Data
public class ProdPropBO implements Serializable {
    /**
     * 属性id
     */
    @TableId(value = "prop_id", type = IdType.AUTO)
    private Long propId;

    /**
     * 属性名称
     */
    @TableField(value = "prop_name")
    private String propName;

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
        ProdPropBO other = (ProdPropBO) that;
        return (this.getPropId() == null ? other.getPropId() == null : this.getPropId().equals(other.getPropId()))
            && (this.getPropName() == null ? other.getPropName() == null : this.getPropName().equals(other.getPropName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPropId() == null) ? 0 : getPropId().hashCode());
        result = prime * result + ((getPropName() == null) ? 0 : getPropName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", propId=").append(propId);
        sb.append(", propName=").append(propName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
