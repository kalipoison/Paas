package com.gohb.controller;

import com.gohb.manage.KubeNodeManage;
import io.kubernetes.client.openapi.models.V1Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/node")
public class KubeNodeController {

    @Autowired
    private KubeNodeManage kubeNodeManage;


    @GetMapping()
    public List<V1Node> listNode() {
        List<V1Node> v1Nodes = kubeNodeManage.listNode();
        return v1Nodes;
    }

    @GetMapping("detail")
    public V1Node nodeDetail(@RequestParam("nodeName") String nodeName) {
        V1Node v1Node = kubeNodeManage.nodeDetail(nodeName);
        return v1Node;
    }

    @GetMapping("exist")
    public Boolean isExistNode(@RequestParam("nodeName") String nodeName) {
        Boolean exist = kubeNodeManage.isExistNode(nodeName);
        return exist;
    }

}
