package com.flame.mall.admin.dto;

import com.flame.mall.mbg.model.PmsProductAttribute;
import com.flame.mall.mbg.model.PmsProductAttributeCategory;
import lombok.Data;

import java.util.List;

/**
 * 包含有分类下属性的dto
 *
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2020/12/10 14:16
 */
@Data
public class PmsProductAttributeCategoryItem extends PmsProductAttributeCategory {

    private List<PmsProductAttribute> productAttributeList;

}
