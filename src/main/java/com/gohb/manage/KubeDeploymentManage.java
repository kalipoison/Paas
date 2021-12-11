package com.gohb.manage;

import com.gohb.bo.KubeDeploymentBO;
import io.kubernetes.client.openapi.models.V1Deployment;
import io.kubernetes.client.openapi.models.V1Status;

import java.util.List;

public interface KubeDeploymentManage {

    List<KubeDeploymentBO> listDeployment(String namespace);

    KubeDeploymentBO detailDeployment(String deploymentName, String namespace);

    V1Status deleteDeployment(String deploymentName, String namespace);

    KubeDeploymentBO updateDeployment(String deploymentName, String namepsace, Integer replicas, String metadataLabelsApp, String image, String portName, Integer containerPort);

    KubeDeploymentBO createDeployment(String deploymentName, String namepsace, Integer replicas, String metadataLabelsApp, String image, String portName, Integer containerPort);


}
