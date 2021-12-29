package com.gohb.params.dto.kube;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class KubePodContainerDTO {

    private String name;

    private String image;

    private List<Integer> ports;

    private String limitsCPU;

    private String requestsCPU;

    private String limitsMemory;

    private String requestsMemory;

}
