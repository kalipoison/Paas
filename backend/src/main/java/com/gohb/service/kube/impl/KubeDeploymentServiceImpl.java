package com.gohb.service.kube.impl;

import com.gohb.convert.KubeToBoUtils;
import com.gohb.params.bo.kube.KubeDeploymentBO;
import com.gohb.params.bo.kube.KubeDeploymentDetailBO;
import com.gohb.params.request.kube.CreateDeploymentRequest;
import com.gohb.params.request.kube.UpdateDeploymentRequest;
import com.gohb.service.kube.KubeDeploymentService;
import io.kubernetes.client.custom.Quantity;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KubeDeploymentServiceImpl implements KubeDeploymentService {

    @Autowired
    private AppsV1Api appsV1Api;

    @Override
    public List<KubeDeploymentBO> listDeployment(String namespace) {
        List<KubeDeploymentBO> kubeDeploymentBOS = new ArrayList<>();
        try {
            V1DeploymentList v1DeploymentList = appsV1Api.listNamespacedDeployment(namespace, null, null, null, null, null, null, null, null, null, null);
            List<V1Deployment> v1Deployments = v1DeploymentList.getItems();
            for (V1Deployment v1Deployment : v1Deployments) {
                KubeDeploymentBO kubeDeploymentBO = KubeToBoUtils.v1DeploymentToKubeDeploymentBO(v1Deployment);
                kubeDeploymentBOS.add(kubeDeploymentBO);
            }
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return kubeDeploymentBOS;
    }

    @Override
    public KubeDeploymentDetailBO detailDeployment(String deploymentName, String namespace) {
        V1Deployment v1Deployment = null;
        KubeDeploymentDetailBO kubeDeploymentDetailBO = null;
        try {
            v1Deployment = appsV1Api.readNamespacedDeployment(deploymentName, namespace, null, null, null);
            kubeDeploymentDetailBO = KubeToBoUtils.v1DeploymentToKubeDeploymentDetailBO(v1Deployment);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return kubeDeploymentDetailBO;
    }

    @Override
    public String deploymentDetailYaml(String namespace, String deploymentName) {
        String deploymentDetailYaml = "";
        try {
            V1Deployment v1Deployment = appsV1Api.readNamespacedDeployment(deploymentName, namespace, null, null, null);
            deploymentDetailYaml = v1Deployment.toString();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return deploymentDetailYaml;
    }

    @Override
    public V1Status deleteDeployment(String deploymentName, String namespace) {
        V1Status v1Status = null;
        try {
            v1Status = appsV1Api.deleteNamespacedDeployment(deploymentName, namespace, null, null, null, null, null, null);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return v1Status;
    }

    @Override
    public KubeDeploymentBO updateDeployment(UpdateDeploymentRequest updateDeploymentRequest) {
        Map<String, String> labels = new HashMap<>();
        labels.put("app", updateDeploymentRequest.getMatchLabelsApp());
        V1Deployment body = new V1Deployment()
                .apiVersion(updateDeploymentRequest.getApiVersion())
                .metadata(new V1ObjectMeta()
                        .name(updateDeploymentRequest.getMetadataName()))
                .spec(new V1DeploymentSpec()
                        .selector(new V1LabelSelector().matchLabels(labels))
                        .replicas(Integer.valueOf(updateDeploymentRequest.getReplicas()))
                        .template(new V1PodTemplateSpec()
                                .metadata(new V1ObjectMeta()
                                        .labels(labels))
                                .spec(new V1PodSpec()
                                        .containers(Arrays.asList(new V1Container()
                                                .name(updateDeploymentRequest.getTemplateSpecConatinersName())
                                                .image(updateDeploymentRequest.getTemplateSpecContainersImage())
                                                .ports(Arrays.asList(new V1ContainerPort()
                                                        .containerPort(Integer.valueOf(updateDeploymentRequest.getTemplateContainerPort()))))
                                                .resources(getV1ResourceRequirements(updateDeploymentRequest)))))));
        KubeDeploymentBO kubeDeploymentBO = null;
        try {
            V1Deployment v1Deployment = appsV1Api.replaceNamespacedDeployment(updateDeploymentRequest.getMetadataName(), updateDeploymentRequest.getNamespace(), body, null, null, null);
            kubeDeploymentBO = KubeToBoUtils.v1DeploymentToKubeDeploymentBO(v1Deployment);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return kubeDeploymentBO;
    }

    /**
     * 从请求参数中提取 资源信息
     * @param updateDeploymentRequest
     * @return
     */
    private V1ResourceRequirements getV1ResourceRequirements(UpdateDeploymentRequest updateDeploymentRequest) {
        V1ResourceRequirements resource = new V1ResourceRequirements();
        Map<String, Quantity> limits = new HashMap<>();
        if (updateDeploymentRequest.getLimitCPU() != null && !"".equals(updateDeploymentRequest.getLimitCPU())) {
            limits.put("cpu", Quantity.fromString(updateDeploymentRequest.getLimitCPU()));
        }
        if (updateDeploymentRequest.getLimitMemory() != null && !"".equals(updateDeploymentRequest.getLimitMemory())) {
            limits.put("memory", Quantity.fromString(updateDeploymentRequest.getLimitMemory()));
        }
        resource.setLimits(limits);
        Map<String, Quantity> requests = new HashMap<>();
        if (updateDeploymentRequest.getRequestCPU() != null && !"".equals(updateDeploymentRequest.getRequestCPU())) {
            requests.put("cpu", Quantity.fromString(updateDeploymentRequest.getLimitCPU()));
        }
        if (updateDeploymentRequest.getRequestMemory() != null && !"".equals(updateDeploymentRequest.getRequestMemory())) {
            requests.put("memory", Quantity.fromString(updateDeploymentRequest.getRequestMemory()));
        }
        resource.setRequests(requests);
        return resource;
    }

    @Override
    public KubeDeploymentBO createDeployment(CreateDeploymentRequest createDeploymentRequest) {
        Map<String, String> labels = new HashMap<>();
        labels.put("app", createDeploymentRequest.getMatchLabelsApp());
        V1Deployment body = new V1Deployment()
            .apiVersion(createDeploymentRequest.getApiVersion())
            .metadata(new V1ObjectMeta()
                .name(createDeploymentRequest.getMetadataName()))
            .spec(new V1DeploymentSpec()
                .selector(new V1LabelSelector().matchLabels(labels))
                .replicas(Integer.valueOf(createDeploymentRequest.getReplicas()))
                .template(new V1PodTemplateSpec()
                    .metadata(new V1ObjectMeta()
                        .labels(labels))
                    .spec(new V1PodSpec()
                        .containers(Arrays.asList(new V1Container()
                            .name(createDeploymentRequest.getTemplateSpecConatinersName())
                            .image(createDeploymentRequest.getTemplateSpecContainersImage())
                            .ports(Arrays.asList(new V1ContainerPort()
                                .containerPort(Integer.valueOf(createDeploymentRequest.getTemplateContainerPort()))))
                            .resources(getV1ResourceRequirements(createDeploymentRequest)))))));

        KubeDeploymentBO kubeDeploymentBO = null;
        try {
            String namespace = "default";
            if (createDeploymentRequest.getNamespace() != null && !"".equals(createDeploymentRequest.getNamespace())) {
                namespace = createDeploymentRequest.getNamespace();
            }
            V1Deployment v1Deployment = appsV1Api.createNamespacedDeployment(namespace, body, null, null, null);
            kubeDeploymentBO = KubeToBoUtils.v1DeploymentToKubeDeploymentBO(v1Deployment);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return kubeDeploymentBO;
    }

    /**
     * 从请求参数中提取 资源信息
     * @param createDeploymentRequest
     * @return
     */
    private V1ResourceRequirements getV1ResourceRequirements(CreateDeploymentRequest createDeploymentRequest) {
        V1ResourceRequirements resource = new V1ResourceRequirements();
        Map<String, Quantity> limits = new HashMap<>();
        if (createDeploymentRequest.getLimitCPU() != null && !"".equals(createDeploymentRequest.getLimitCPU())) {
            limits.put("cpu", Quantity.fromString(createDeploymentRequest.getLimitCPU()));
        }
        if (createDeploymentRequest.getLimitMemory() != null && !"".equals(createDeploymentRequest.getLimitMemory())) {
            limits.put("memory", Quantity.fromString(createDeploymentRequest.getLimitMemory()));
        }
        resource.setLimits(limits);
        Map<String, Quantity> requests = new HashMap<>();
        if (createDeploymentRequest.getRequestCPU() != null && !"".equals(createDeploymentRequest.getRequestCPU())) {
            requests.put("cpu", Quantity.fromString(createDeploymentRequest.getLimitCPU()));
        }
        if (createDeploymentRequest.getRequestMemory() != null && !"".equals(createDeploymentRequest.getRequestMemory())) {
            requests.put("memory", Quantity.fromString(createDeploymentRequest.getRequestMemory()));
        }
        resource.setRequests(requests);
        return resource;
    }
}
