package com.gohb.manage.kube.impl;

import com.gohb.convert.BoToDtoUtils;
import com.gohb.params.bo.kube.KubeDeploymentBO;
import com.gohb.params.bo.kube.KubeDeploymentDetailBO;
import com.gohb.params.bo.kube.KubeNamespaceBO;
import com.gohb.convert.KubeToBoUtils;
import com.gohb.manage.kube.KubeDeploymentManage;
import com.gohb.params.dto.kube.KubeDeploymentDTO;
import com.gohb.params.dto.kube.KubeDeploymentDetailDTO;
import com.gohb.params.request.CreateDeploymentRequest;
import com.gohb.params.request.UpdateDeploymentRequest;
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
    public KubeDeploymentDetailDTO detailDeployment(String deploymentName, String namespace) {
        KubeDeploymentDetailBO kubeDeploymentDetailBO = kubeDeploymentService.detailDeployment(deploymentName, namespace);
        KubeDeploymentDetailDTO kubeDeploymentDetailDTO = BoToDtoUtils.kubeDeploymentDetailBOTOKubeDeploymentDetailDTO(kubeDeploymentDetailBO);
        return kubeDeploymentDetailDTO;
    }

    @Override
    public V1Status deleteDeployment(String deploymentName, String namespace) {
        V1Status v1Status = kubeDeploymentService.deleteDeployment(deploymentName, namespace);
        return v1Status;
    }

    @Override
    public KubeDeploymentDTO updateDeployment(UpdateDeploymentRequest updateDeploymentRequest) {
        KubeDeploymentBO kubeDeploymentBO = kubeDeploymentService.updateDeployment(updateDeploymentRequest);
        KubeDeploymentDTO kubeDeploymentDTO = BoToDtoUtils.kubeDeploymentBOTOKubeDeploymentDTO(kubeDeploymentBO);
        return kubeDeploymentDTO;
    }

    @Override
    public KubeDeploymentDTO createDeployment(CreateDeploymentRequest createDeploymentRequest) {
        KubeDeploymentBO kubeDeploymentBO = kubeDeploymentService.createDeployment(createDeploymentRequest);
        KubeDeploymentDTO kubeDeploymentDTO = BoToDtoUtils.kubeDeploymentBOTOKubeDeploymentDTO(kubeDeploymentBO);
        return kubeDeploymentDTO;
    }

    @Override
    public String deploymentDetailYaml(String namespace, String deploymentName) {
        return kubeDeploymentService.deploymentDetailYaml(namespace, deploymentName);
    }
}
