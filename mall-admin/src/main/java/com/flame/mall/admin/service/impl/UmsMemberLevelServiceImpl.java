package com.flame.mall.admin.service.impl;

import com.flame.mall.admin.service.UmsMemberLevelService;
import com.flame.mall.mbg.mapper.UmsMemberLevelMapper;
import com.flame.mall.mbg.model.UmsMemberLevel;
import com.flame.mall.mbg.model.UmsMemberLevelExample;
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
 * @time: 2020/12/10 11:53
 */
@Service
@RequiredArgsConstructor
public class UmsMemberLevelServiceImpl implements UmsMemberLevelService {

    private final UmsMemberLevelMapper memberLevelMapper;

    @Override
    public List<UmsMemberLevel> list(Integer defaultStatus) {
        UmsMemberLevelExample example = new UmsMemberLevelExample();
        example.createCriteria().andDefaultStatusEqualTo(defaultStatus);
        return memberLevelMapper.selectByExample(example);
    }
}
