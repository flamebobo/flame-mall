package com.flame.mall.admin.dao;

import com.flame.mall.admin.dto.PmsProductAttributeCategoryItem;

import java.util.List;

/**
 * 自定义商品属性分类Dao
 *
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2020/12/21 11:34
 */
public interface PmsProductAttributeCategoryDao {
    /**
     * 获取商品属性分类，包括属性
     */
    List<PmsProductAttributeCategoryItem> getListWithAttr();
}
