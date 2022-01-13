package com.gohb.service.prod.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gohb.params.bo.prod.ProductBO;
import com.gohb.service.prod.ProductService;
import com.gohb.mapper.prod.ProductMapper;
import org.springframework.stereotype.Service;

/**
* @author zsh
* @description 针对表【prod(商品)】的数据库操作Service实现
* @createDate 2022-01-12 01:33:21
*/
@Service
public class ProdServiceImpl extends ServiceImpl<ProductMapper, ProductBO>
    implements ProductService {

}




