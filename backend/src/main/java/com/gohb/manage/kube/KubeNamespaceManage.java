package com.gohb.manage.kube;

import com.gohb.bo.kube.KubeNamespaceBO;
import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.openapi.models.V1Status;

import java.util.List;

public interface KubeNamespaceManage {

    V1Namespace createNamespace(String namespace);

    V1Status deleteNamespace(String namespace);

    List<KubeNamespaceBO> listNamespace();

    KubeNamespaceBO namespaceDetail(String namespace);

    Boolean isExistNamespace(String namespace);

}
