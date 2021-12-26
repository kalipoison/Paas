package com.gohb.controller;

import com.gohb.params.bo.kube.KubeDeploymentBO;
import com.gohb.params.dto.Result;
import com.gohb.params.dto.ResultUtils;
import com.gohb.manage.kube.KubeDeploymentManage;
import com.gohb.params.dto.kube.KubeDeploymentDTO;
import com.gohb.params.dto.kube.KubeDeploymentDetailDTO;
import com.gohb.params.request.CreateDeploymentRequest;
import com.gohb.params.request.UpdateDeploymentRequest;
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
    public Result<List<KubeDeploymentDTO>> listDeployment(@RequestParam(value = "namespace", required = false) String namespace) {
        List<KubeDeploymentDTO> kubeDeploymentDTOS = kubeDeploymentManage.listDeployment(namespace);
        return ResultUtils.getSuccessResult(kubeDeploymentDTOS);
    }

    @GetMapping("/detail")
    public Result<KubeDeploymentDetailDTO> detailDeployment(@RequestParam("deploymentName") String deploymentName,
                                                            @RequestParam("namespace") String namespace) {
        KubeDeploymentDetailDTO kubeDeploymentDetailDTO = kubeDeploymentManage.detailDeployment(deploymentName, namespace);
        return ResultUtils.getSuccessResult(kubeDeploymentDetailDTO);
    }

    /**
     * deploymentName 所有信息， yaml格式
     * @param namespace
     * @param deploymentName
     * @return
     */
    @GetMapping("/detailYml")
    public Result<String> deploymentDetailYml(@RequestParam("namespace") String namespace, @RequestParam("deploymentName") String deploymentName) {
        String podDetailYaml = kubeDeploymentManage.deploymentDetailYaml(namespace, deploymentName);
        return ResultUtils.getSuccessResult(podDetailYaml);
    }

    @DeleteMapping("")
    public Result<V1Status> deleteDeployment(@RequestParam("deploymentName") String deploymentName,
                                     @RequestParam("namespace") String namespace) {
        V1Status v1Status = kubeDeploymentManage.deleteDeployment(deploymentName, namespace);
        return ResultUtils.getSuccessResult(v1Status);
    }

    @PutMapping("")
    public Result<KubeDeploymentDTO> updateDeployment(UpdateDeploymentRequest updateDeploymentRequest) {
        KubeDeploymentDTO kubeDeploymentDTO = kubeDeploymentManage.updateDeployment(updateDeploymentRequest);
        return ResultUtils.getSuccessResult(kubeDeploymentDTO);
    }

    @PostMapping("")
    public Result<KubeDeploymentDTO> createDeployment(CreateDeploymentRequest createDeploymentRequest) {
        KubeDeploymentDTO kubeDeploymentDTO = kubeDeploymentManage.createDeployment(createDeploymentRequest);
        return ResultUtils.getSuccessResult(kubeDeploymentDTO);
    }


}
