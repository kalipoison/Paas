package com.gohb.service.prod.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gohb.params.bo.prod.SkuBO;
import com.gohb.service.prod.SkuService;
import com.gohb.mapper.prod.SkuMapper;
import org.springframework.stereotype.Service;

/**
* @author zsh
* @description 针对表【sku(单品SKU表)】的数据库操作Service实现
* @createDate 2022-01-12 22:17:03
*/
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, SkuBO>
    implements SkuService{

}




