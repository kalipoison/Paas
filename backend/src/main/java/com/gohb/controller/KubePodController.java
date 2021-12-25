package com.gohb.controller;

import com.gohb.params.dto.Result;
import com.gohb.params.dto.ResultUtils;
import com.gohb.params.dto.kube.KubePodDTO;
import com.gohb.params.dto.kube.KubePodDetailDTO;
import com.gohb.manage.kube.KubePodManage;
import com.gohb.params.request.CreatePodRequest;
import io.kubernetes.client.openapi.ApiClient;
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

    /**
     * Pod 详情
     * @param namespace
     * @param podName
     * @return
     */
    @GetMapping("/detail")
    public Result<KubePodDetailDTO> podDetail(@RequestParam("namespace") String namespace, @RequestParam("podName") String podName) {
        KubePodDetailDTO kubePodDetailDTO = kubePodManage.podDetail(namespace, podName);
        return ResultUtils.getSuccessResult(kubePodDetailDTO);
    }

    /**
     * Pod 所以信息， yaml格式
     * @param namespace
     * @param podName
     * @return
     */
    @GetMapping("/detailYml")
    public Result<String> podDetailYml(@RequestParam("namespace") String namespace, @RequestParam("podName") String podName) {
        String podDetailYaml = kubePodManage.podDetailYaml(namespace, podName);
        return ResultUtils.getSuccessResult(podDetailYaml);
    }

    @PostMapping("")
    public Result<String> createPod(CreatePodRequest createPodRequest) {
        KubePodDTO kubePodDTO = kubePodManage.createPod(createPodRequest);
        return ResultUtils.getSuccessResult("pod : " + kubePodDTO.getPodName() + "创建成功");
    }


    @Autowired
    private ApiClient apiClient;

    @PostMapping("/createByYaml")
    public Result createByYaml(@RequestParam("podInfo") String podInfo) {
        V1Pod v1Pod = apiClient.getJSON().deserialize(podInfo, V1Pod.class);
        return ResultUtils.getSuccessResult("sss");
    }


}
