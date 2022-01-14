package com.gohb.params.dto.prod;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdPropValueDTO implements Serializable {
    /**
     * 属性值ID
     */
    private Long valueId;

    /**
     * 属性值名称
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


}
