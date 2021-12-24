package com.gohb.service.kube;

import com.gohb.bo.kube.KubeNodeBO;
import io.kubernetes.client.openapi.models.V1Node;

import java.util.List;

/**
 * k8s node操作
 */
public interface KubeNodeService {

    List<KubeNodeBO> listNode();

    V1Node nodeDetail(String nodeName);
}
