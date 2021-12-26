package com.gohb.controller;

import com.gohb.params.bo.kube.KubeServiceBO;
import com.gohb.constant.StatusCodeConstant;
import com.gohb.params.dto.Result;
import com.gohb.params.dto.ResultUtils;
import com.gohb.manage.kube.KubeServiceManage;
import com.gohb.params.dto.kube.KubeServiceDTO;
import com.gohb.params.dto.kube.KubeServiceDetailDTO;
import com.gohb.params.request.CreateServiceRequest;
import com.gohb.params.request.UpdateServiceRequest;
import io.kubernetes.client.custom.IntOrString;
import io.kubernetes.client.openapi.models.V1Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * v1Service json转换会失败，需要进行封装
 */
@RestController
@RequestMapping("/api/auth/service")
public class KubeServiceController {

    @Autowired
    private KubeServiceManage kubeServiceManage;


    @GetMapping("")
    public Result<List<KubeServiceDTO>> listService(@RequestParam(value = "namespace", required = false) String namespace) {
        List<KubeServiceDTO> kubeServiceDTOS = kubeServiceManage.listService(namespace);
        return ResultUtils.getSuccessResult(kubeServiceDTOS);
    }


    @PostMapping("")
    public Result<KubeServiceDTO> createService(CreateServiceRequest createServiceRequest) {
        KubeServiceDTO kubeServiceDTO = kubeServiceManage.createService(createServiceRequest);
        return ResultUtils.getSuccessResult(kubeServiceDTO);
    }

    @DeleteMapping("")
    public Result<V1Status> deleteService(@RequestParam("serviceName")String serviceName,
                                          @RequestParam("namespace") String namespace) {
        V1Status v1Status = kubeServiceManage.deleteService(serviceName, namespace);
        return ResultUtils.getSuccessResult(v1Status);
    }

    @PutMapping("")
    public Result<KubeServiceDTO> updateService(UpdateServiceRequest updateServiceRequest) {
        KubeServiceDTO kubeServiceDTO = kubeServiceManage.updateService(updateServiceRequest);
        return ResultUtils.getSuccessResult(kubeServiceDTO);
    }


    @GetMapping("detailYml")
    public Result<String> serviceDetailYaml(@RequestParam("serviceName")String serviceName,
                                               @RequestParam("namespace") String namespace) {
        String serviceDetailYaml = kubeServiceManage.serviceDetailYaml(serviceName, namespace);
        return ResultUtils.getSuccessResult(serviceDetailYaml);
    }

    @GetMapping("detail")
    public Result<KubeServiceDetailDTO> serviceDetail(@RequestParam("serviceName")String serviceName,
                                        @RequestParam("namespace") String namespace) {
        KubeServiceDetailDTO kubeServiceDetailDTO = kubeServiceManage.serviceDetail(serviceName, namespace);
        return ResultUtils.getSuccessResult(kubeServiceDetailDTO);
    }

}
