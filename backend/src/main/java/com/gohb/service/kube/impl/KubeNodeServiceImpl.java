package com.gohb.service.kube.impl;

import com.gohb.params.bo.kube.KubeNodeBO;
import com.gohb.convert.KubeToBoUtils;
import com.gohb.params.exception.KubeException;
import com.gohb.service.kube.KubeNodeService;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Node;
import io.kubernetes.client.openapi.models.V1NodeList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class KubeNodeServiceImpl implements KubeNodeService {

    @Autowired
    private CoreV1Api coreV1Api;


    @Override
    public List<KubeNodeBO> listNode() {
        List<KubeNodeBO> kubeNodeBOS = new ArrayList<>();
        try {
            V1NodeList v1NodeList = coreV1Api.listNode(null, null, null, null, null, null, null, null, null, null);
            List<V1Node> v1NodeListItems = v1NodeList.getItems();
            for (V1Node v1Node : v1NodeListItems) {
                KubeNodeBO kubeNodeBO = KubeToBoUtils.v1NodeToKubeNodeBO(v1Node);
                kubeNodeBOS.add(kubeNodeBO);
            }
        } catch (ApiException e) {
            log.info(e.getMessage());
            throw new KubeException(e.getMessage());
        }
        return kubeNodeBOS;
    }

    @Override
    public V1Node nodeDetail(String nodeName) {
        V1Node v1Node = null;
        try {
            v1Node = coreV1Api.readNode(nodeName, null, null, null);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return v1Node;
    }
}
