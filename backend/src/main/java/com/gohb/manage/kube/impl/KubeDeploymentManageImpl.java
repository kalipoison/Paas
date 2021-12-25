package com.gohb.manage.kube.impl;

import com.gohb.params.bo.kube.KubeDeploymentBO;
import com.gohb.params.bo.kube.KubeNamespaceBO;
import com.gohb.convert.KubeToBoUtils;
import com.gohb.manage.kube.KubeDeploymentManage;
import com.gohb.service.kube.KubeDeploymentService;
import com.gohb.service.kube.KubeNamespaceService;
import io.kubernetes.client.openapi.models.V1Deployment;
import io.kubernetes.client.openapi.models.V1Status;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean
public class KubeDeploymentManageImpl implements KubeDeploymentManage {

    @Autowired
    private KubeDeploymentService kubeDeploymentService;

    @Autowired
    private KubeNamespaceService kubeNamespaceService;

    @Override
    public List<KubeDeploymentBO> listDeployment(String namespace) {
        List<KubeDeploymentBO> kubeDeploymentBOS = new ArrayList<>();
        if(namespace != null && !"".equals(namespace)) {
            List<KubeDeploymentBO> kubeDeploymentBOList = listDeploymentByNamespace(namespace);
            kubeDeploymentBOS.addAll(kubeDeploymentBOList);
        } else {
            List<KubeNamespaceBO> kubeNamespaceBOS = kubeNamespaceService.listNamespace();
            List<String> namespaceList = kubeNamespaceBOS.stream().map(KubeNamespaceBO::getName).collect(Collectors.toList());
            for (String name : namespaceList) {
                List<KubeDeploymentBO> kubeDeploymentBOList = listDeploymentByNamespace(name);
                kubeDeploymentBOS.addAll(kubeDeploymentBOList);
            }
        }
        return kubeDeploymentBOS;
    }

    private List<KubeDeploymentBO>  listDeploymentByNamespace(String namespace) {
        List<KubeDeploymentBO> kubeDeploymentBOS = new ArrayList<>();
        List<V1Deployment> v1Deployments = kubeDeploymentService.listDeployment(namespace);
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
