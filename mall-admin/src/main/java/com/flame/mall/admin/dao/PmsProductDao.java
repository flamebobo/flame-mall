package com.flame.mall.admin.dao;

import com.flame.mall.admin.dto.PmsProductResult;
import org.apache.ibatis.annotations.Param;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2020/12/11 10:28
 */
public interface PmsProductDao {

    /**
     * 获取商品编辑信息
     */
    PmsProductResult getUpdateInfo(@Param("id") Long id);
}
