package com.gohb.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * k8s servicePort bo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class KubeServicePortBO implements Serializable {
    private String appProtocol;
    private String name;
    private String nodePort;
    private String port;
    private String protocol;
    private String targetPort;
}


