package com.flame.mall.admin.service.impl;

import com.flame.mall.admin.dto.PmsProductAttributeParam;
import com.flame.mall.admin.dto.ProductAttrInfo;
import com.flame.mall.admin.service.PmsProductAttributeService;
import com.flame.mall.mbg.model.PmsProductAttribute;
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
 * @time: 2020/12/14 14:29
 */
@Service
@RequiredArgsConstructor
public class PmsProductAttributeServiceImpl implements PmsProductAttributeService {


    @Override
    public List<PmsProductAttribute> getList(Long cid, Integer type, Integer pageSize, Integer pageNum) {
        return null;
    }

    @Override
    public int create(PmsProductAttributeParam pmsProductAttributeParam) {
        return 0;
    }

    @Override
    public int update(Long id, PmsProductAttributeParam productAttributeParam) {
        return 0;
    }

    @Override
    public PmsProductAttribute getItem(Long id) {
        return null;
    }

    @Override
    public int delete(List<Long> ids) {
        return 0;
    }

    @Override
    public List<ProductAttrInfo> getProductAttrInfo(Long productCategoryId) {
        return null;
    }
}
