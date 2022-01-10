package com.gohb.params.bo.prod;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName prod_prop_value
 */
@TableName(value ="prod_prop_value")
@Data
public class ProdPropValueBO implements Serializable {
    /**
     * 属性值ID
     */
    @TableId(value = "value_id", type = IdType.AUTO)
    private Long valueId;

    /**
     * 属性值名称
     */
    @TableField(value = "prop_value")
    private String propValue;

    /**
     * 属性ID
     */
    @TableField(value = "prop_id")
    private Long propId;

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
        ProdPropValueBO other = (ProdPropValueBO) that;
        return (this.getValueId() == null ? other.getValueId() == null : this.getValueId().equals(other.getValueId()))
            && (this.getPropValue() == null ? other.getPropValue() == null : this.getPropValue().equals(other.getPropValue()))
            && (this.getPropId() == null ? other.getPropId() == null : this.getPropId().equals(other.getPropId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getValueId() == null) ? 0 : getValueId().hashCode());
        result = prime * result + ((getPropValue() == null) ? 0 : getPropValue().hashCode());
        result = prime * result + ((getPropId() == null) ? 0 : getPropId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", valueId=").append(valueId);
        sb.append(", propValue=").append(propValue);
        sb.append(", propId=").append(propId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
