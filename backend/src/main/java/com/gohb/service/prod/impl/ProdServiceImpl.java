package com.gohb.service.prod.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gohb.params.bo.prod.ProdBO;
import com.gohb.service.prod.ProdService;
import com.gohb.mapper.prod.ProdMapper;
import org.springframework.stereotype.Service;

/**
* @author zsh
* @description 针对表【prod(商品)】的数据库操作Service实现
* @createDate 2022-01-12 01:33:21
*/
@Service
public class ProdServiceImpl extends ServiceImpl<ProdMapper, ProdBO>
    implements ProdService{

}




