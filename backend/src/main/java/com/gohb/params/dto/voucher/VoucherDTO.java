package com.gohb.params.dto.voucher;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class VoucherDTO implements Serializable {
    /**
     * 兑换券ID
     */
    private Long voucherId;

    /**
     * 使用用户名
     */
    private String username;

    /**
     * 兑换码
     */
    private String voucherCode;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 过期时间
     */
    private String expireTime;

    /**
     * 状态  0：未使用   1：已使用
     */
    private Integer status;

    private static final long serialVersionUID = 1L;


}
