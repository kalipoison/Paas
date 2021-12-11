package com.gohb.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class KubeServiceBO {

    String apiVersion;

    String kind;

    Date createTime;

    String namespace;

    String name;

    String selfLink;

    String type;

    String clusterIP;

    List<String> externalIPs;

    List<KubeServicePortBO> ports;

}
