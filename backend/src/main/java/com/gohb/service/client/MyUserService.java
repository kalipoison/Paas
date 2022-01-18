package com.gohb.service.client;

import com.gohb.params.bo.client.MyUserBO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author zsh
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2022-01-16 23:28:23
*/
public interface MyUserService extends IService<MyUserBO> {

    MyUserBO getByName(String username);

}
