package com.gohb.manage.prod;

import com.gohb.params.bo.prod.ProdBO;
import com.gohb.params.bo.prod.ProdPropBO;
import com.gohb.params.dto.prod.ProdDTO;
import com.gohb.params.dto.prod.ProdPropDTO;

import java.util.List;

public interface ProdManage {

    /**
     * 新增商品
     * @param prodBO
     * @return
     */
    Boolean saveProd(ProdBO prodBO);

    /**
     * 根据id 删除商品
     * @param id
     * @return
     */
    Boolean deleteProd(Integer id);

    /**
     * 更新 商品
     * @param prodBO
     * @return
     */
    Boolean updateProd(ProdBO prodBO);

    /**
     * 查询所有商品，模糊查询
     * @return
     */
    List<ProdDTO> listProd(ProdBO prodBO);

}
