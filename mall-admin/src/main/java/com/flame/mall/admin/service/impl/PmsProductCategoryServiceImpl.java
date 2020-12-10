package com.flame.mall.admin.service.impl;

import com.flame.mall.admin.dao.PmsProductCategoryDao;
import com.flame.mall.admin.dto.PmsProductCategoryParam;
import com.flame.mall.admin.dto.PmsProductCategoryWithChildrenItem;
import com.flame.mall.admin.service.PmsProductCategoryService;
import com.flame.mall.mbg.model.PmsProductCategory;
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
 * @description:
 * @author: Flame.Lai
 * @time: 2020/12/9 14:28
 */
@Service
@RequiredArgsConstructor
public class PmsProductCategoryServiceImpl implements PmsProductCategoryService {

    private final PmsProductCategoryDao productCategoryDao;

    @Override
    public int create(PmsProductCategoryParam pmsProductCategoryParam) {
        return 0;
    }

    @Override
    public int update(Long id, PmsProductCategoryParam pmsProductCategoryParam) {
        return 0;
    }

    @Override
    public List<PmsProductCategory> getList(Long parentId, Integer pageSize, Integer pageNum) {
        return null;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public PmsProductCategory getItem(Long id) {
        return null;
    }

    @Override
    public int updateNavStatus(List<Long> ids, Integer navStatus) {
        return 0;
    }

    @Override
    public int updateShowStatus(List<Long> ids, Integer showStatus) {
        return 0;
    }

    @Override
    public List<PmsProductCategoryWithChildrenItem> listWithChildren() {
        return productCategoryDao.listWithChildren();
    }
}
