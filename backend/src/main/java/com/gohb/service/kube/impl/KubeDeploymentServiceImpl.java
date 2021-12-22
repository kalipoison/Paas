package com.gohb.service.kube.impl;

import com.gohb.service.kube.KubeDeploymentService;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KubeDeploymentServiceImpl implements KubeDeploymentService {

    @Autowired
    private AppsV1Api appsV1Api;

    @Override
    public List<V1Deployment> listDeployment(String namespace) {
        List<V1Deployment> v1Deployments = null;
        try {
            V1DeploymentList v1DeploymentList = appsV1Api.listNamespacedDeployment(namespace, null, null, null, null, null, null, null, null, null, null);
            v1Deployments = v1DeploymentList.getItems();
        } catch (ApiException e) {
            return v1Deployments;
        }
        return v1Deployments;
    }

    @Override
    public V1Deployment detailDeployment(String deploymentName, String namespace) {
        V1Deployment v1Deployment = null;
        try {
            v1Deployment = appsV1Api.readNamespacedDeployment(deploymentName, namespace, null, null, null);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return v1Deployment;
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
    public V1Deployment updateDeployment(String deploymentName, String namepsace, Integer replicas, String metadataLabelsApp, String image, String portName, Integer containerPort) {
        V1Deployment body = generateDeployment(deploymentName, namepsace, replicas, metadataLabelsApp, image, portName, containerPort);
        V1Deployment v1Deployment = null;
        try {
            v1Deployment = appsV1Api.replaceNamespacedDeployment(deploymentName, namepsace, body, null, null, null);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return v1Deployment;
    }

    @Override
    public V1Deployment createDeployment(String deploymentName, String namepsace, Integer replicas, String metadataLabelsApp, String image, String portName, Integer containerPort) {
        V1Deployment body = generateDeployment(deploymentName, namepsace, replicas, metadataLabelsApp, image, portName, containerPort);
        V1Deployment v1Deployment = null;
        try {
            v1Deployment = appsV1Api.createNamespacedDeployment(namepsace, body, null, null, null);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return v1Deployment;
    }

    private V1Deployment generateDeployment(String deploymentName, String namepsace, Integer replicas, String metadataLabelsApp, String image, String portName, Integer containerPort) {
        // labels
        Map<String,String> matchLabels = new HashMap<>();
        matchLabels.put("app", metadataLabelsApp);
        // ports
        List<V1ContainerPort> portList = new ArrayList<>();
        V1ContainerPort port = new V1ContainerPort();
        port.setName(portName);
        port.setContainerPort(containerPort);
        portList.add(port);
        // 使用对象封装deployment
        V1Deployment body = new V1DeploymentBuilder()
                .withApiVersion("apps/v1")
                .withKind("Deployment")
                .withNewMetadata()
                .withName(deploymentName)
                .withNamespace(namepsace)
                .endMetadata()
                .withNewSpec()
                .withReplicas(replicas)
                .withNewSelector()
                .withMatchLabels(matchLabels)
                .endSelector()
                .withNewTemplate()
                .withNewMetadata()
                .withLabels(matchLabels)
                .endMetadata()
                .withNewSpec()
                .withContainers(
                        new V1Container()
                                .name(metadataLabelsApp)
                                .image(image)
                                .imagePullPolicy("IfNotPresent")
                                .ports(portList)
                )
                .endSpec()
                .endTemplate()
                .endSpec()
                .build();
        return body;
    }
}
