package com.gohb.convert;

import com.gohb.bo.KubeNamespaceBO;
import com.gohb.bo.KubeStatusBO;
import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.openapi.models.V1Status;
import org.joda.time.DateTime;

import java.util.Date;

public class KubeConvert {

    public static KubeNamespaceBO V1NamespaceToKubeNamespaceBO(V1Namespace v1Namespace) {
        KubeNamespaceBO kubeNamespaceBO = new KubeNamespaceBO();
        try{
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


}
