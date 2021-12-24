package com.gohb.controller;

import com.gohb.dto.Result;
import com.gohb.dto.ResultUtils;
import com.gohb.dto.kube.KubePodDTO;
import com.gohb.dto.kube.KubePodDetailDTO;
import com.gohb.manage.kube.KubePodManage;
import io.kubernetes.client.openapi.models.V1Pod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/auth/pod")
public class KubePodController {

    @Autowired
    private KubePodManage kubePodManage;

    /**
     * 列出namespace下所有pod
     * namespace为空，则查询所有namespace下的pod
     * @param namespace
     * @return
     */
    @GetMapping("")
    public Result<List<KubePodDTO>> listPod(@RequestParam(value = "namespace", required = false) String namespace) {
        List<KubePodDTO> kubePodDTOS = kubePodManage.listPod(namespace);
        return ResultUtils.getSuccessResult(kubePodDTOS);
    }

    /**
     * 删除namespace 下 pod
     * @param namespace
     * @param podName
     * @return
     */
    @DeleteMapping("")
    public Result<KubePodDTO> deletePod(@RequestParam("namespace") String namespace, @RequestParam("podName") String podName) {
        KubePodDTO kubePodDTO = kubePodManage.deletePod(namespace, podName);
        return ResultUtils.getSuccessResult(kubePodDTO);
    }

    @GetMapping("/detail")
    public Result<KubePodDetailDTO> podDetail(@RequestParam("namespace") String namespace, @RequestParam("podName") String podName) {
        KubePodDetailDTO kubePodDetailDTO = kubePodManage.podDetail(namespace, podName);
        return ResultUtils.getSuccessResult(kubePodDetailDTO);
    }

    @GetMapping("/detailYml")
    public Result<String> podDetailYml(@RequestParam("namespace") String namespace, @RequestParam("podName") String podName) {
        String podDetailYaml = kubePodManage.podDetailYaml(namespace, podName);
        return ResultUtils.getSuccessResult(podDetailYaml);
    }



}
