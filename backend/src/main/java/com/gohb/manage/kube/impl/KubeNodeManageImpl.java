package com.gohb.manage.kube.impl;

import com.gohb.params.bo.kube.KubeNodeBO;
import com.gohb.convert.BoToDtoUtils;
import com.gohb.params.dto.kube.KubeNodeDTO;
import com.gohb.manage.kube.KubeNodeManage;
import com.gohb.service.kube.KubeNodeService;
import io.kubernetes.client.openapi.models.V1Node;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class KubeNodeManageImpl implements KubeNodeManage {

    @Autowired
    private KubeNodeService kubeNodeService;

    @Override
    public List<KubeNodeDTO> listNode() {
        List<KubeNodeBO> kubeNodeBOS = kubeNodeService.listNode();
        List<KubeNodeDTO> kubeNodeDTOS = new ArrayList<>();
        if (kubeNodeBOS.size() > 0 ) {
            for (KubeNodeBO kubeNodeBO : kubeNodeBOS) {
                KubeNodeDTO kubeNodeDTO = BoToDtoUtils.kubeNodeBOTOKubeNodeDTO(kubeNodeBO);
                kubeNodeDTOS.add(kubeNodeDTO);
            }
        }
        return kubeNodeDTOS;
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
