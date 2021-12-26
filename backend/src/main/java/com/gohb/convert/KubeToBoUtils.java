package com.gohb.convert;

import com.gohb.params.bo.kube.*;
import io.kubernetes.client.custom.Quantity;
import io.kubernetes.client.openapi.models.*;
import org.joda.time.DateTime;

import java.util.*;

/**
 * k8s 返回值过多，提取有用的
 */
public class KubeToBoUtils {

    public static KubeNodeBO v1NodeToKubeNodeBO(V1Node v1Node) {
        KubeNodeBO kubeNodeBO = new KubeNodeBO();
        try {
            kubeNodeBO.setNodeName(v1Node.getMetadata().getName());
            kubeNodeBO.setCreateTime(DateTimeUtils.kubeDateTimeToString(v1Node.getMetadata().getCreationTimestamp()));
            kubeNodeBO.setKernelVersion(v1Node.getStatus().getNodeInfo().getKernelVersion());
            kubeNodeBO.setKubeletVersion(v1Node.getStatus().getNodeInfo().getKubeletVersion());
            kubeNodeBO.setKubeProxyVersion(v1Node.getStatus().getNodeInfo().getKubeProxyVersion());
            return kubeNodeBO;
        } catch (Exception e) {
            e.printStackTrace();
            return kubeNodeBO;
        }
    }


    public static KubeNamespaceBO V1NamespaceToKubeNamespaceBO(V1Namespace v1Namespace) {
        KubeNamespaceBO kubeNamespaceBO = new KubeNamespaceBO();
        try {
            kubeNamespaceBO.setApiVersion(v1Namespace.getApiVersion());
            kubeNamespaceBO.setKind(v1Namespace.getKind());
            kubeNamespaceBO.setName(v1Namespace.getMetadata().getName());
            kubeNamespaceBO.setStatus(v1Namespace.getStatus().getPhase());
            DateTime creationTimestamp = v1Namespace.getMetadata().getCreationTimestamp();
            kubeNamespaceBO.setCreateTime(DateTimeUtils.kubeDateTimeToString(creationTimestamp));
            return kubeNamespaceBO;
        } catch (Exception e) {
            e.printStackTrace();
            return kubeNamespaceBO;
        }
    }

    public static KubePodBO v1PodTOKubePodBO(V1Pod v1Pod) {
        KubePodBO kubePodBO = new KubePodBO();
        try{
            kubePodBO.setPodName(v1Pod.getMetadata().getName());
            kubePodBO.setNamespace(v1Pod.getMetadata().getNamespace());
            kubePodBO.setLabelsApp(v1Pod.getMetadata().getLabels().get("app"));
            kubePodBO.setCreateTime(DateTimeUtils.kubeDateTimeToString(v1Pod.getMetadata().getCreationTimestamp()));
            kubePodBO.setRestartPolicy(v1Pod.getSpec().getRestartPolicy());
            kubePodBO.setServiceAccount(v1Pod.getSpec().getServiceAccount());
            kubePodBO.setStatus(v1Pod.getStatus().getPhase());
            return kubePodBO;
        } catch (Exception e) {
            return kubePodBO;
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
                kubeServicePortBOS.add(KubeToBoUtils.v1ServicePortToKubeServicePortBO(v1ServicePort));
            }
            kubeServiceBO.setPorts(kubeServicePortBOS);
            return kubeServiceBO;
        } catch (Exception e) {
            return kubeServiceBO;
        }
    }

    public static KubeDeploymentBO v1DeploymentToKubeDeploymentBO(V1Deployment v1Deployment){
        KubeDeploymentBO kubeDeploymentBO = new KubeDeploymentBO();
        try {
            System.out.println(v1Deployment.getMetadata().getName());
            String name = v1Deployment.getMetadata().getName();
            kubeDeploymentBO.setName(name);
            kubeDeploymentBO.setNamespace(v1Deployment.getMetadata().getNamespace());
            kubeDeploymentBO.setLabels(v1Deployment.getMetadata().getLabels());
            kubeDeploymentBO.setSelfLink(v1Deployment.getMetadata().getSelfLink());
            kubeDeploymentBO.setRestartPolicy(v1Deployment.getSpec().getTemplate().getSpec().getRestartPolicy());
            kubeDeploymentBO.setReplicas(v1Deployment.getStatus().getReplicas());
            kubeDeploymentBO.setUnavilableReplicas(v1Deployment.getStatus().getUnavailableReplicas() != null ?
                    v1Deployment.getStatus().getUnavailableReplicas() : 0);
            kubeDeploymentBO.setAvilableReplicas(v1Deployment.getStatus().getAvailableReplicas() != null ?
                    v1Deployment.getStatus().getAvailableReplicas() : 0);
            return kubeDeploymentBO;
        } catch (Exception e) {
            return kubeDeploymentBO;
        }
    }

    public static KubeDeploymentDetailBO v1DeploymentToKubeDeploymentDetailBO(V1Deployment v1Deployment) {
        KubeDeploymentDetailBO kubeDeploymentDetailBO = new KubeDeploymentDetailBO();
        try {
            kubeDeploymentDetailBO.setApiVersion(v1Deployment.getApiVersion());
            kubeDeploymentDetailBO.setNamespace(v1Deployment.getMetadata().getNamespace());
            kubeDeploymentDetailBO.setMetadataName(v1Deployment.getMetadata().getName());
            String matchLabelsApp = "";
            if (v1Deployment.getSpec().getSelector().getMatchLabels().get("app") != null) {
                matchLabelsApp = v1Deployment.getSpec().getSelector().getMatchLabels().get("app");
            }
            kubeDeploymentDetailBO.setMatchLabelsApp(matchLabelsApp);
            kubeDeploymentDetailBO.setReplicas(String.valueOf(v1Deployment.getSpec().getReplicas()));
            kubeDeploymentDetailBO.setTemplateSpecConatinersName(v1Deployment.getSpec().getTemplate().getSpec().getContainers().get(0).getName());
            kubeDeploymentDetailBO.setTemplateSpecContainersImage(v1Deployment.getSpec().getTemplate().getSpec().getContainers().get(0).getImage());
            kubeDeploymentDetailBO.setTemplateContainerPort(String.valueOf(v1Deployment.getSpec().getTemplate().getSpec().getContainers().get(0).getPorts().get(0).getContainerPort()));
            V1ResourceRequirements resources = v1Deployment.getSpec().getTemplate().getSpec().getContainers().get(0).getResources();
            Map<String, Quantity> limits = resources.getLimits();
            String limitCPU = limits.get("cpu") != null ? limits.get("cpu").toSuffixedString() : "";
            String limitMemory = limits.get("memory") != null ? limits.get("memory").toSuffixedString() : "";
            Map<String, Quantity> requests = resources.getRequests();
            String requestCPU = requests.get("cpu") != null ? requests.get("cpu").toSuffixedString() : "";
            String requestMemory = requests.get("memory") != null ? requests.get("memory").toSuffixedString() : "";
            kubeDeploymentDetailBO.setLimitCPU(limitCPU);
            kubeDeploymentDetailBO.setLimitMemory(limitMemory);
            kubeDeploymentDetailBO.setRequestCPU(requestCPU);
            kubeDeploymentDetailBO.setRequestMemory(requestMemory);
            return kubeDeploymentDetailBO;
        } catch (Exception e) {
            return kubeDeploymentDetailBO;
        }
    }

}
