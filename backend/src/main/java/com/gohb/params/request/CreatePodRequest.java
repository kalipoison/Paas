package com.gohb.params.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class CreatePodRequest {

    /**
     * 定义k8s api的版本
     */
    private String apiVersion;

    /**
     * 命名空间
     */
    private String namespace;

    /**
     * 属性，名字
     */
    private String metadataName;

    /**
     * 属性，标签叫app ： xx
     */
    private String metadataLabelsApp;

    /**
     * 容器名
     */
    private String specConatinersName;

    /**
     * 容器镜像
     */
    private String specContainersImage;

    /**
     * 容器开放端口
     */
    private Integer containerPort;

    /**
     * CPU资源上限
     */
    private String limitCPU;

    /**
     * 最少需要的CPU资源
     */
    private String requestCPU;

    /**
     * 内存资源上限
     */
    private String limitMemory;

    /**
     * 最少需要的内存资源
     */
    private String requestMemory;

}
