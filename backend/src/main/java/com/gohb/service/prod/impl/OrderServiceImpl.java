package com.gohb.service.prod.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gohb.params.bo.prod.OrderBO;
import com.gohb.service.prod.OrderService;
import com.gohb.mapper.prod.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author zsh
* @description 针对表【order(订单表)】的数据库操作Service实现
* @createDate 2022-01-19 22:28:49
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderBO>
    implements OrderService{

}




