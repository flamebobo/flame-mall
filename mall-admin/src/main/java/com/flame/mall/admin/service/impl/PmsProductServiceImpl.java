package com.flame.mall.admin.service.impl;

import com.flame.mall.admin.dao.CmsPrefrenceAreaProductRelationDao;
import com.flame.mall.admin.dao.CmsSubjectProductRelationDao;
import com.flame.mall.admin.dao.PmsMemberPriceDao;
import com.flame.mall.admin.dao.PmsProductAttributeValueDao;
import com.flame.mall.admin.dao.PmsProductFullReductionDao;
import com.flame.mall.admin.dao.PmsProductLadderDao;
import com.flame.mall.admin.dao.PmsSkuStockDao;
import com.flame.mall.admin.dto.PmsProductParam;
import com.flame.mall.admin.dto.PmsProductQueryParam;
import com.flame.mall.admin.dto.PmsProductResult;
import com.flame.mall.admin.service.PmsProductService;
import com.flame.mall.mbg.mapper.PmsProductMapper;
import com.flame.mall.mbg.model.PmsProduct;
import com.flame.mall.mbg.model.PmsProductExample;
import com.flame.mall.mbg.model.PmsSkuStock;
import com.flame.mall.util.CommonResult;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * @time: 2020/12/7 16:24
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PmsProductServiceImpl implements PmsProductService {

    private final PmsProductMapper pmsProductMapper;
    private final PmsMemberPriceDao memberPriceDao;
    private final PmsProductLadderDao productLadderDao;
    private final PmsProductFullReductionDao productFullReductionDao;
    private final PmsSkuStockDao skuStockDao;
    private final PmsProductAttributeValueDao productAttributeValueDao;
    private final CmsSubjectProductRelationDao subjectProductRelationDao;
    private final CmsPrefrenceAreaProductRelationDao prefrenceAreaProductRelationDao;

    @Override
    public List<PmsProduct> list(PmsProductQueryParam productQueryParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        PmsProductExample pmsProductExample = new PmsProductExample();
        PmsProductExample.Criteria criteria = pmsProductExample.createCriteria();
        criteria.andDeleteStatusEqualTo(0);
        if (productQueryParam.getPublishStatus() != null) {
            criteria.andPublishStatusEqualTo(productQueryParam.getPublishStatus());
        }
        if (productQueryParam.getVerifyStatus() != null) {
            criteria.andVerifyStatusEqualTo(productQueryParam.getVerifyStatus());
        }
        if (!StringUtils.isEmpty(productQueryParam.getKeyword())) {
            criteria.andNameLike("%" + productQueryParam.getKeyword() + "%");
        }
        if (!StringUtils.isEmpty(productQueryParam.getProductSn())) {
            criteria.andProductSnEqualTo(productQueryParam.getProductSn());
        }
        if (productQueryParam.getBrandId() != null) {
            criteria.andBrandIdEqualTo(productQueryParam.getBrandId());
        }
        if (productQueryParam.getProductCategoryId() != null) {
            criteria.andProductCategoryIdEqualTo(productQueryParam.getProductCategoryId());
        }

        return pmsProductMapper.selectByExample(pmsProductExample);
    }

    @Override
    public CommonResult create(PmsProductParam productParam) {
        try {
            // 创建商品
            PmsProduct product = productParam;
            product.setId(null);
            pmsProductMapper.insertSelective(product);
            // 根据促销类型设置价格：会员价格、阶梯价格、满减价格
            Long productId = product.getId();
            // 会员价格
            relateAndInsertList(memberPriceDao, productParam.getMemberPriceList(), productId);
            // 阶梯价格
            relateAndInsertList(productLadderDao, productParam.getProductLadderList(), productId);
            // 满减价格
            relateAndInsertList(productFullReductionDao, productParam.getProductFullReductionList(), productId);
            // 处理sku的编码
            handleSkuStockCode(productParam.getSkuStockList(),productId);
            // 添加sku库存信息
            relateAndInsertList(skuStockDao, productParam.getSkuStockList(), productId);
            // 添加商品参数,添加自定义商品规格
            relateAndInsertList(productAttributeValueDao, productParam.getProductAttributeValueList(), productId);
            // 关联专题
            relateAndInsertList(subjectProductRelationDao, productParam.getSubjectProductRelationList(), productId);
            // 关联优选
            relateAndInsertList(prefrenceAreaProductRelationDao, productParam.getPrefrenceAreaProductRelationList(), productId);
            return CommonResult.success(1);
        }catch (Exception e){
            log.error("新增商品报错:{}", e.getMessage());
            return CommonResult.failed();
        }

    }

    private void handleSkuStockCode(List<PmsSkuStock> skuStockList, Long productId) {
        if(CollectionUtils.isEmpty(skuStockList)) {
            return;
        }
        for (int i = 0; i < skuStockList.size(); i++) {
            PmsSkuStock skuStock = skuStockList.get(i);
            if (StringUtils.isEmpty(skuStock.getSkuCode())) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                StringBuilder sb = new StringBuilder();
                //日期
                sb.append(sdf.format(new Date()));
                //四位商品id
                sb.append(String.format("%04d", productId));
                //三位索引id
                sb.append(String.format("%03d", i + 1));
                skuStock.setSkuCode(sb.toString());
            }
        }
    }

    private void relateAndInsertList(Object dao, List dataList, Long productId) {
        try {
            if (CollectionUtils.isEmpty(dataList)) {
                return;
            }
            for (Object item : dataList) {
                Method setId = item.getClass().getMethod("setId", Long.class);
                setId.invoke(item, (Long) null);
                Method setProductId = item.getClass().getMethod("setProductId", Long.class);
                setProductId.invoke(item, productId);
            }
            Method insertList = dao.getClass().getMethod("insertList", List.class);
            insertList.invoke(dao, dataList);
        } catch (Exception e) {
            log.warn("创建产品出错:{}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public PmsProductResult getUpdateInfo(Long id) {
        return null;
    }

    @Override
    public int update(Long id, PmsProductParam productParam) {
        return 0;
    }

    @Override
    public int updateVerifyStatus(List<Long> ids, Integer verifyStatus, String detail) {
        return 0;
    }

    @Override
    public int updatePublishStatus(List<Long> ids, Integer publishStatus) {
        return 0;
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        return 0;
    }

    @Override
    public int updateNewStatus(List<Long> ids, Integer newStatus) {
        return 0;
    }

    @Override
    public int updateDeleteStatus(List<Long> ids, Integer deleteStatus) {
        return 0;
    }
}
