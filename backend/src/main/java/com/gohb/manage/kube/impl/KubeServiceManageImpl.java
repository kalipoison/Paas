package com.gohb.manage.kube.impl;

import com.gohb.convert.BoToDtoUtils;
import com.gohb.params.bo.kube.KubeNamespaceBO;
import com.gohb.params.bo.kube.KubeServiceBO;
import com.gohb.convert.KubeToBoUtils;
import com.gohb.manage.kube.KubeServiceManage;
import com.gohb.params.bo.kube.KubeServiceDetailBO;
import com.gohb.params.dto.kube.KubeServiceDTO;
import com.gohb.params.dto.kube.KubeServiceDetailDTO;
import com.gohb.params.request.CreateServiceRequest;
import com.gohb.params.request.UpdateServiceRequest;
import com.gohb.service.kube.KubeNamespaceService;
import com.gohb.service.kube.KubeServiceService;
import io.kubernetes.client.custom.IntOrString;
import io.kubernetes.client.openapi.models.V1Service;
import io.kubernetes.client.openapi.models.V1Status;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean
public class KubeServiceManageImpl implements KubeServiceManage {

    @Autowired
    private KubeServiceService kubeServiceService;

    @Autowired
    private KubeNamespaceService kubeNamespaceService;

    @Override
    public KubeServiceDTO createService(CreateServiceRequest createServiceRequest) {
        V1Service v1Service = kubeServiceService.createService(createServiceRequest);
        KubeServiceBO kubeServiceBO = KubeToBoUtils.v1ServiceToKubeServiceBO(v1Service);
        KubeServiceDTO kubeServiceDTO = BoToDtoUtils.kubeServiceBOTOKubeServiceDTO(kubeServiceBO);
        return kubeServiceDTO;
    }

    @Override
    public V1Status deleteService(String serviceName, String namespace) {
        V1Status v1Status = kubeServiceService.deleteService(serviceName, namespace);
        return v1Status;
    }

    @Override
    public KubeServiceDTO updateService(UpdateServiceRequest updateServiceRequest) {
        KubeServiceBO kubeServiceBO = kubeServiceService.updateService(updateServiceRequest);
        KubeServiceDTO kubeServiceDTO = BoToDtoUtils.kubeServiceBOTOKubeServiceDTO(kubeServiceBO);
        return kubeServiceDTO;
    }

    @Override
    public List<KubeServiceDTO> listService(String namespace) {
        List<KubeServiceDTO> kubeServiceDTOList = new ArrayList<>();
        if (namespace == null || "".equals(namespace)) {
            List<KubeNamespaceBO> kubeNamespaceBOS = kubeNamespaceService.listNamespace();
            List<String> namespaceList = kubeNamespaceBOS.stream().map(KubeNamespaceBO::getName).collect(Collectors.toList());
            for (String name : namespaceList) {
                List<KubeServiceDTO> kubeServiceDTOS = listServiceByNamespace(name);
                kubeServiceDTOList.addAll(kubeServiceDTOS);
            }
        } else {
            List<KubeServiceDTO> kubeServiceDTOS = listServiceByNamespace(namespace);
            kubeServiceDTOList.addAll(kubeServiceDTOS);
        }
        return kubeServiceDTOList;
    }

    private List<KubeServiceDTO> listServiceByNamespace(String namespace) {
        List<KubeServiceBO> kubeServiceBOS = kubeServiceService.listNamespaceService(namespace);
        List<KubeServiceDTO> kubeServiceDTOS = new ArrayList<>();
        for (KubeServiceBO kubeServiceBO : kubeServiceBOS) {
            KubeServiceDTO kubeServiceDTO = BoToDtoUtils.kubeServiceBOTOKubeServiceDTO(kubeServiceBO);
            kubeServiceDTOS.add(kubeServiceDTO);
        }
        return kubeServiceDTOS;
    }

    @Override
    public String serviceDetailYaml(String serviceName, String namespace) {
        String serviceDetailYaml = kubeServiceService.serviceDetailYaml(serviceName, namespace);
        return serviceDetailYaml;
    }

    @Override
    public KubeServiceDetailDTO serviceDetail(String serviceName, String namespace) {
        KubeServiceDetailBO kubeServiceDetailBO = kubeServiceService.serviceDetail(serviceName, namespace);
        KubeServiceDetailDTO kubeServiceDetailDTO = BoToDtoUtils.kubeServiceDetailBOTOKubeServiceDetailDTO(kubeServiceDetailBO);
        return kubeServiceDetailDTO;
    }

}
