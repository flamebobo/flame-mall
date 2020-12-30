package com.flame.mall.admin.service.impl;

import com.flame.mall.admin.service.OmsOrderSettingService;
import com.flame.mall.mbg.mapper.OmsOrderMapper;
import com.flame.mall.mbg.mapper.OmsOrderSettingMapper;
import com.flame.mall.mbg.model.OmsOrder;
import com.flame.mall.mbg.model.OmsOrderSetting;
import com.flame.mall.util.Global;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>Title: 订单设置管理Service实现类</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2020/12/29 0:10
 */
@Service
@RequiredArgsConstructor
public class OmsOrderSettingServiceImpl implements OmsOrderSettingService {

    private final StringRedisTemplate stringRedisTemplate;
    private final OmsOrderSettingMapper orderSettingMapper;
    private final OmsOrderMapper orderMapper;

    @Override
    public OmsOrderSetting getItem(Long id) {
        return orderSettingMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Long id, OmsOrderSetting orderSetting) {
        orderSetting.setId(id);
//        // 存入redis
//        // 秒杀订单超时关闭时间
//        // 查询秒杀的订单
//
//        omsOrder.setOrderType(Global.OrderType.SPIKE.getValue());
//        orderMapper.selectByExample(omsOrder);
//        stringRedisTemplate.opsForValue().set();
        return orderSettingMapper.updateByPrimaryKey(orderSetting);
    }
}
