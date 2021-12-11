package com.gohb.convert;

import com.gohb.bo.KubeNamespaceBO;
import com.gohb.bo.KubeServiceBO;
import com.gohb.bo.KubeServicePortBO;
import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.openapi.models.V1Service;
import io.kubernetes.client.openapi.models.V1ServicePort;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KubeConvert {

    public static KubeNamespaceBO V1NamespaceToKubeNamespaceBO(V1Namespace v1Namespace) {
        KubeNamespaceBO kubeNamespaceBO = new KubeNamespaceBO();
        try{
            kubeNamespaceBO.setApiVersion(v1Namespace.getApiVersion());
            kubeNamespaceBO.setKind(v1Namespace.getKind());
            kubeNamespaceBO.setName(v1Namespace.getMetadata().getName());
            kubeNamespaceBO.setStatus(v1Namespace.getStatus().getPhase());
            DateTime creationTimestamp = v1Namespace.getMetadata().getCreationTimestamp();
            kubeNamespaceBO.setCreateTime(new Date(creationTimestamp.getMillis()));
            return kubeNamespaceBO;
        } catch (Exception e) {
            e.printStackTrace();
            return kubeNamespaceBO;
        }
    }

    public static KubeServicePortBO v1ServicePortToKubeServicePortBO(V1ServicePort v1ServicePort) {
        KubeServicePortBO kubeServicePortBO = new KubeServicePortBO();
        try {
            kubeServicePortBO.setPort(String.valueOf(v1ServicePort.getPort()));
            kubeServicePortBO.setNodePort(String.valueOf(v1ServicePort.getNodePort()));
            kubeServicePortBO.setTargetPort(String.valueOf(v1ServicePort.getTargetPort()));
            kubeServicePortBO.setName(v1ServicePort.getName());
            kubeServicePortBO.setProtocol(v1ServicePort.getProtocol());
            kubeServicePortBO.setAppProtocol(v1ServicePort.getAppProtocol());
            return kubeServicePortBO;
        } catch (Exception e) {
            return kubeServicePortBO;
        }
    }

    public static KubeServiceBO v1ServiceToKubeServiceBO(V1Service v1Service) {
        KubeServiceBO kubeServiceBO = new KubeServiceBO();
        try {
            kubeServiceBO.setApiVersion(v1Service.getApiVersion());
            kubeServiceBO.setKind(v1Service.getKind());
            DateTime creationTimestamp = v1Service.getMetadata().getCreationTimestamp();
            kubeServiceBO.setCreateTime(new Date(creationTimestamp.getMillis()));
            kubeServiceBO.setNamespace(v1Service.getMetadata().getNamespace());
            kubeServiceBO.setName(v1Service.getMetadata().getName());
            kubeServiceBO.setSelfLink(v1Service.getMetadata().getSelfLink());
            kubeServiceBO.setClusterIP(v1Service.getSpec().getClusterIP());
            kubeServiceBO.setExternalIPs(v1Service.getSpec().getExternalIPs());
            kubeServiceBO.setType(v1Service.getSpec().getType());
            List<V1ServicePort> v1ServicePorts = v1Service.getSpec().getPorts();
            List<KubeServicePortBO> kubeServicePortBOS = new ArrayList<>();
            for (V1ServicePort v1ServicePort : v1ServicePorts) {
                kubeServicePortBOS.add(KubeConvert.v1ServicePortToKubeServicePortBO(v1ServicePort));
            }
            kubeServiceBO.setPorts(kubeServicePortBOS);
            return kubeServiceBO;
        } catch (Exception e) {
            return kubeServiceBO;
        }
    }

}
