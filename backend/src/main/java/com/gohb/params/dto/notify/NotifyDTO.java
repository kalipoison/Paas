package com.gohb.params.dto.notify;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class NotifyDTO implements Serializable {
    /**
     * 通知id
     */
    private Long notifyId;

    /**
     * 通知时间
     */
    private String createTime;

    /**
     * 通知内容
     */
    private String notifyContent;

    private static final long serialVersionUID = 1L;

}
