package com.gohb.manage.kube;

import com.gohb.params.dto.kube.KubeDeploymentDTO;
import com.gohb.params.dto.kube.KubeDeploymentDetailDTO;
import com.gohb.params.request.kube.CreateDeploymentRequest;
import com.gohb.params.request.kube.UpdateDeploymentRequest;
import io.kubernetes.client.openapi.models.V1Status;

import java.util.List;

public interface KubeDeploymentManage {

    List<KubeDeploymentDTO> listDeployment(String namespace);

    KubeDeploymentDetailDTO detailDeployment(String deploymentName, String namespace);

    V1Status deleteDeployment(String deploymentName, String namespace);

    KubeDeploymentDTO updateDeployment(UpdateDeploymentRequest updateDeploymentRequest);

    KubeDeploymentDTO createDeployment(CreateDeploymentRequest createDeploymentRequest);

    String deploymentDetailYaml(String namespace, String deploymentName);
}
