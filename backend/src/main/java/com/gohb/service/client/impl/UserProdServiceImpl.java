package com.gohb.service.client.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gohb.params.bo.client.UserProdBO;
import com.gohb.service.client.UserProdService;
import com.gohb.mapper.client.UserProdMapper;
import org.springframework.stereotype.Service;

/**
* @author zsh
* @description 针对表【user_prod】的数据库操作Service实现
* @createDate 2022-01-18 17:29:41
*/
@Service
public class UserProdServiceImpl extends ServiceImpl<UserProdMapper, UserProdBO>
    implements UserProdService{

}




