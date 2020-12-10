package com.flame.mall.admin.dao;

import com.flame.mall.admin.dto.PmsProductCategoryWithChildrenItem;

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
 * @time: 2020/12/9 16:15
 */
public interface PmsProductCategoryDao {

    /**
     * 获取商品分类包括子分类
     */
    List<PmsProductCategoryWithChildrenItem> listWithChildren();
}
