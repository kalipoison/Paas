package com.gohb.manage;

import com.gohb.bo.KubeNamespaceBO;
import com.gohb.service.KubeNamespaceService;
import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.openapi.models.V1Status;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import java.util.List;

public interface KubeNamespaceManage {

    V1Namespace createNamespace(String namespace);

    V1Status deleteNamespace(String namespace);

    List<KubeNamespaceBO> listNamespace();

    KubeNamespaceBO namespaceDetail(String namespace);

    Boolean isExistNamespace(String namespace);

}
