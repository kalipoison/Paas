package com.gohb.manage.kube.impl;

import com.gohb.manage.kube.KubeNodeManage;
import com.gohb.service.kube.KubeNodeService;
import io.kubernetes.client.openapi.models.V1Node;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import java.util.List;

@ManagedBean
public class KubeNodeManageImpl implements KubeNodeManage {

    @Autowired
    private KubeNodeService kubeNodeService;

    @Override
    public List<V1Node> listNode() {
        List<V1Node> v1Nodes = kubeNodeService.listNode();
        return v1Nodes;
    }

    @Override
    public V1Node nodeDetail(String nodeName) {
        V1Node v1Node = kubeNodeService.nodeDetail(nodeName);
        return v1Node;
    }

    @Override
    public Boolean isExistNode(String nodeName) {
        V1Node v1Node = kubeNodeService.nodeDetail(nodeName);
        return v1Node == null ? false : true;
    }
}
