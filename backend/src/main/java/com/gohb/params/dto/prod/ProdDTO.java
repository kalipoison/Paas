package com.gohb.params.dto.prod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdDTO implements Serializable {
    /**
     * 产品ID
     */
    private Long prodId;

    /**
     * 商品名称
     */
    private String prodName;

    /**
     * 现价
     */
    private BigDecimal price;

    /**
     * 简要描述,卖点等
     */
    private String brief;

    /**
     * 详细描述
     */
    private String content;

    /**
     * 默认是1，表示正常状态, -1表示删除, 0下架
     */
    private Integer status;

    /**
     * 录入时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 上架时间
     */
    private Date putawayTime;

    /**
     * 版本 乐观锁
     */
    private Integer version;


}
