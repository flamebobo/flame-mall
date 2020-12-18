package com.flame.mall.admin.service;

import com.flame.mall.mbg.model.PmsSkuStock;
import com.flame.mall.util.CommonResult;

import java.util.List;

/**
 * sku商品库存管理Service
 *
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2020/12/18 14:17
 */
public interface PmsSkuStockService {

    /**
     * 根据产品id和skuCode模糊搜索
     */
    List<PmsSkuStock> getList(Long pid, String keyword);

    /**
     * 批量更新商品库存信息
     */
    CommonResult update(Long pid, List<PmsSkuStock> skuStockList);

}
