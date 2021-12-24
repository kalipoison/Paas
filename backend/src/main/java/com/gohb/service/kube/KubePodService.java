package com.gohb.service.kube;

import com.gohb.bo.kube.KubePodBO;
import com.gohb.bo.kube.KubePodDetailBO;
import io.kubernetes.client.openapi.models.V1Pod;

import java.util.List;

public interface KubePodService {

    /**
     * 列出所有pod
     * @param namespace
     * @return
     */
    List<KubePodBO> listPod(String namespace);

    /**
     * 删除指定namespace下的pod
     * @param namespace
     * @param podName
     * @return
     */
    KubePodBO deletePod(String namespace, String podName);

    /**
     * 查看pod详情
     * @param namespace
     * @param podName
     * @return
     */
    KubePodDetailBO podDetail(String namespace, String podName);

    /**
     * 返回 pod 信息， yaml格式
     * @param namespace
     * @param podName
     * @return
     */
    String podDetailYaml(String namespace, String podName);

    /**
     * 创建pod
     * @param podName
     * @return
     */
    V1Pod createPod(String podName);

}
