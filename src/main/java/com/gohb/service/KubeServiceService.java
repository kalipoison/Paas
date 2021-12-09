package com.gohb.service;

import io.kubernetes.client.custom.IntOrString;
import io.kubernetes.client.openapi.models.V1Service;
import io.kubernetes.client.openapi.models.V1Status;

import java.util.List;

/**
 * k8s service操作
 */
public interface KubeServiceService {

    /**
     * 创建service
     * @param serviceName
     * @param namespace
     * @param type
     * @param port
     * @param nodePort
     * @param targetPort
     * @param protocol
     * @return
     */
    V1Service createService(String serviceName, String namespace, String type, Integer port, Integer nodePort, IntOrString targetPort, String protocol);

    /**
     * 删除service
     * @param serviceName
     * @param namespace
     * @return
     */
    V1Status deleteService(String serviceName, String namespace);

    /**
     * 列出所有service
     * @param namespace
     * @return
     */
    List<V1Service> listService(String namespace);

    /**
     * service详情
     * @param serviceName
     * @param namespace
     * @return
     */
    V1Service serviceDetail(String serviceName, String namespace);
}
