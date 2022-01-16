package com.gohb.manage.voucher.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gohb.convert.BoToDtoUtils;
import com.gohb.convert.DateTimeUtils;
import com.gohb.manage.voucher.VoucherManage;
import com.gohb.params.bo.sys.SysUserBO;
import com.gohb.params.bo.voucher.VoucherBO;
import com.gohb.params.dto.voucher.VoucherDTO;
import com.gohb.service.sys.SysUserService;
import com.gohb.service.voucher.VoucherService;
import com.gohb.utils.VoucherUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import java.util.*;
import java.util.stream.Collectors;

@ManagedBean
public class VoucherManageImpl implements VoucherManage {

    @Autowired
    private VoucherService voucherService;

    @Autowired
    private SysUserService sysUserService;


    @Override
    public Boolean saveVoucher(String username, Integer num) {
        if (num == null || num <= 0) {
            num = 5;
        }
        SysUserBO sysUserBO = sysUserService.getOne(new LambdaQueryWrapper<SysUserBO>()
                .eq(SysUserBO::getUsername, username));
        List<VoucherBO> voucherBOS = new ArrayList<>();
        for (int i = 0 ; i < num ; i++) {
            VoucherBO voucherBO = new VoucherBO();
            voucherBO.setCreateTime(new Date());
            voucherBO.setStatus(0);
            voucherBO.setExpireTime(getExpireTime(new Date()));
            voucherBO.setUserId(sysUserBO.getUserId());
            voucherBO.setVoucherCode(VoucherUtil.generateNewCode());
            voucherBOS.add(voucherBO);
        }
        boolean save = voucherService.saveBatch(voucherBOS);
        return save;
    }

    private Date getExpireTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);   //设置当前时间
        cal.add(Calendar.YEAR, 1);
        return new Date(cal.getTimeInMillis());
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
        Map<Long, SysUserBO> idsMap = new HashMap<>();
        voucherBOS.forEach(v -> idsMap.put(v.getUserId(), new SysUserBO()));
        List<SysUserBO> sysUserBOS = sysUserService.list(new LambdaQueryWrapper<SysUserBO>()
                .in(SysUserBO::getUserId, new ArrayList<>(idsMap.keySet())));
        sysUserBOS.forEach(sysUserBO -> idsMap.put(sysUserBO.getUserId(), sysUserBO));
        List<VoucherDTO> voucherDTOS = new ArrayList<>();
        for (VoucherBO voucher : voucherBOS) {
            VoucherDTO voucherDTO = BoToDtoUtils.VoucherBoTOVoucherDTO(voucher);
            voucherDTO.setUsername(idsMap.get(voucher.getUserId()).getUsername());
            voucherDTO.setCreateTime(DateTimeUtils.DateToString(voucher.getCreateTime()));
            voucherDTO.setExpireTime(DateTimeUtils.DateToString(voucher.getExpireTime()));
            voucherDTOS.add(voucherDTO);
        }
        return voucherDTOS;
    }
}
