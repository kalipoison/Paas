package com.gohb.manage.kube;

import com.gohb.bo.kube.KubeServiceBO;
import io.kubernetes.client.custom.IntOrString;
import io.kubernetes.client.openapi.models.V1Status;

import java.util.List;

public interface KubeServiceManage {

    KubeServiceBO createService(String serviceName, String namespace, String type, Integer port, Integer nodePort, IntOrString targetPort, String protocol);

    V1Status deleteService(String serviceName, String namespace);

    List<KubeServiceBO> listService(String namespace);

    KubeServiceBO serviceDetail(String serviceName, String namespace);

    Boolean isExistService(String serviceName, String namespace);
}
