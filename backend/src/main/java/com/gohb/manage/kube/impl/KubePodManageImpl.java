package com.gohb.manage.kube.impl;

import com.gohb.params.bo.kube.KubePodBO;
import com.gohb.params.bo.kube.KubePodDetailBO;
import com.gohb.convert.BoToDtoUtils;
import com.gohb.params.dto.kube.KubePodDTO;
import com.gohb.params.dto.kube.KubePodDetailDTO;
import com.gohb.manage.kube.KubePodManage;
import com.gohb.params.request.kube.CreatePodRequest;
import com.gohb.service.kube.KubePodService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class KubePodManageImpl implements KubePodManage {

    @Autowired
    private KubePodService kubePodService;

    @Override
    public List<KubePodDTO> listPod(String namespace) {
        List<KubePodBO> kubePodBOS = kubePodService.listPod(namespace);
        List<KubePodDTO> kubePodDTOS = new ArrayList<>();
        for (KubePodBO kubePodBO : kubePodBOS) {
            kubePodDTOS.add(BoToDtoUtils.kubePodBOTOKubePodDTO(kubePodBO));
        }
        return kubePodDTOS;
    }

    @Override
    public KubePodDTO deletePod(String namespace, String podName) {
        KubePodBO kubePodBO = kubePodService.deletePod(namespace, podName);
        KubePodDTO kubePodDTO = BoToDtoUtils.kubePodBOTOKubePodDTO(kubePodBO);
        return kubePodDTO;
    }

    @Override
    public KubePodDetailDTO podDetail(String namespace, String podName) {
        KubePodDetailBO kubePodDetailBO = kubePodService.podDetail(namespace, podName);
        KubePodDetailDTO kubePodDetailDTO = BoToDtoUtils.kubePodDetailBOTOKubePodDetailDTO(kubePodDetailBO);
        return kubePodDetailDTO;
    }

    @Override
    public String podDetailYaml(String namespace, String podName) {
        return kubePodService.podDetailYaml(namespace, podName);
    }

    @Override
    public KubePodDTO createPod(CreatePodRequest createPodRequest) {
        KubePodBO kubePodBO = kubePodService.createPod(createPodRequest);
        KubePodDTO kubePodDTO = BoToDtoUtils.kubePodBOTOKubePodDTO(kubePodBO);
        return kubePodDTO;
    }
}
