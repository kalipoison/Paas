package com.gohb.service.kube.impl;

import com.gohb.bo.kube.KubePodBO;
import com.gohb.bo.kube.KubePodContainerBO;
import com.gohb.bo.kube.KubePodDetailBO;
import com.gohb.convert.KubeToBoUtils;
import com.gohb.service.kube.KubePodService;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KubePodServiceImpl implements KubePodService {

    @Autowired
    private CoreV1Api coreV1Api;

    @Override
    public List<KubePodBO> listPod(String namespace) {
        List<KubePodBO> kubePodBOS = new ArrayList<>();
        try {
            V1PodList v1PodList = null;
            if (namespace == null || "".equals(namespace)) {
                v1PodList = coreV1Api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null, null);
            } else {
                v1PodList = coreV1Api.listNamespacedPod(namespace, null, null, null, null, null, null, null, null, null, null);
            }
            List<V1Pod> v1Pods = v1PodList.getItems();
            for (V1Pod v1Pod : v1Pods) {
                kubePodBOS.add(KubeToBoUtils.v1PodTOKubePodBO(v1Pod));
            }
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return kubePodBOS;
    }

    @Override
    public KubePodBO deletePod(String namespace, String podName) {
        KubePodBO kubePodBO = null;
        try {
            V1Pod v1Pod = coreV1Api.deleteNamespacedPod(podName, namespace, null, null, null, null, null, null);
            kubePodBO = KubeToBoUtils.v1PodTOKubePodBO(v1Pod);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return kubePodBO;
    }

    @Override
    public KubePodDetailBO podDetail(String namespace, String podName) {
        KubePodDetailBO kubePodDetailBO = null;
        try {
            V1Pod v1Pod = coreV1Api.readNamespacedPod(podName, namespace, null, null, null);
            kubePodDetailBO = new KubePodDetailBO();
            kubePodDetailBO.setApiVersion(v1Pod.getApiVersion());
            kubePodDetailBO.setKind(v1Pod.getKind());
            kubePodDetailBO.setMetadataName(v1Pod.getMetadata().getName());
            kubePodDetailBO.setMetadataLabelsApp(v1Pod.getMetadata().getLabels().get("app"));
            List<KubePodContainerBO> kubePodContainerBOList = new ArrayList<>();
            for (V1Container v1Container : v1Pod.getSpec().getContainers()) {
                KubePodContainerBO kubePodContainerBO = new KubePodContainerBO();
                kubePodContainerBO.setName(v1Container.getName());
                kubePodContainerBO.setImage(v1Container.getImage());
                List<Integer> ports = v1Container.getPorts().stream().map(V1ContainerPort::getContainerPort).collect(Collectors.toList());
                kubePodContainerBO.setPorts(ports);
                kubePodContainerBOList.add(kubePodContainerBO);
            }
            kubePodDetailBO.setKubePodContainerBOList(kubePodContainerBOList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kubePodDetailBO;
    }

    @Override
    public String podDetailYaml(String namespace, String podName) {
        try {
            V1Pod v1Pod = coreV1Api.readNamespacedPod(podName, namespace, null, null, null);
            return v1Pod.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public V1Pod createPod(String podName) {
        return null;
    }
}
