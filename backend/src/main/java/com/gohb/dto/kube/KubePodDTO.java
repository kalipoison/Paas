package com.gohb.dto.kube;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * k8s pod bo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class KubePodDTO {

    private String podName;

    private String namespace;

    private String createTime;

    private String restartPolicy;

    private String serviceAccount;

    private String status;

}
