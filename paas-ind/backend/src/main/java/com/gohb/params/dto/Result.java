package com.gohb.params.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 3651301191889705532L;

    /**
     * 调用成功标志
     */
    private boolean success;

    /**
     * 错误码， 在 success为 false的时候使用
     */
    private Integer statusCode;

    /**
     * 错误描述信息
     */
    private String message;

    /**
     * 实际数据
     */
    private T data;

    private Object errorData;

    /**
     * 分页操作时，数据量
     */
    private Long count;

}
