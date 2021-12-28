package com.gohb.manage.kube;

import com.gohb.params.dto.kube.KubeServiceDTO;
import com.gohb.params.dto.kube.KubeServiceDetailDTO;
import com.gohb.params.request.kube.CreateServiceRequest;
import com.gohb.params.request.kube.UpdateServiceRequest;
import io.kubernetes.client.openapi.models.V1Status;

import java.util.List;

public interface KubeServiceManage {

    KubeServiceDTO createService(CreateServiceRequest createServiceRequest);

    V1Status deleteService(String serviceName, String namespace);

    KubeServiceDTO updateService(UpdateServiceRequest updateServiceRequest);

    List<KubeServiceDTO> listService(String namespace);

    String serviceDetailYaml(String serviceName, String namespace);

    KubeServiceDetailDTO serviceDetail(String serviceName, String namespace);
}
