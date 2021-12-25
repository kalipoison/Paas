package com.gohb.manage.kube.impl;

import com.gohb.params.bo.kube.KubeServiceBO;
import com.gohb.convert.KubeToBoUtils;
import com.gohb.manage.kube.KubeServiceManage;
import com.gohb.service.kube.KubeServiceService;
import io.kubernetes.client.custom.IntOrString;
import io.kubernetes.client.openapi.models.V1Service;
import io.kubernetes.client.openapi.models.V1Status;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class KubeServiceManageImpl implements KubeServiceManage {

    @Autowired
    private KubeServiceService kubeServiceService;

    @Override
    public KubeServiceBO createService(String serviceName, String namespace, String type, Integer port, Integer nodePort, IntOrString targetPort, String protocol) {
        V1Service v1Service = kubeServiceService.createService(serviceName, namespace, type, port, nodePort, targetPort, protocol);
        KubeServiceBO kubeServiceBO = KubeToBoUtils.v1ServiceToKubeServiceBO(v1Service);
        return kubeServiceBO;
    }

    @Override
    public V1Status deleteService(String serviceName, String namespace) {
        V1Status v1Status = kubeServiceService.deleteService(serviceName, namespace);
        return v1Status;
    }

    @Override
    public List<KubeServiceBO> listService(String namespace) {
        List<V1Service> v1ServiceList = kubeServiceService.listService(namespace);
        List<KubeServiceBO> kubeServiceBOS = new ArrayList<>();
        for (V1Service v1Service : v1ServiceList) {
            kubeServiceBOS.add(KubeToBoUtils.v1ServiceToKubeServiceBO(v1Service));
        }
        return kubeServiceBOS;
    }

    @Override
    public KubeServiceBO serviceDetail(String serviceName, String namespace) {
        V1Service v1Service = kubeServiceService.serviceDetail(serviceName, namespace);
        KubeServiceBO kubeServiceBO = KubeToBoUtils.v1ServiceToKubeServiceBO(v1Service);
        return kubeServiceBO;
    }

    @Override
    public Boolean isExistService(String serviceName, String namespace) {
        V1Service v1Service = kubeServiceService.serviceDetail(serviceName, namespace);
        return v1Service == null ? false : true;
    }
}
