package com.gohb.manage.Client;

import com.gohb.params.exception.ClientException;

public interface ClientManage {

    /**
     * 兑换 兑换券
     * @return
     */
    Boolean useVoucher(String voucherCode, String email) throws ClientException;

}
