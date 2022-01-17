package com.gohb.manage.Client;

import com.gohb.params.dto.client.ProdDetailDTO;
import com.gohb.params.exception.ClientException;

import java.util.List;

public interface ClientManage {

    /**
     * 兑换 兑换券
     * @return
     */
    Boolean useVoucher(String voucherCode, String email) throws ClientException;

    /**
     * 列出 产品详情
     * @return
     */
    List<ProdDetailDTO> listProd();
}
