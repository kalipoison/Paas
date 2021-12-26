package com.gohb.params.dto.kube;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

/**
 * k8s deployment dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class KubeDeploymentDTO implements Serializable {

    String namespace;

    String name;

    String selfLink;

    Integer replicas;

    Map<String,String> labels;

    String restartPolicy;

    Integer unavilableReplicas;

    Integer avilableReplicas;
}
