package com.flame.mall.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.flame.mall.admin.dao.CmsPrefrenceAreaProductRelationDao;
import com.flame.mall.admin.dao.CmsSubjectProductRelationDao;
import com.flame.mall.admin.dao.PmsMemberPriceDao;
import com.flame.mall.admin.dao.PmsProductAttributeValueDao;
import com.flame.mall.admin.dao.PmsProductDao;
import com.flame.mall.admin.dao.PmsProductFullReductionDao;
import com.flame.mall.admin.dao.PmsProductLadderDao;
import com.flame.mall.admin.dao.PmsProductVertifyRecordDao;
import com.flame.mall.admin.dao.PmsSkuStockDao;
import com.flame.mall.admin.dto.PmsProductParam;
import com.flame.mall.admin.dto.PmsProductQueryParam;
import com.flame.mall.admin.dto.PmsProductResult;
import com.flame.mall.admin.service.PmsProductService;
import com.flame.mall.mbg.mapper.CmsPrefrenceAreaProductRelationMapper;
import com.flame.mall.mbg.mapper.CmsSubjectProductRelationMapper;
import com.flame.mall.mbg.mapper.PmsMemberPriceMapper;
import com.flame.mall.mbg.mapper.PmsProductAttributeCategoryMapper;
import com.flame.mall.mbg.mapper.PmsProductAttributeValueMapper;
import com.flame.mall.mbg.mapper.PmsProductFullReductionMapper;
import com.flame.mall.mbg.mapper.PmsProductLadderMapper;
import com.flame.mall.mbg.mapper.PmsProductMapper;
import com.flame.mall.mbg.mapper.PmsSkuStockMapper;
import com.flame.mall.mbg.model.CmsPrefrenceAreaProductRelationExample;
import com.flame.mall.mbg.model.CmsSubjectProductRelationExample;
import com.flame.mall.mbg.model.PmsMemberPriceExample;
import com.flame.mall.mbg.model.PmsProduct;
import com.flame.mall.mbg.model.PmsProductAttributeValueExample;
import com.flame.mall.mbg.model.PmsProductExample;
import com.flame.mall.mbg.model.PmsProductFullReductionExample;
import com.flame.mall.mbg.model.PmsProductLadderExample;
import com.flame.mall.mbg.model.PmsProductVertifyRecord;
import com.flame.mall.mbg.model.PmsSkuStock;
import com.flame.mall.mbg.model.PmsSkuStockExample;
import com.flame.mall.util.CommonResult;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    private final PmsProductDao productDao;
    private final PmsMemberPriceMapper memberPriceMapper;
    private final PmsProductLadderMapper productLadderMapper;
    private final PmsProductFullReductionMapper productFullReductionMapper;
    private final PmsSkuStockMapper skuStockMapper;
    private final CmsPrefrenceAreaProductRelationMapper prefrenceAreaProductRelationMapper;
    private final CmsSubjectProductRelationMapper subjectProductRelationMapper;
    private final PmsProductAttributeValueMapper productAttributeValueMapper;
    private final PmsProductVertifyRecordDao productVertifyRecordDao;
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
        return productDao.getUpdateInfo(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult update(Long id, PmsProductParam productParam) {
        try{
            // 更新商品信息
            productParam.setId(id);
            pmsProductMapper.updateByPrimaryKeySelective(productParam);
            // 会员价格
            PmsMemberPriceExample pmsMemberPriceExample = new PmsMemberPriceExample();
            pmsMemberPriceExample.createCriteria().andProductIdEqualTo(id);
            memberPriceMapper.deleteByExample(pmsMemberPriceExample);
            relateAndInsertList(memberPriceDao, productParam.getMemberPriceList(), id);
            // 阶梯价格
            PmsProductLadderExample pmsProductLadderExample = new PmsProductLadderExample();
            pmsProductLadderExample.createCriteria().andProductIdEqualTo(id);
            productLadderMapper.deleteByExample(pmsProductLadderExample);
            relateAndInsertList(productLadderDao, productParam.getProductLadderList(), id);
            // 满减价格
            PmsProductFullReductionExample pmsProductFullReductionExample = new PmsProductFullReductionExample();
            pmsProductFullReductionExample.createCriteria().andProductIdEqualTo(id);
            productFullReductionMapper.deleteByExample(pmsProductFullReductionExample);
            relateAndInsertList(productFullReductionDao, productParam.getProductFullReductionList(), id);
            // 修改sku库存信息
            handleUpdateSkuStockList(id, productParam);
            // 修改商品参数，添加自定义商品规格
            PmsProductAttributeValueExample productAttributeValueExample = new PmsProductAttributeValueExample();
            productAttributeValueExample.createCriteria().andProductIdEqualTo(id);
            productAttributeValueMapper.deleteByExample(productAttributeValueExample);
            relateAndInsertList(productAttributeValueDao, productParam.getProductAttributeValueList(), id);
            // 关联专题
            CmsSubjectProductRelationExample subjectProductRelationExample = new CmsSubjectProductRelationExample();
            subjectProductRelationExample.createCriteria().andProductIdEqualTo(id);
            subjectProductRelationMapper.deleteByExample(subjectProductRelationExample);
            relateAndInsertList(subjectProductRelationDao, productParam.getSubjectProductRelationList(), id);
            // 关联优选
            CmsPrefrenceAreaProductRelationExample prefrenceAreaExample = new CmsPrefrenceAreaProductRelationExample();
            prefrenceAreaExample.createCriteria().andProductIdEqualTo(id);
            prefrenceAreaProductRelationMapper.deleteByExample(prefrenceAreaExample);
            relateAndInsertList(prefrenceAreaProductRelationDao, productParam.getPrefrenceAreaProductRelationList(), id);
            return CommonResult.success(1);
        }catch (Exception e){
            log.error("编辑商品报错:{}", e.getMessage());
            return CommonResult.failed();
        }
    }

    /**
     * 修改sku库存信息
     */
    private void handleUpdateSkuStockList(Long id, PmsProductParam productParam) {
        // 当前的sku信息
        List<PmsSkuStock> currSkuList = productParam.getSkuStockList();
        // 当前没有sku直接删除
        if(CollUtil.isEmpty(currSkuList)){
            PmsSkuStockExample skuStockExample = new PmsSkuStockExample();
            skuStockExample.createCriteria().andProductIdEqualTo(id);
            skuStockMapper.deleteByExample(skuStockExample);
            return;
        }
        // 获取初始sku信息
        PmsSkuStockExample skuStockExample = new PmsSkuStockExample();
        skuStockExample.createCriteria().andProductIdEqualTo(id);
        List<PmsSkuStock> oriStuList = skuStockMapper.selectByExample(skuStockExample);
        // 获取新增sku信息
        List<PmsSkuStock> insertSkuList = currSkuList.stream().filter(item -> item.getId()==null).collect(Collectors.toList());
        // 获取需要更新的sku信息
        List<PmsSkuStock> updateSkuList = currSkuList.stream().filter(item -> item.getId()!=null).collect(Collectors.toList());
        List<Long> updateSkuIds = updateSkuList.stream().map(PmsSkuStock::getId).collect(Collectors.toList());
        // 获取需要删除的sku信息
        List<PmsSkuStock> removeSkuList = oriStuList.stream().filter(item-> !updateSkuIds.contains(item.getId())).collect(Collectors.toList());
        handleSkuStockCode(insertSkuList,id);
        handleSkuStockCode(updateSkuList,id);
        // 新增sku
        if(CollUtil.isNotEmpty(insertSkuList)){
            relateAndInsertList(skuStockDao, insertSkuList, id);
        }
        String test= "123213";
        String test1="12312";
        if (test!=test1) {

        }
        // 删除sku
        if(CollUtil.isNotEmpty(removeSkuList)){
            List<Long> removeSkuIds = removeSkuList.stream().map(PmsSkuStock::getId).collect(Collectors.toList());
            PmsSkuStockExample removeExample = new PmsSkuStockExample();
            removeExample.createCriteria().andIdIn(removeSkuIds);
            skuStockMapper.deleteByExample(removeExample);
        }
        // 修改sku
        if(CollUtil.isNotEmpty(updateSkuList)){
            updateSkuList.forEach(skuStockMapper::updateByPrimaryKeySelective);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult updateVerifyStatus(List<Long> ids, Integer verifyStatus, String detail) {
        PmsProduct product = new PmsProduct();
        product.setVerifyStatus(verifyStatus);
        PmsProductExample example = new PmsProductExample();
        example.createCriteria().andIdIn(ids);
        List<PmsProductVertifyRecord> list = new ArrayList<>();
        int count = pmsProductMapper.updateByExampleSelective(product, example);
        //修改完审核状态后插入审核记录
        for (Long id : ids) {
            PmsProductVertifyRecord record = new PmsProductVertifyRecord();
            record.setProductId(id);
            record.setCreateTime(new Date());
            record.setDetail(detail);
            record.setStatus(verifyStatus);
            record.setVertifyMan("test");
            list.add(record);
        }
        productVertifyRecordDao.insertList(list);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @Override
    public CommonResult updatePublishStatus(List<Long> ids, Integer publishStatus) {
        PmsProduct record = new PmsProduct();
        record.setPublishStatus(publishStatus);
        PmsProductExample example = new PmsProductExample();
        example.createCriteria().andIdIn(ids);
        int count = pmsProductMapper.updateByExampleSelective(record, example);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @Override
    public CommonResult updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        PmsProduct record = new PmsProduct();
        record.setRecommandStatus(recommendStatus);
        PmsProductExample example = new PmsProductExample();
        example.createCriteria().andIdIn(ids);
        int count =  pmsProductMapper.updateByExampleSelective(record, example);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @Override
    public CommonResult updateNewStatus(List<Long> ids, Integer newStatus) {
        PmsProduct record = new PmsProduct();
        record.setNewStatus(newStatus);
        PmsProductExample example = new PmsProductExample();
        example.createCriteria().andIdIn(ids);
        int count = pmsProductMapper.updateByExampleSelective(record, example);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @Override
    public CommonResult updateDeleteStatus(List<Long> ids, Integer deleteStatus) {
        PmsProduct record = new PmsProduct();
        record.setDeleteStatus(deleteStatus);
        PmsProductExample example = new PmsProductExample();
        example.createCriteria().andIdIn(ids);
        int count =  pmsProductMapper.updateByExampleSelective(record, example);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @Override
    public List<PmsProduct> list(String keyword) {
        PmsProductExample productExample = new PmsProductExample();
        PmsProductExample.Criteria criteria = productExample.createCriteria();
        criteria.andDeleteStatusEqualTo(0);
        if(!StringUtils.isEmpty(keyword)){
            criteria.andNameLike("%" + keyword + "%");
            productExample.or().andDeleteStatusEqualTo(0).andProductSnLike("%" + keyword + "%");
        }
        return pmsProductMapper.selectByExample(productExample);
    }
}
