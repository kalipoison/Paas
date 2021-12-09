package com.gohb.controller;

import com.gohb.manage.KubeNamespaceManage;
import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.openapi.models.V1Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("namespace")
public class KubeNamespaceController {

    @Autowired
    private KubeNamespaceManage kubeNamespaceManage;

    @PostMapping("")
    public V1Namespace createNamespace(@RequestParam("namespace") String namespace) {
        // 已存在namespace，不允许创建
        if (kubeNamespaceManage.isExistNamespace(namespace)) {
            return null;
        }
        V1Namespace v1Namespace = kubeNamespaceManage.createNamespace(namespace);
        return v1Namespace;
    }

    @DeleteMapping("")
    public V1Status deleteNamespace(@RequestParam("namespace") String namespace) {
        if (!kubeNamespaceManage.isExistNamespace(namespace)) {
            return null;
        }
        V1Status v1Status = kubeNamespaceManage.deleteNamespace(namespace);
        return v1Status;
    }

    @GetMapping("")
    public List<V1Namespace> listNamespace() {
        List<V1Namespace> v1NamespaceList = kubeNamespaceManage.listNamespace();
        return v1NamespaceList;
    }

    @GetMapping("exist")
    public Boolean isExistNamespace(String namespace) {
        Boolean exist = kubeNamespaceManage.isExistNamespace(namespace);
        return exist;
    }

    @GetMapping("detail")
    public V1Namespace namespaceDetail(String namespace) {
        V1Namespace v1Namespace = kubeNamespaceManage.namespaceDetail(namespace);
        return v1Namespace;
    }

}
