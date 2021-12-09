package com.gohb.manage;

import io.kubernetes.client.custom.IntOrString;
import io.kubernetes.client.openapi.models.V1Service;
import io.kubernetes.client.openapi.models.V1Status;

import java.util.List;

public interface KubeServiceManage {

    V1Service createService(String serviceName, String namespace, String type, Integer port, Integer nodePort, IntOrString targetPort, String protocol);

    V1Status deleteService(String serviceName, String namespace);

    List<V1Service> listService(String namespace);

    V1Service serviceDetail(String serviceName, String namespace);

    Boolean isExistService(String serviceName, String namespace);
}
