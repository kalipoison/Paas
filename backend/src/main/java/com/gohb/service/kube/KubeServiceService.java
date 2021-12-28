package com.gohb.service.kube;

import com.gohb.params.bo.kube.KubeServiceBO;
import com.gohb.params.bo.kube.KubeServiceDetailBO;
import com.gohb.params.request.kube.CreateServiceRequest;
import com.gohb.params.request.kube.UpdateServiceRequest;
import io.kubernetes.client.openapi.models.V1Service;
import io.kubernetes.client.openapi.models.V1Status;

import java.util.List;

/**
 * k8s service操作
 */
public interface KubeServiceService {

    /**
     * 创建service
     * @param createServiceRequest
     * @return
     */
    V1Service createService(CreateServiceRequest createServiceRequest);

    /**
     * 删除service
     * @param serviceName
     * @param namespace
     * @return
     */
    V1Status deleteService(String serviceName, String namespace);


    /**
     * 修改service
     * @param updateServiceRequest
     * @return
     */
    KubeServiceBO updateService(UpdateServiceRequest updateServiceRequest);

    /**
     * 列出所有service
     * @param namespace
     * @return
     */
    List<KubeServiceBO> listNamespaceService(String namespace);

    /**
     * service 详情, yaml 格式
     * @param serviceName
     * @param namespace
     * @return
     */
    String serviceDetailYaml(String serviceName, String namespace);

    /**
     * service 详情
     * @param serviceName
     * @param namespace
     * @return
     */
    KubeServiceDetailBO serviceDetail(String serviceName, String namespace);
}
