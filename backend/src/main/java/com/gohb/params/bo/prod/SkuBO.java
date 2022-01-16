package com.gohb.params.bo.prod;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 单品SKU表
 * @TableName sku
 */
@TableName(value ="sku")
@Data
public class SkuBO implements Serializable {
    /**
     * 单品ID
     */
    @TableId(value = "sku_id", type = IdType.AUTO)
    private Long skuId;

    /**
     * 商品ID
     */
    @TableField(value = "prod_id")
    private Long prodId;

    /**
     * 属性值ID
     */
    @TableField(value = "value_id")
    private Long valueId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 商品名
     */
    @TableField(exist = false)
    private String productName;

}
