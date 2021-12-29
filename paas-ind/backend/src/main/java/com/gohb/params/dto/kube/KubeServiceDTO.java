package com.gohb.params.dto.kube;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * k8s service bo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class KubeServiceDTO implements Serializable {

    String apiVersion;

    String kind;

    Date createTime;

    String namespace;

    String metadataName;

    String specSelectorLabelsApp;

    String type;

    String clusterIP;

    String externalIP;

    String protocal;

    String port;

    String targetPort;

}
