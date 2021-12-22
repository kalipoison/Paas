package com.gohb.bo.kube;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * k8s namespace bo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class KubeNamespaceBO implements Serializable {

    String apiVersion;

    String kind;

    String name;

    String status;

    Date createTime;

}
