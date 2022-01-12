package com.gohb.manage.voucher.impl;

import com.gohb.convert.BoToDtoUtils;
import com.gohb.manage.voucher.VoucherManage;
import com.gohb.params.bo.voucher.VoucherBO;
import com.gohb.params.dto.voucher.VoucherDTO;
import com.gohb.service.voucher.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class VoucherManageImpl implements VoucherManage {

    @Autowired
    private VoucherService voucherService;


    @Override
    public Boolean saveVoucher(VoucherBO voucherBO) {
        boolean save = voucherService.save(voucherBO);
        return save;
    }

    @Override
    public Boolean deleteVoucher(Integer id) {
        boolean delete = voucherService.removeById(id);
        return delete;
    }

    @Override
    public Boolean updateVoucher(VoucherBO voucherBO) {
        boolean update = voucherService.updateById(voucherBO);
        return update;
    }

    @Override
    public List<VoucherDTO> listVoucher(VoucherBO voucherBO) {
        List<VoucherBO> voucherBOS = voucherService.list();
        List<VoucherDTO> voucherDTOS = new ArrayList<>();
        for (VoucherBO voucher : voucherBOS) {
            voucherDTOS.add(BoToDtoUtils.VoucherBoTOVoucherDTO(voucher));
        }
        return voucherDTOS;
    }
}
