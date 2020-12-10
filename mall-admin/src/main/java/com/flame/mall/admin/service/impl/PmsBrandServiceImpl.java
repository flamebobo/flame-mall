package com.flame.mall.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.flame.mall.admin.dto.PmsBrandParam;
import com.flame.mall.admin.service.PmsBrandService;
import com.flame.mall.mbg.mapper.PmsBrandMapper;
import com.flame.mall.mbg.mapper.PmsProductMapper;
import com.flame.mall.mbg.model.PmsBrand;
import com.flame.mall.mbg.model.PmsBrandExample;
import com.flame.mall.mbg.model.PmsProduct;
import com.flame.mall.mbg.model.PmsProductExample;
import com.flame.mall.util.CommonResult;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
 * @time: 2020/12/9 16:53
 */
@Service
@RequiredArgsConstructor
public class PmsBrandServiceImpl implements PmsBrandService {

    private final PmsBrandMapper brandMapper;
    private final PmsProductMapper productMapper;

    @Override
    public List<PmsBrand> listAllBrand() {
        return null;
    }

    @Override
    public CommonResult createBrand(PmsBrandParam pmsBrandParam) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtil.copyProperties(pmsBrandParam, pmsBrand);
        // 如果创建时首字母为空，取名称的第一个为首字母
        if (StrUtil.isEmpty(pmsBrand.getFirstLetter())) {
            pmsBrand.setFirstLetter(pmsBrand.getName().substring(0, 1));
        }
        long count = brandMapper.insertSelective(pmsBrand);
        if (count==1) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult updateBrand(Long id, PmsBrandParam pmsBrandParam) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtil.copyProperties(pmsBrandParam, pmsBrand);
        pmsBrand.setId(id);
        // 如果创建时首字母为空，取名称的第一个为首字母
        if (StrUtil.isEmpty(pmsBrand.getFirstLetter())) {
            pmsBrand.setFirstLetter(pmsBrand.getName().substring(0, 1));
        }
        long count = brandMapper.updateByPrimaryKeySelective(pmsBrand);
        // 更新品牌时要更新商品中的品牌名称
        PmsProduct pmsProduct = new PmsProduct();
        pmsProduct.setBrandName(pmsBrand.getName());
        PmsProductExample pmsProductExample = new PmsProductExample();
        pmsProductExample.createCriteria().andBrandIdEqualTo(id);
        // TODO: 2020/12/10 为何在product表设计品牌名称这个字段，而不是通过外键关键品牌表
        productMapper.updateByExampleSelective(pmsProduct, pmsProductExample);
        if (count==1) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @Override
    public CommonResult deleteBrand(Long id) {
        long count = brandMapper.deleteByPrimaryKey(id);
        if (count == 1) {
            return CommonResult.success(1);
        } else {
            return CommonResult.failed();
        }
    }

    @Override
    public CommonResult deleteBrand(List<Long> ids) {
        PmsBrandExample pmsBrandExample = new PmsBrandExample();
        pmsBrandExample.createCriteria().andIdIn(ids);
        long count = brandMapper.deleteByExample(pmsBrandExample);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @Override
    public List<PmsBrand> listBrand(String keyword, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PmsBrandExample pmsBrandExample = new PmsBrandExample();
        pmsBrandExample.setOrderByClause("sort desc");
        PmsBrandExample.Criteria criteria = pmsBrandExample.createCriteria();
        if (!StrUtil.isEmpty(keyword)) {
            criteria.andNameLike("%" + keyword + "%");
        }
        return brandMapper.selectByExample(pmsBrandExample);
    }

    @Override
    public PmsBrand getBrand(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public CommonResult updateShowStatus(List<Long> ids, Integer showStatus) {
        PmsBrand pmsBrand = new PmsBrand();
        pmsBrand.setShowStatus(showStatus);
        PmsBrandExample pmsBrandExample = new PmsBrandExample();
        pmsBrandExample.createCriteria().andIdIn(ids);
        long count = brandMapper.updateByExampleSelective(pmsBrand, pmsBrandExample);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @Override
    public CommonResult updateFactoryStatus(List<Long> ids, Integer factoryStatus) {
        PmsBrand pmsBrand = new PmsBrand();
        pmsBrand.setFactoryStatus(factoryStatus);
        PmsBrandExample pmsBrandExample = new PmsBrandExample();
        pmsBrandExample.createCriteria().andIdIn(ids);
        long count = brandMapper.updateByExampleSelective(pmsBrand, pmsBrandExample);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }
}
