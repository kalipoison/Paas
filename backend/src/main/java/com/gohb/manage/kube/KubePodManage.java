package com.gohb.manage.kube;

import com.gohb.params.dto.kube.KubePodDTO;
import com.gohb.params.dto.kube.KubePodDetailDTO;
import com.gohb.params.request.kube.CreatePodRequest;

import java.util.List;

public interface KubePodManage {

    /**
     * 列出所有pod
     * @param namespace
     * @return
     */
    List<KubePodDTO> listPod(String namespace);

    /**
     * 删除指定namespace下的pod
     * @param namespace
     * @param podName
     * @return
     */
    KubePodDTO deletePod(String namespace, String podName);

    /**
     * 查看pod详情
     * @param namespace
     * @param podName
     * @return
     */
    KubePodDetailDTO podDetail(String namespace, String podName);

    /**
     * 返回 pod 信息， yaml格式
     * @param namespace
     * @param podName
     * @return
     */
    String podDetailYaml(String namespace, String podName);

    /**
     * 创建一个Pod
     * @param createPodRequest
     */
    KubePodDTO createPod(CreatePodRequest createPodRequest);
}
