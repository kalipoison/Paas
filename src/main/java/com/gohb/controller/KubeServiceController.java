package com.gohb.controller;

import com.gohb.manage.KubeServiceManage;
import io.kubernetes.client.custom.IntOrString;
import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.openapi.models.V1Service;
import io.kubernetes.client.openapi.models.V1Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * v1Service json转换会失败，需要进行封装
 */
@RestController
@RequestMapping("/service")
public class KubeServiceController {

    @Autowired
    private KubeServiceManage kubeServiceManage;

    @PostMapping("")
    public V1Service createService(@RequestParam("serviceName") String serviceName, @RequestParam("namespace") String namespace,
                                       @RequestParam("type") String type, @RequestParam("port") Integer port,
                                       @RequestParam("nodePort") Integer nodePort, @RequestParam("targetPort") IntOrString targetPort,
                                       @RequestParam("protocol") String protocol) {
        V1Service v1Service = kubeServiceManage.createService(serviceName, namespace, type, port, nodePort, targetPort, protocol);
        return v1Service;
    }

    @DeleteMapping("")
    public V1Status deleteService(@RequestParam("serviceName")String serviceName, @RequestParam("namespace") String namespace) {
        if (!kubeServiceManage.isExistService(serviceName, namespace)) {
            return null;
        }
        V1Status v1Status = kubeServiceManage.deleteService(serviceName, namespace);
        return v1Status;
    }

    @GetMapping("")
    public List<V1Service> listService(String namespace) {
        List<V1Service> v1ServiceList = kubeServiceManage.listService(namespace);
        return v1ServiceList;
    }

    @GetMapping("exist")
    public Boolean isExistService(@RequestParam("serviceName")String serviceName,@RequestParam("namespace") String namespace) {
        Boolean exist = kubeServiceManage.isExistService(serviceName, namespace);
        return exist;
    }

    @GetMapping("detail")
    public V1Service serviceDetail(String serviceName,String namespace) {
        V1Service v1Service = kubeServiceManage.serviceDetail(serviceName, namespace);
        return v1Service;
    }

}
