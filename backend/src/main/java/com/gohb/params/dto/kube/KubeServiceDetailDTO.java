package com.gohb.params.dto.kube;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class KubeServiceDetailDTO implements Serializable {

    private String apiVersion;

    private String namespace;

    private String metadataName;

    private String specSelectorApp;

    private String specProtocol;

    private String specPort;

    private String specTargetPort;

    private String externalIP;

    private String type;
}
