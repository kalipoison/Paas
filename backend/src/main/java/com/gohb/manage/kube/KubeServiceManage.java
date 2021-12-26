package com.gohb.manage.kube;

import com.gohb.params.bo.kube.KubeServiceBO;
import com.gohb.params.dto.kube.KubeServiceDTO;
import com.gohb.params.dto.kube.KubeServiceDetailDTO;
import com.gohb.params.request.CreateServiceRequest;
import com.gohb.params.request.UpdateServiceRequest;
import io.kubernetes.client.custom.IntOrString;
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
