package com.gohb.manage.voucher;

import com.gohb.params.bo.voucher.VoucherBO;
import com.gohb.params.dto.voucher.VoucherDTO;

import java.util.List;

public interface VoucherManage {

    /**
     * 新增 兑换券
     * @param voucherBO
     * @return
     */
    Boolean saveVoucher(VoucherBO voucherBO);

    /**
     * 根据id 删除 兑换券
     * @param id
     * @return
     */
    Boolean deleteVoucher(Integer id);

    /**
     * 更新 兑换券
     * @param voucherBO
     * @return
     */
    Boolean updateVoucher(VoucherBO voucherBO);

    /**
     * 查询所有 兑换券，模糊查询
     * @return
     */
    List<VoucherDTO> listVoucher(VoucherBO voucherBO);


}
