package com.gohb.params.bo.kube;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

/**
 * k8s deployment bo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class KubeDeploymentBO implements Serializable {

    String namespace;

    String name;

    String selfLink;

    Integer replicas;

    String matchLabels;

    String restartPolicy;

    Integer unavilableReplicas;

    Integer avilableReplicas;
}
