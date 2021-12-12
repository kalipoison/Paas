package com.gohb.service;


import io.kubernetes.client.openapi.models.V1Deployment;
import io.kubernetes.client.openapi.models.V1Status;

import java.util.List;

public interface KubeDeploymentService {

    List<V1Deployment> listDeployment(String namespace);

    V1Deployment detailDeployment(String deploymentName, String namespace);

    V1Status deleteDeployment(String deploymentName, String namespace);

    V1Deployment updateDeployment(String deploymentName, String namepsace, Integer replicas, String metadataLabelsApp, String image, String portName, Integer containerPort);

    V1Deployment createDeployment(String deploymentName, String namepsace, Integer replicas, String metadataLabelsApp, String image, String portName, Integer containerPort);
}
