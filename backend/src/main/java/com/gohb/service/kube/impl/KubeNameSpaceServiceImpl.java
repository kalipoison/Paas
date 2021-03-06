package com.gohb.service.kube.impl;

import com.gohb.params.bo.kube.KubeNamespaceBO;
import com.gohb.convert.KubeToBoUtils;
import com.gohb.params.exception.KubeException;
import com.gohb.service.kube.KubeNamespaceService;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.openapi.models.V1NamespaceList;
import io.kubernetes.client.openapi.models.V1ObjectMeta;
import io.kubernetes.client.openapi.models.V1Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class KubeNameSpaceServiceImpl implements KubeNamespaceService {

    @Autowired
    private CoreV1Api coreV1Api;


    @Override
    public V1Namespace createNamespace(String namespace) {
        V1Namespace body = new V1Namespace().metadata(new V1ObjectMeta().name(namespace));
        V1Namespace v1Namespace = null;
        try {
            v1Namespace = coreV1Api.createNamespace(body, null, null, null);
        } catch (ApiException e) {
            if (e.getCode() == 409) {
                log.info("pod 已存在");
            } else {
                log.info(e.getCode() + ":" + e.getMessage());
            }
        }
        return v1Namespace;
    }

    @Override
    public V1Status deleteNamespace(String namespace) {
        V1Status v1Status = null;
        try {
            v1Status = coreV1Api.deleteNamespace(namespace, null, null, null, null, null, null);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return v1Status;
    }

    @Override
    public List<KubeNamespaceBO> listNamespace() {
        List<V1Namespace> v1NamespaceListItems = null;
        List<KubeNamespaceBO> kubeNamespaceBOS = new ArrayList<>();
        try {
            V1NamespaceList v1NamespaceList = coreV1Api.listNamespace(null, null, null, null, null, null, null, null, null, null);
            v1NamespaceListItems = v1NamespaceList.getItems();
            for (V1Namespace v1Namespace : v1NamespaceListItems) {
                kubeNamespaceBOS.add(KubeToBoUtils.V1NamespaceToKubeNamespaceBO(v1Namespace));
            }
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return kubeNamespaceBOS;
    }

    @Override
    public String namespaceDetail(String namespace) {
        String namespaceDetail = "";
        try {
            V1Namespace v1Namespace = coreV1Api.readNamespace(namespace, null, null, null);
            namespaceDetail = v1Namespace.toString();
        } catch (ApiException e) {
            log.info(e.getMessage());
            throw new KubeException(e.getMessage());
        }
        return namespaceDetail;
    }
}
