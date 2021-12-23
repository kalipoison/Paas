package com.gohb.controller;

import com.gohb.bo.kube.KubeDeploymentBO;
import com.gohb.dto.Result;
import com.gohb.dto.ResultUtils;
import com.gohb.manage.kube.KubeDeploymentManage;
import io.kubernetes.client.openapi.models.V1Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/deployment")
public class KubeDeploymentController {

    @Autowired
    private KubeDeploymentManage kubeDeploymentManage;


    @GetMapping("")
    public Result<List<KubeDeploymentBO>> listDeployment(@RequestParam(value = "namespace", required = false) String namespace) {
        List<KubeDeploymentBO> kubeDeploymentBOS = kubeDeploymentManage.listDeployment(namespace);
        return ResultUtils.getSuccessResult(kubeDeploymentBOS);
    }

    @GetMapping("/detail")
    public Result<KubeDeploymentBO> detailDeployment(@RequestParam("deploymentName") String deploymentName,
                                         @RequestParam("namespace") String namespace) {
        KubeDeploymentBO kubeDeploymentBO = kubeDeploymentManage.detailDeployment(deploymentName, namespace);
        return ResultUtils.getSuccessResult(kubeDeploymentBO);
    }

    @DeleteMapping("")
    public Result<V1Status> deleteDeployment(@RequestParam("deploymentName") String deploymentName,
                                     @RequestParam("namespace") String namespace) {
        V1Status v1Status = kubeDeploymentManage.deleteDeployment(deploymentName, namespace);
        return ResultUtils.getSuccessResult(v1Status);
    }

    @PutMapping("")
    public Result<KubeDeploymentBO> updateDeployment(@RequestParam("deploymentName") String deploymentName,
                                         @RequestParam("namepsace") String namepsace, @RequestParam("replicas") Integer replicas,
                                         @RequestParam("metadataLabelsApp") String metadataLabelsApp, @RequestParam("image") String image,
                                         @RequestParam("portName") String portName, @RequestParam("containerPort") Integer containerPort) {
        KubeDeploymentBO kubeDeploymentBO = kubeDeploymentManage.updateDeployment(deploymentName, namepsace, replicas, metadataLabelsApp, image, portName, containerPort);
        return ResultUtils.getSuccessResult(kubeDeploymentBO);
    }

    @PostMapping("")
    public Result<KubeDeploymentBO> createDeployment(@RequestParam("deploymentName") String deploymentName,
                                         @RequestParam("namepsace") String namepsace, @RequestParam("replicas") Integer replicas,
                                         @RequestParam("metadataLabelsApp") String metadataLabelsApp, @RequestParam("image") String image,
                                         @RequestParam("portName") String portName, @RequestParam("containerPort") Integer containerPort) {
        KubeDeploymentBO kubeDeploymentBO = kubeDeploymentManage.createDeployment(deploymentName, namepsace, replicas, metadataLabelsApp, image, portName, containerPort);
        return ResultUtils.getSuccessResult(kubeDeploymentBO);
    }


}
