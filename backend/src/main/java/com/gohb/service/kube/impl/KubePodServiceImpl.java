package com.gohb.service.kube.impl;

import com.gohb.params.bo.kube.KubePodBO;
import com.gohb.params.bo.kube.KubePodContainerBO;
import com.gohb.params.bo.kube.KubePodDetailBO;
import com.gohb.convert.KubeToBoUtils;
import com.gohb.params.exception.KubeException;
import com.gohb.params.request.CreatePodRequest;
import com.gohb.service.kube.KubePodService;
import io.kubernetes.client.custom.Quantity;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
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
            log.info(e.getMessage());
            throw new KubeException(e.getMessage());
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
                Map<String, Quantity> limits = v1Container.getResources().getLimits();
                Map<String, Quantity> requests = v1Container.getResources().getRequests();
                String limitsCPU = limits != null && limits.get("cpu") != null ? limits.get("cpu").toString() : "无上限";
                String requestsCPU = requests != null && requests.get("cpu") != null ? requests.get("cpu").toString() : "无最低要求";
                String limitsMemory = limits != null && limits.get("memory") != null ? limits.get("memory").toString() : "无上限";
                String requestsMemory = requests != null && requests.get("memory") != null ? requests.get("memory").toString() : "无最低要求";
                kubePodContainerBO.setLimitsCPU(limitsCPU);
                kubePodContainerBO.setLimitsMemory(limitsMemory);
                kubePodContainerBO.setRequestsCPU(requestsCPU);
                kubePodContainerBO.setRequestsMemory(requestsMemory);
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
    public KubePodBO createPod(CreatePodRequest createPodRequest) {
        V1Pod body = new V1Pod()
            .apiVersion(createPodRequest.getApiVersion())
            .metadata(getV1ObjectMeta(createPodRequest))
            .spec(new V1PodSpec()
                .containers(Arrays.asList(new V1Container()
                    .name(createPodRequest.getSpecConatinersName())
                    .image(createPodRequest.getSpecContainersImage())
                    .ports(Arrays.asList(new V1ContainerPort()
                        .containerPort(createPodRequest.getContainerPort()))))));
        KubePodBO kubePodBO = null;
        try {
            String namespace = (createPodRequest.getNamespace() == null || "".equals(createPodRequest.getNamespace())) ? "default" : createPodRequest.getNamespace();
            V1Pod pod = coreV1Api.createNamespacedPod(namespace, body, null, null, null);
            kubePodBO = KubeToBoUtils.v1PodTOKubePodBO(pod);
        } catch (ApiException e) {
            log.info(e.getMessage());
            throw new KubeException(e.getMessage());
        }
        return kubePodBO;
    }

    /**
     * 根据 label 内 app 是否为空，
     * 生成 V1ObjectMeta
     * @param createPodRequest
     * @return
     */
    private V1ObjectMeta getV1ObjectMeta(CreatePodRequest createPodRequest) {
        V1ObjectMeta v1ObjectMeta = new V1ObjectMeta();
        v1ObjectMeta.setName(createPodRequest.getMetadataName());
//        v1ObjectMeta.setNamespace(createPodRequest.getNamespace());
        if (createPodRequest.getMetadataLabelsApp() != null && !"".equals(createPodRequest.getMetadataLabelsApp())) {
            Map<String, String> labels = new HashMap<>();
            labels.put("app", createPodRequest.getMetadataLabelsApp());
            v1ObjectMeta.setLabels(labels);
        }
        return v1ObjectMeta;
    }

}
