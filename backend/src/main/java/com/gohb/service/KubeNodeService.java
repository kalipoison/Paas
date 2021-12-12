package com.gohb.service;

import io.kubernetes.client.openapi.models.V1Node;

import java.util.List;

/**
 * k8s node操作
 */
public interface KubeNodeService {

    List<V1Node> listNode();

    V1Node nodeDetail(String nodeName);
}
