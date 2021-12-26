package com.gohb.params.request;

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
public class UpdateServiceRequest implements Serializable {

    private String serviceName;

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
