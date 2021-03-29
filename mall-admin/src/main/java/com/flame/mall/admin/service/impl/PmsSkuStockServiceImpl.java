package com.flame.mall.admin.service.impl;

import com.flame.mall.admin.dao.PmsSkuStockDao;
import com.flame.mall.admin.service.PmsSkuStockService;
import com.flame.mall.mbg.mapper.PmsSkuStockMapper;
import com.flame.mall.mbg.model.PmsSkuStock;
import com.flame.mall.mbg.model.PmsSkuStockExample;
import com.flame.mall.util.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 商品sku库存管理Service实现类
 *
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2020/12/18 14:19
 */
@Service
@RequiredArgsConstructor
public class PmsSkuStockServiceImpl implements PmsSkuStockService {

    private final PmsSkuStockMapper skuStockMapper;

    private final PmsSkuStockDao skuStockDao;

    @Override
    public List<PmsSkuStock> getList(Long pid, String keyword) {
        PmsSkuStockExample example = new PmsSkuStockExample();
        PmsSkuStockExample.Criteria criteria = example.createCriteria().andProductIdEqualTo(pid);
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andSkuCodeLike("%" + keyword + "%");
        }
        return skuStockMapper.selectByExample(example);
    }

    @Override
    public CommonResult update(Long pid, List<PmsSkuStock> skuStockList) {
        int count = skuStockDao.replaceList(skuStockList);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }
}
