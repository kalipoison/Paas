package com.gohb.service.kube;


import com.gohb.params.bo.kube.KubeDeploymentBO;
import com.gohb.params.bo.kube.KubeDeploymentDetailBO;
import com.gohb.params.request.kube.CreateDeploymentRequest;
import com.gohb.params.request.kube.UpdateDeploymentRequest;
import io.kubernetes.client.openapi.models.V1Status;

import java.util.List;

public interface KubeDeploymentService {

    List<KubeDeploymentBO> listDeployment(String namespace);

    KubeDeploymentDetailBO detailDeployment(String deploymentName, String namespace);

    V1Status deleteDeployment(String deploymentName, String namespace);

    KubeDeploymentBO updateDeployment(UpdateDeploymentRequest updateDeploymentRequest);

    KubeDeploymentBO createDeployment(CreateDeploymentRequest createDeploymentRequest);

    String deploymentDetailYaml(String namespace, String deploymentName);
}
