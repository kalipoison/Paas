package com.gohb.manage.impl;


import com.gohb.manage.KubeNamespaceManage;
import com.gohb.service.KubeNamespaceService;
import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.openapi.models.V1Status;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
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

    public List<V1Namespace> listNamespace() {
        List<V1Namespace> v1NamespaceList = kubeNamespaceService.listNamespace();
        return v1NamespaceList;
    }

    public V1Namespace namespaceDetail(String namespace) {
        V1Namespace v1Namespace = kubeNamespaceService.namespaceDetail(namespace);
        return v1Namespace;
    }

    public Boolean isExistNamespace(String namespace) {
        V1Namespace v1Namespace = kubeNamespaceService.namespaceDetail(namespace);
        return v1Namespace == null ? false : true;
    }

}
