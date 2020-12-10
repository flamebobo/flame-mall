package com.flame.mall.admin.dto;

import com.flame.mall.mbg.model.CmsPrefrenceAreaProductRelation;
import com.flame.mall.mbg.model.CmsSubjectProductRelation;
import com.flame.mall.mbg.model.PmsMemberPrice;
import com.flame.mall.mbg.model.PmsProduct;
import com.flame.mall.mbg.model.PmsProductAttributeValue;
import com.flame.mall.mbg.model.PmsProductFullReduction;
import com.flame.mall.mbg.model.PmsProductLadder;
import com.flame.mall.mbg.model.PmsSkuStock;
import io.swagger.annotations.ApiModelProperty;
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
 * @time: 2020/12/9 17:17
 */
@Data
public class PmsProductParam extends PmsProduct {

    @ApiModelProperty("商品阶梯价格设置")
    private List<PmsProductLadder> productLadderList;
    @ApiModelProperty("商品满减价格设置")
    private List<PmsProductFullReduction> productFullReductionList;
    @ApiModelProperty("商品会员价格设置")
    private List<PmsMemberPrice> memberPriceList;
    @ApiModelProperty("商品的sku库存信息")
    private List<PmsSkuStock> skuStockList;
    @ApiModelProperty("商品参数及自定义规格属性")
    private List<PmsProductAttributeValue> productAttributeValueList;
    @ApiModelProperty("专题和商品关系")
    private List<CmsSubjectProductRelation> subjectProductRelationList;
    @ApiModelProperty("优选专区和商品的关系")
    private List<CmsPrefrenceAreaProductRelation> prefrenceAreaProductRelationList;
}
