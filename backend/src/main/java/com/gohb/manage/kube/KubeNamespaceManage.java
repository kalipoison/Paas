package com.gohb.manage.kube;

import com.gohb.params.bo.kube.KubeNamespaceBO;
import com.gohb.params.dto.kube.KubeNamespaceDTO;
import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.openapi.models.V1Status;

import java.util.List;

public interface KubeNamespaceManage {

    V1Namespace createNamespace(String namespace);

    V1Status deleteNamespace(String namespace);

    List<KubeNamespaceDTO> listNamespace();

    String namespaceDetail(String namespace);

}
