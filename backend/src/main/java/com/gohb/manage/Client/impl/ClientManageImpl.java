package com.gohb.manage.Client.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gohb.manage.Client.ClientManage;
import com.gohb.params.bo.client.MyUserBO;
import com.gohb.params.bo.voucher.VoucherBO;
import com.gohb.params.exception.ClientException;
import com.gohb.service.client.MyUserService;
import com.gohb.service.voucher.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import java.util.Date;

@ManagedBean
public class ClientManageImpl implements ClientManage {

    @Autowired
    private MyUserService myUserService;

    @Autowired
    private VoucherService voucherService;

    @Override
    public Boolean useVoucher(String voucherCode, String email) throws ClientException{
        MyUserBO myUserBO = myUserService.getOne(new LambdaQueryWrapper<MyUserBO>()
                .eq(MyUserBO::getEmail, email));
        VoucherBO voucherBO = voucherService.getOne(new LambdaQueryWrapper<VoucherBO>()
                .eq(VoucherBO::getVoucherCode, voucherCode));
        if (voucherBO == null || voucherBO.getUserId() != null) {
            throw new ClientException("兑换码失效");
        }
        if (voucherBO.getExpireTime().before(new Date())) {
            throw new ClientException("兑换码已过期");
        }
        voucherBO.setUserId(myUserBO.getUserId());
        boolean save = voucherService.updateById(voucherBO);
        return save;
    }

}
