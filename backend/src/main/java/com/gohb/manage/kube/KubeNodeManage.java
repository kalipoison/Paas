package com.gohb.manage.kube;

import com.gohb.params.dto.kube.KubeNodeDTO;
import io.kubernetes.client.openapi.models.V1Node;

import java.util.List;

public interface KubeNodeManage {

    List<KubeNodeDTO> listNode();

    V1Node nodeDetail(String nodeName);

    Boolean isExistNode(String nodeName);

}
