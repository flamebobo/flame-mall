package com.flame.mall.admin.service.impl;

import com.flame.mall.admin.service.CmsPrefrenceAreaService;
import com.flame.mall.mbg.mapper.CmsPrefrenceAreaMapper;
import com.flame.mall.mbg.model.CmsPrefrenceArea;
import com.flame.mall.mbg.model.CmsPrefrenceAreaExample;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品优选Service实现类 *
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2020/12/10 15:10
 */
@Service
@RequiredArgsConstructor
public class CmsPrefrenceAreaServiceImpl implements CmsPrefrenceAreaService {

    private final CmsPrefrenceAreaMapper prefrenceAreaMapper;

    @Override
    public List<CmsPrefrenceArea> listAll() {
        return prefrenceAreaMapper.selectByExample(new CmsPrefrenceAreaExample());
    }
}
