package com.gohb.params.dto.prod;

import lombok.Data;

import java.io.Serializable;


@Data
public class SkuDTO implements Serializable {
    /**
     * 单品ID
     */
    private Long skuId;

    /**
     * 商品ID
     */
    private Long prodId;

    /**
     * 商品名
     */
    private String productName;

    /**
     * 属性值ID
     */
    private Long valueId;

    /**
     * 属性值
     */
    private String propValue;

    /**
     * 属性ID
     */
    private Long propId;

    /**
     * 属性名
     */
    private String propName;

    private static final long serialVersionUID = 1L;

}
