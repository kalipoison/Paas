package com.gohb.controller;

import com.gohb.constant.StatusCodeConstant;
import com.gohb.dto.Result;
import com.gohb.dto.ResultUtils;
import com.gohb.dto.kube.KubeNodeDTO;
import com.gohb.manage.kube.KubeNodeManage;
import io.kubernetes.client.openapi.models.V1Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auth/node")
public class KubeNodeController {

    @Autowired
    private KubeNodeManage kubeNodeManage;


    @GetMapping()
    public Result<List<KubeNodeDTO>> listNode() {
        List<KubeNodeDTO> kubeNodeDTOS = kubeNodeManage.listNode();
        return ResultUtils.getSuccessResult(kubeNodeDTOS);
    }

    @GetMapping("detail")
    public Result<V1Node> nodeDetail(@RequestParam("nodeName") String nodeName) {
        V1Node v1Node = kubeNodeManage.nodeDetail(nodeName);
        if (ObjectUtils.isEmpty(v1Node)) {
            return ResultUtils.getFailedResult(StatusCodeConstant.isNotExist, String.format(""));
        }

        return ResultUtils.getSuccessResult(v1Node);
    }

    @GetMapping("exist")
    public Result<Boolean> isExistNode(@RequestParam("nodeName") String nodeName) {
        Boolean exist = kubeNodeManage.isExistNode(nodeName);
        return ResultUtils.getSuccessResult(exist);
    }

}
