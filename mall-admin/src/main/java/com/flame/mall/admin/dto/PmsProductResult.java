package com.flame.mall.admin.dto;

import lombok.Data;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2020/12/9 17:24
 */
@Data
public class PmsProductResult extends PmsProductParam {

    //商品所选分类的父id
    private Long cateParentId;

}
