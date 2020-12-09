package com.flame.mall.admin.dto;

import com.flame.mall.mbg.model.PmsBrand;
import com.flame.mall.mbg.model.PmsProductCategory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 商品列表查询参数dto
 *
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2020/12/7 16:14
 */
@Data
public class PmsProductQueryParam {

    @ApiModelProperty("商品名称")
    private String name;
    @ApiModelProperty("商品货号")
    private String productSn;
    @ApiModelProperty("上架状态")
    private Integer publishStatus;
    @ApiModelProperty("审核状态")
    private Integer verifyStatus;
    @ApiModelProperty("商品品牌Id")
    private Long brandId;
    @ApiModelProperty("商品分类Id")
    private Long productCategoryId;
    @ApiModelProperty("商品名称模糊搜索")
    private String keyword;

}
