package com.gohb.params.dto.client;

import lombok.Data;

import java.util.List;

@Data
public class ProdDetailDTO {

    private Long productId;

    private String prodName;

    private String price;

    private List<String> props;

}
