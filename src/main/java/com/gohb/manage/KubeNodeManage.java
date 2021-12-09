package com.gohb.manage;

import io.kubernetes.client.openapi.models.V1Node;

import java.util.List;

public interface KubeNodeManage {

    List<V1Node> listNode();

    V1Node nodeDetail(String nodeName);

    Boolean isExistNode(String nodeName);

}
