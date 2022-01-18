package com.gohb.controller;

import com.gohb.constant.StatusCodeConstant;
import com.gohb.manage.Client.ClientManage;
import com.gohb.manage.Client.MyUserManage;
import com.gohb.params.bo.client.MyUserBO;
import com.gohb.params.dto.Result;
import com.gohb.params.dto.ResultUtils;
import com.gohb.params.dto.client.ProdDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private MyUserManage myUserManage;

    @Autowired
    private ClientManage clientManage;



    @PostMapping("/register")
    public Result register(MyUserBO myUserBO) {
        Boolean register = myUserManage.saveMyUser(myUserBO);
        if (!register) {
            return ResultUtils.getFailedResult(StatusCodeConstant.createFail, "注册失败");
        }
        return ResultUtils.getSuccessResult("注册成功");
    }

    @PostMapping("/voucher")
    public Result voucherManage(String voucherCode, String email) {
        Boolean use = null;
        try {
            use = clientManage.useVoucher(voucherCode.replace("-",""), email);
        } catch (Exception e) {
            return ResultUtils.getFailedResult(StatusCodeConstant.createFail, e.getMessage());
        }
        if (use == null || !use) {
            return ResultUtils.getFailedResult(StatusCodeConstant.createFail, "兑换券使用失败");
        }
        return ResultUtils.getSuccessResult("兑换券使用成功");
    }

    @GetMapping("/products")
    public Result getProdDetailList() {
        List<ProdDetailDTO> prodDetailDTOS = clientManage.listProd();
        return ResultUtils.getSuccessResult(prodDetailDTOS);
    }

}
