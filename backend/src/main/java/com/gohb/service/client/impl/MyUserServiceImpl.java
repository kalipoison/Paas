package com.gohb.service.client.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gohb.params.bo.client.MyUserBO;
import com.gohb.service.client.MyUserService;
import com.gohb.mapper.client.MyUserMapper;
import org.springframework.stereotype.Service;

/**
* @author zsh
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2022-01-16 23:28:23
*/
@Service
public class MyUserServiceImpl extends ServiceImpl<MyUserMapper, MyUserBO>
    implements MyUserService {

}




