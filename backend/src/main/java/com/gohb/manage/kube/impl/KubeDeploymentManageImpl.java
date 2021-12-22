package com.gohb.manage.kube.impl;

import com.gohb.bo.kube.KubeDeploymentBO;
import com.gohb.convert.KubeToBoUtils;
import com.gohb.manage.kube.KubeDeploymentManage;
import com.gohb.service.kube.KubeDeploymentService;
import io.kubernetes.client.openapi.models.V1Deployment;
import io.kubernetes.client.openapi.models.V1Status;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class KubeDeploymentManageImpl implements KubeDeploymentManage {

    @Autowired
    private KubeDeploymentService kubeDeploymentService;

    @Override
    public List<KubeDeploymentBO> listDeployment(String namespace) {
        List<V1Deployment> v1Deployments = kubeDeploymentService.listDeployment(namespace);
        List<KubeDeploymentBO> kubeDeploymentBOS = new ArrayList<>();
        for (V1Deployment v1Deployment : v1Deployments) {
            kubeDeploymentBOS.add(KubeToBoUtils.v1DeploymentToKubeDeploymentBO(v1Deployment));
        }
        return kubeDeploymentBOS;
    }

    @Override
    public KubeDeploymentBO detailDeployment(String deploymentName, String namespace) {
        V1Deployment v1Deployment = kubeDeploymentService.detailDeployment(deploymentName, namespace);
        KubeDeploymentBO kubeDeploymentBO = KubeToBoUtils.v1DeploymentToKubeDeploymentBO(v1Deployment);
        return kubeDeploymentBO;
    }

    @Override
    public V1Status deleteDeployment(String deploymentName, String namespace) {
        V1Status v1Status = kubeDeploymentService.deleteDeployment(deploymentName, namespace);
        return v1Status;
    }

    @Override
    public KubeDeploymentBO updateDeployment(String deploymentName, String namepsace, Integer replicas, String metadataLabelsApp, String image, String portName, Integer containerPort) {
        V1Deployment v1Deployment = kubeDeploymentService.updateDeployment(deploymentName, namepsace, replicas, metadataLabelsApp, image, portName, containerPort);
        KubeDeploymentBO kubeDeploymentBO = KubeToBoUtils.v1DeploymentToKubeDeploymentBO(v1Deployment);
        return kubeDeploymentBO;
    }

    @Override
    public KubeDeploymentBO createDeployment(String deploymentName, String namepsace, Integer replicas, String metadataLabelsApp, String image, String portName, Integer containerPort) {
        V1Deployment v1Deployment = kubeDeploymentService.createDeployment(deploymentName, namepsace, replicas, metadataLabelsApp, image, portName, containerPort);
        KubeDeploymentBO kubeDeploymentBO = KubeToBoUtils.v1DeploymentToKubeDeploymentBO(v1Deployment);
        return kubeDeploymentBO;
    }
}
