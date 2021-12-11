package com.gohb.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * k8s deployment bo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class KubeDeploymentBO {

    String namespace;

    String name;

    String selfLink;

    Integer replicas;

    Map<String,String> labels;

    String restartPolicy;

    Integer unavilableReplicas;

    Integer avilableReplicas;
}
