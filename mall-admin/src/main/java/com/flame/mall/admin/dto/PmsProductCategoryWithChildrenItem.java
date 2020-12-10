package com.flame.mall.admin.dto;

import com.flame.mall.mbg.model.PmsProductCategory;
import lombok.Data;

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
 * @time: 2020/12/9 14:26
 */
@Data
public class PmsProductCategoryWithChildrenItem extends PmsProductCategory {

    private List<PmsProductCategory> children;

}
