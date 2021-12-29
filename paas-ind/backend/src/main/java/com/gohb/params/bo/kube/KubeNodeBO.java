package com.gohb.params.bo.kube;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * k8s node bo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class KubeNodeBO implements Serializable {

    private String nodeName;

    private String createTime;

    private String kernelVersion;

    private String kubeletVersion;

    private String kubeProxyVersion;

}
