package com.gohb.manage.kube;

import com.gohb.bo.kube.KubePodBO;
import com.gohb.bo.kube.KubePodDetailBO;
import com.gohb.dto.kube.KubePodDTO;
import com.gohb.dto.kube.KubePodDetailDTO;
import io.kubernetes.client.openapi.models.V1Pod;

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
}
