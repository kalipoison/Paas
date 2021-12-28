package com.gohb.service.kube.impl;

import com.gohb.convert.KubeToBoUtils;
import com.gohb.params.bo.kube.KubeServiceBO;
import com.gohb.params.bo.kube.KubeServiceDetailBO;
import com.gohb.params.request.kube.CreateServiceRequest;
import com.gohb.params.request.kube.UpdateServiceRequest;
import com.gohb.service.kube.KubeServiceService;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KubeServiceServiceImpl implements KubeServiceService {

    @Autowired
    private CoreV1Api coreV1Api;

    @Override
    public V1Service createService(CreateServiceRequest createServiceRequest) {
        V1Service v1Service = null;
        Map<String, String> selector = new HashMap<>();
        String specType = "ClusterIP";
        if (createServiceRequest.getType() != null && !"".equals(createServiceRequest.getType())) {
            specType = createServiceRequest.getType();
        }
        selector.put("app", createServiceRequest.getSpecSelectorApp());
        V1Service body = new V1Service()
            .apiVersion(createServiceRequest.getApiVersion())
            .metadata(new V1ObjectMeta()
                .name(createServiceRequest.getMetadataName()))
            .spec(new V1ServiceSpec()
                .type(specType)
                .selector(selector)
                .externalIPs(Arrays.asList(createServiceRequest.getExternalIP()))
                .ports(Arrays.asList(new V1ServicePort()
                    .port(Integer.valueOf(createServiceRequest.getSpecPort()))
                    .protocol(createServiceRequest.getSpecProtocol()))));
        try {
            v1Service = coreV1Api.createNamespacedService(createServiceRequest.getNamespace(), body, null, null, null);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return v1Service;
    }

    @Override
    public V1Status deleteService(String serviceName, String namespace) {
        V1Status v1Status = null;
        try {
            v1Status = coreV1Api.deleteNamespacedService(serviceName, namespace, null, null, null, null, null, new V1DeleteOptions());
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return v1Status;
    }

    @Override
    public KubeServiceBO updateService(UpdateServiceRequest updateServiceRequest) {
        V1Service v1Service = null;
        Map<String, String> selector = new HashMap<>();
        String specType = "ClusterIP";
        if (updateServiceRequest.getType() != null && !"".equals(updateServiceRequest.getType())) {
            specType = updateServiceRequest.getType();
        }
        selector.put("app", updateServiceRequest.getSpecSelectorApp());
        V1Service body = new V1Service()
            .apiVersion(updateServiceRequest.getApiVersion())
            .metadata(new V1ObjectMeta()
                .name(updateServiceRequest.getMetadataName()))
            .spec(new V1ServiceSpec()
                .type(specType)
                .selector(selector)
                .externalIPs(Arrays.asList(updateServiceRequest.getExternalIP()))
                .ports(Arrays.asList(new V1ServicePort()
                    .port(Integer.valueOf(updateServiceRequest.getSpecPort()))
                    .protocol(updateServiceRequest.getSpecProtocol()))));
        KubeServiceBO kubeServiceBO = null;
        try {
            v1Service = coreV1Api.replaceNamespacedService(updateServiceRequest.getMetadataName(), updateServiceRequest.getNamespace(), body, null, null, null);
            kubeServiceBO = KubeToBoUtils.v1ServiceToKubeServiceBO(v1Service);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kubeServiceBO;
    }

    @Override
    public List<KubeServiceBO> listNamespaceService(String namespace) {
        List<KubeServiceBO> kubeServiceBOS = new ArrayList<>();
        try {
            V1ServiceList v1ServiceList = coreV1Api.listNamespacedService(namespace, null, null, null, null, null, null, null, null, null, null);
            List<V1Service> v1Services = v1ServiceList.getItems();
            for (V1Service v1Service : v1Services) {
                KubeServiceBO kubeServiceBO = KubeToBoUtils.v1ServiceToKubeServiceBO(v1Service);
                kubeServiceBOS.add(kubeServiceBO);
            }
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return kubeServiceBOS;
    }

    @Override
    public String serviceDetailYaml(String serviceName, String namespace) {
        String serviceYaml = "";
        try {
            V1Service v1Service = coreV1Api.readNamespacedService(serviceName, namespace, null, null, null);
            serviceYaml = v1Service.toString();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return serviceYaml;
    }

    @Override
    public KubeServiceDetailBO serviceDetail(String serviceName, String namespace) {
        KubeServiceDetailBO kubeServiceDetailBO = null;
        try {
            V1Service v1Service = coreV1Api.readNamespacedService(serviceName, namespace, null, null, null);
            kubeServiceDetailBO = KubeToBoUtils.v1ServiceToKubeServiceDetailBO(v1Service);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return kubeServiceDetailBO;
    }
}
