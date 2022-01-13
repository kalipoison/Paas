package com.gohb.manage.prod;

import com.gohb.params.bo.prod.ProdPropBO;
import com.gohb.params.dto.prod.ProdPropDTO;


import java.util.List;

public interface ProdPropManage {

    /**
     * 新增商品属性名
     * @param prodPropBO
     * @return
     */
    Boolean saveProdProp(ProdPropBO prodPropBO);

    /**
     * 根据id 删除商品属性名
     * @param id
     * @return
     */
    Boolean deleteProdProp(Integer id);

    /**
     * 更新 商品属性名
     * @param prodPropBO
     * @return
     */
    Boolean updateProdProp(ProdPropBO prodPropBO);

    /**
     * 查询所有商品属性名，模糊查询
     * @return
     */
    List<ProdPropDTO> listProdProp(ProdPropBO prodPropBO);


    /**
     * 查询 商品属性详情
     * @param propId
     * @return
     */
    ProdPropDTO prodPropDetail(Integer propId);

}
