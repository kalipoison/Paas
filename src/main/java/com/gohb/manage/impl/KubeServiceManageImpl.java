package com.gohb.manage.impl;

import com.gohb.manage.KubeServiceManage;
import com.gohb.service.KubeServiceService;
import io.kubernetes.client.custom.IntOrString;
import io.kubernetes.client.openapi.models.V1Service;
import io.kubernetes.client.openapi.models.V1Status;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import java.util.List;

@ManagedBean
public class KubeServiceManageImpl implements KubeServiceManage {

    @Autowired
    private KubeServiceService kubeServiceService;

    @Override
    public V1Service createService(String serviceName, String namespace, String type, Integer port, Integer nodePort, IntOrString targetPort, String protocol) {
        kubeServiceService.createService(serviceName, namespace, type, port, nodePort, targetPort, protocol);
        return null;
    }

    @Override
    public V1Status deleteService(String serviceName, String namespace) {
        V1Status v1Status = kubeServiceService.deleteService(serviceName, namespace);
        return v1Status;
    }

    @Override
    public List<V1Service> listService(String namespace) {
        List<V1Service> v1ServiceList = kubeServiceService.listService(namespace);
        return v1ServiceList;
    }

    @Override
    public V1Service serviceDetail(String serviceName, String namespace) {
        V1Service v1Service = kubeServiceService.serviceDetail(serviceName, namespace);
        return v1Service;
    }

    @Override
    public Boolean isExistService(String serviceName, String namespace) {
        V1Service v1Service = kubeServiceService.serviceDetail(serviceName, namespace);
        return v1Service == null ? false : true;
    }
}
