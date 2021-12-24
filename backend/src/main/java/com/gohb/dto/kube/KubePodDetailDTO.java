package com.gohb.dto.kube;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * k8s pod detail bo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class KubePodDetailDTO {

    private String apiVersion;

    private String kind;

    private String metadataName;

    private String metadataLabelsApp;

    private List<KubePodContainerDTO> kubePodContainerBOList;

}
