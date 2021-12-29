package com.gohb.params.request.kube;

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
public class UpdateDeploymentRequest {


    /**
     * 定义k8s api的版本
     */
    private String apiVersion;

    /**
     * k8s namespace
     */
    private String namespace;

    /**
     * 属性，名称
     */
    private String metadataName;

    /**
     * 标签选择器
     */
    private String matchLabelsApp;

    /**
     * 副本数
     */
    private String replicas;

    /**
     * 容器名
     */
    private String templateSpecConatinersName;

    /**
     * 容器镜像
     */
    private String templateSpecContainersImage;

    /**
     * 容器开放端口
     */
    private String templateContainerPort;

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
