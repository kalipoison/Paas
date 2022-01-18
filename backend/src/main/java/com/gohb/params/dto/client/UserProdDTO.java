package com.gohb.params.dto.client;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserProdDTO implements Serializable {
    /**
     *
     */
    private Long userProdId;

    /**
     * 用户id
     */
    private Long userId;

    private String username;

    /**
     * 产品id
     */
    private Long prodId;

    private String productName;

    /**
     * 镜像
     */
    private String image;

    /**
     * 容器名
     */
    private String containerName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 到期时间
     */
    private Date expireTime;

    /**
     * 有效天数
     */
    private Integer validateDay;

    private static final long serialVersionUID = 1L;


}
