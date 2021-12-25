package com.gohb.manage.kube.impl;


import com.gohb.params.bo.kube.KubeNamespaceBO;
import com.gohb.convert.BoToDtoUtils;
import com.gohb.convert.KubeToBoUtils;
import com.gohb.params.dto.kube.KubeNamespaceDTO;
import com.gohb.manage.kube.KubeNamespaceManage;
import com.gohb.service.kube.KubeNamespaceService;
import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.openapi.models.V1Status;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class KubeNamespaceManageImpl implements KubeNamespaceManage {

    @Autowired
    private KubeNamespaceService kubeNamespaceService;

    public V1Namespace createNamespace(String namespace) {
        V1Namespace v1Namespace = kubeNamespaceService.createNamespace(namespace);
        return v1Namespace;
    }

    public V1Status deleteNamespace(String namespace) {
        V1Status v1Status = kubeNamespaceService.deleteNamespace(namespace);
        return v1Status;
    }

    public List<KubeNamespaceDTO> listNamespace() {
        List<KubeNamespaceBO> kubeNamespaceBOS = kubeNamespaceService.listNamespace();
        List<KubeNamespaceDTO> kubeNamespaceDTOS = new ArrayList<>();
        for (KubeNamespaceBO kubeNamespaceBO : kubeNamespaceBOS) {
            kubeNamespaceDTOS.add(BoToDtoUtils.kubeNamespaceBOTOkubeNamespaceDTO(kubeNamespaceBO));
        }
        return kubeNamespaceDTOS;
    }

    public String namespaceDetail(String namespace) {
        return kubeNamespaceService.namespaceDetail(namespace);
    }


}
