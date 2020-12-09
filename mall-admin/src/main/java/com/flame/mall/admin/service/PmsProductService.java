package com.flame.mall.admin.service;

import com.flame.mall.admin.dto.PmsProductQueryParam;
import com.flame.mall.mbg.model.PmsProduct;

import java.util.List;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2020/12/7 16:23
 */
public interface PmsProductService {

    /**
     * 分页查询商品
     */
    List<PmsProduct> list(PmsProductQueryParam productQueryParam, Integer pageSize, Integer pageNum);
}
