package com.gohb.dto.kube;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KubeNodeDTO {

    private String nodeName;

    private String createTime;

    private String kernelVersion;

    private String kubeletVersion;

    private String kubeProxyVersion;

}
