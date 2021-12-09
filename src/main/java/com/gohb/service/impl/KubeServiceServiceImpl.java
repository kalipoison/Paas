package com.gohb.service.impl;

import com.gohb.service.KubeServiceService;
import io.kubernetes.client.custom.IntOrString;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KubeServiceServiceImpl implements KubeServiceService {

    @Autowired
    private CoreV1Api api;

    @Override
    public V1Service createService(String serviceName, String namespace, String type, Integer port, Integer nodePort, IntOrString targetPort, String protocol) {
        //    "metadata_name":"test-bs",
        //    "metadata_namespace":"test",
        //    "labels_workLayer":"svc",
        //    "spec_type":"ClusterIP",
        //    "spec_ports_port":8995,
        //    "spec_ports_targetPort":8995,
        //    "spec_ports_protocol":"TCP"
        V1Service svc = new V1Service();
        V1ObjectMeta objectMeta = new V1ObjectMeta();
        objectMeta.setName(serviceName);
        objectMeta.setNamespace(namespace);
        V1ServiceSpec serviceSpec = new V1ServiceSpec();
        List<V1ServicePort> servicePorts = new ArrayList<>();
        serviceSpec.setType(type);
        V1ServicePort servicePort = new V1ServicePort();
        servicePort.setPort(port);
        servicePort.setNodePort(nodePort);
        servicePort.setProtocol(protocol);
        servicePort.setTargetPort(targetPort);
        servicePorts.add(servicePort);
        serviceSpec.setPorts(servicePorts);
        svc.setApiVersion("v1");
        svc.setKind("Service");
        svc.setMetadata(objectMeta);
        svc.setSpec(serviceSpec);
        V1Service v1Service = null;
        try {
            v1Service = api.createNamespacedService(namespace, svc, null, null, null);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return v1Service;
    }

    @Override
    public V1Status deleteService(String serviceName, String namespace) {
        V1Status v1Status = null;
        try {
            v1Status = api.deleteNamespacedService(serviceName, namespace, null, null, null, null, null, new V1DeleteOptions());
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return v1Status;
    }

    @Override
    public List<V1Service> listService(String namespace) {
        List<V1Service> v1ServiceListItems = null;
        try {
            V1ServiceList v1ServiceList = api.listNamespacedService(namespace, null, null, null, null, null, null, null, null, null);
            v1ServiceListItems = v1ServiceList.getItems();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return v1ServiceListItems;
    }

    @Override
    public V1Service serviceDetail(String serviceName, String namespace) {
        V1Service v1Service = null;
        try {
            v1Service = api.readNamespacedService(serviceName, namespace, null, null, null);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return v1Service;
    }
}
