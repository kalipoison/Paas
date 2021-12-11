package com.gohb.manage.impl;


import com.gohb.bo.KubeNamespaceBO;
import com.gohb.convert.KubeToBoUtils;
import com.gohb.manage.KubeNamespaceManage;
import com.gohb.service.KubeNamespaceService;
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

    public List<KubeNamespaceBO> listNamespace() {
        List<V1Namespace> v1NamespaceList = kubeNamespaceService.listNamespace();
        List<KubeNamespaceBO> kubeNamespaceBOS = new ArrayList<>();
        for (V1Namespace v1Namespace : v1NamespaceList) {
            kubeNamespaceBOS.add(KubeToBoUtils.V1NamespaceToKubeNamespaceBO(v1Namespace));
        }
        return kubeNamespaceBOS;
    }

    public KubeNamespaceBO namespaceDetail(String namespace) {
        V1Namespace v1Namespace = kubeNamespaceService.namespaceDetail(namespace);
        KubeNamespaceBO kubeNamespaceBO = KubeToBoUtils.V1NamespaceToKubeNamespaceBO(v1Namespace);
        return kubeNamespaceBO;
    }

    public Boolean isExistNamespace(String namespace) {
        V1Namespace v1Namespace = kubeNamespaceService.namespaceDetail(namespace);
        return v1Namespace == null ? false : true;
    }

}
