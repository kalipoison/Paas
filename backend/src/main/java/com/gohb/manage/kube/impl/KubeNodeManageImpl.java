package com.gohb.manage.kube.impl;

import com.gohb.convert.KubeDateTimeUtils;
import com.gohb.dto.kube.KubeNodeDTO;
import com.gohb.manage.kube.KubeNodeManage;
import com.gohb.service.kube.KubeNodeService;
import io.kubernetes.client.openapi.models.V1Node;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean
public class KubeNodeManageImpl implements KubeNodeManage {

    @Autowired
    private KubeNodeService kubeNodeService;

    @Override
    public List<KubeNodeDTO> listNode() {
        List<V1Node> v1Nodes = kubeNodeService.listNode();
        List<KubeNodeDTO> nodeDTOS = new ArrayList<>();
        for (V1Node node : v1Nodes) {
            KubeNodeDTO nodeDTO = new KubeNodeDTO();
            nodeDTO.setNodeName(node.getMetadata().getName());
            nodeDTO.setCreateTime(KubeDateTimeUtils.kubeDateTimeToString(node.getMetadata().getCreationTimestamp()));
            nodeDTO.setKernelVersion(node.getStatus().getNodeInfo().getKernelVersion());
            nodeDTO.setKubeletVersion(node.getStatus().getNodeInfo().getKubeletVersion());
            nodeDTO.setKubeProxyVersion(node.getStatus().getNodeInfo().getKubeProxyVersion());
            nodeDTOS.add(nodeDTO);
        }
        return nodeDTOS;
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
