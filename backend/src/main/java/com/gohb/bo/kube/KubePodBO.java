package com.gohb.bo.kube;

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
public class KubePodBO {

    private String podName;

    private String namespace;

    private String createTime;

    private String restartPolicy;

    private String serviceAccount;

    private String status;

}
