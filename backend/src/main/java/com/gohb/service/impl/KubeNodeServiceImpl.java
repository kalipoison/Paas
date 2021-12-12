package com.gohb.service.impl;

import com.gohb.service.KubeNodeService;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Node;
import io.kubernetes.client.openapi.models.V1NodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KubeNodeServiceImpl implements KubeNodeService {

    @Autowired
    private CoreV1Api coreV1Api;


    @Override
    public List<V1Node> listNode() {
        List<V1Node> v1NodeListItems = null;
        try {
            V1NodeList v1NodeList = coreV1Api.listNode(null, null, null, null, null, null, null, null, null, null);
            v1NodeListItems = v1NodeList.getItems();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return v1NodeListItems;
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
