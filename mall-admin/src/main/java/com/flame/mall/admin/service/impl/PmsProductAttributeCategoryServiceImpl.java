package com.flame.mall.admin.service.impl;

import com.flame.mall.admin.dao.PmsProductAttributeCategoryDao;
import com.flame.mall.admin.dto.PmsProductAttributeCategoryItem;
import com.flame.mall.admin.service.PmsProductAttributeCategoryService;
import com.flame.mall.mbg.mapper.PmsProductAttributeCategoryMapper;
import com.flame.mall.mbg.model.PmsProductAttributeCategory;
import com.flame.mall.mbg.model.PmsProductAttributeCategoryExample;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:testtest
 * @author: Flame.Lai
 * @time: 2020/12/10 14:17
 */
@Service
@RequiredArgsConstructor
public class PmsProductAttributeCategoryServiceImpl implements PmsProductAttributeCategoryService {

    private final PmsProductAttributeCategoryMapper productAttributeCategoryMapper;
    private final PmsProductAttributeCategoryDao productAttributeCategoryDao;

    @Override
    public int create(String name) {
        PmsProductAttributeCategory productAttributeCategory = new PmsProductAttributeCategory();
        productAttributeCategory.setName(name);
        return productAttributeCategoryMapper.insertSelective(productAttributeCategory);
    }

    @Override
    public int update(Long id, String name) {
        PmsProductAttributeCategory productAttributeCategory = new PmsProductAttributeCategory();
        productAttributeCategory.setName(name);
        productAttributeCategory.setId(id);
        return productAttributeCategoryMapper.updateByPrimaryKeySelective(productAttributeCategory);
    }

    @Override
    public int delete(Long id) {
        return productAttributeCategoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PmsProductAttributeCategory getItem(Long id) {
        return productAttributeCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PmsProductAttributeCategory> getList(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        return productAttributeCategoryMapper.selectByExample(new PmsProductAttributeCategoryExample());
    }

    @Override
    public List<PmsProductAttributeCategoryItem> getListWithAttr() {
        return productAttributeCategoryDao.getListWithAttr();
    }
}
