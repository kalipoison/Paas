package com.gohb.controller;

import com.gohb.params.bo.kube.KubeNamespaceBO;
import com.gohb.constant.StatusCodeConstant;
import com.gohb.params.dto.Result;
import com.gohb.params.dto.ResultUtils;
import com.gohb.params.dto.kube.KubeNamespaceDTO;
import com.gohb.manage.kube.KubeNamespaceManage;
import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.openapi.models.V1Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/namespace")
public class KubeNamespaceController {

    @Autowired
    private KubeNamespaceManage kubeNamespaceManage;

    @PostMapping("")
    public Result<V1Namespace> createNamespace(@RequestParam("namespace") String namespace) {
        V1Namespace v1Namespace = kubeNamespaceManage.createNamespace(namespace);
        return ResultUtils.getSuccessResult(v1Namespace);
    }

    @DeleteMapping("")
    public Result<V1Status> deleteNamespace(@RequestParam("namespace") String namespace) {
        V1Status v1Status = kubeNamespaceManage.deleteNamespace(namespace);
        return ResultUtils.getSuccessResult(v1Status);
    }

    @GetMapping("")
    public Result<List<KubeNamespaceDTO>> listNamespace() {
        List<KubeNamespaceDTO> kubeNamespaceDTOS = kubeNamespaceManage.listNamespace();
        return ResultUtils.getSuccessResult(kubeNamespaceDTOS);
    }

    @GetMapping("detail")
    public Result<String> namespaceDetail(@RequestParam("namespace") String namespace) {
        String namespaceDetail = kubeNamespaceManage.namespaceDetail(namespace);
        return ResultUtils.getSuccessResult(namespaceDetail);
    }

}
