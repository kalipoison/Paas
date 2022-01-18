package com.gohb.params.dto.client;

import lombok.Data;
import java.util.Date;


@Data
public class MyUserDTO {
    /**
     *
     */
    private Long userId;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态  0：禁用   1：正常
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否会员
     */
    private Integer member;

    private static final long serialVersionUID = 1L;

}
