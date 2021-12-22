package com.gohb.service.kube;

import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.openapi.models.V1Status;

import java.util.List;

public interface KubeNamespaceService {

    /**
     * 创建namespace
     * @param namespace
     * @return
     */
    V1Namespace createNamespace(String namespace);

    /**
     * 删除namespace
     * @param namespace
     * @return
     */
    V1Status deleteNamespace(String namespace);

    /**
     * 列出所有namespace
     */
    List<V1Namespace> listNamespace();

    /**
     * namespace详情
     */
    V1Namespace namespaceDetail(String namespace);
}
