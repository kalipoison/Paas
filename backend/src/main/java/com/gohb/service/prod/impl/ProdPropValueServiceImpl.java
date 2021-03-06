package com.gohb.service.prod.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gohb.params.bo.prod.ProdPropValueBO;
import com.gohb.service.prod.ProdPropValueService;
import com.gohb.mapper.prod.ProdPropValueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author zsh
* @description 针对表【prod_prop_value】的数据库操作Service实现
* @createDate 2022-01-10 23:18:47
*/
@Service
public class ProdPropValueServiceImpl extends ServiceImpl<ProdPropValueMapper, ProdPropValueBO>
    implements ProdPropValueService{

    @Autowired
    private ProdPropValueMapper prodPropValueMapper;

    @Override
    public Boolean deleteByProdId(Long prodId) {
        int delete = prodPropValueMapper.delete(new LambdaQueryWrapper<ProdPropValueBO>()
                .eq(ProdPropValueBO::getPropId, prodId));
        return delete > 0;
    }
}




