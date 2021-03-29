package com.flame.mall.admin.service.impl;

import com.flame.mall.admin.service.OmsOrderSettingService;
import com.flame.mall.mbg.mapper.OmsOrderMapper;
import com.flame.mall.mbg.mapper.OmsOrderSettingMapper;
import com.flame.mall.mbg.model.OmsOrder;
import com.flame.mall.mbg.model.OmsOrderExample;
import com.flame.mall.mbg.model.OmsOrderSetting;
import com.flame.mall.util.CommonResult;
import com.flame.mall.util.Global;
import com.flame.mall.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Var;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
@Slf4j
@Service
@RequiredArgsConstructor
public class OmsOrderSettingServiceImpl implements OmsOrderSettingService {

    private final RedisUtil redisUtil;
    private final OmsOrderSettingMapper orderSettingMapper;
    private final OmsOrderMapper orderMapper;

    @Override
    public OmsOrderSetting getItem(Long id) {
        return orderSettingMapper.selectByPrimaryKey(id);
    }

    @Override
    public CommonResult update(Long id, OmsOrderSetting orderSetting) {
        orderSetting.setId(id);
        long count = orderSettingMapper.updateByPrimaryKey(orderSetting);
        setOrderExpireTime(orderSetting);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 存入redis
     *
     * @param id
     * @param orderSetting
     */
    private void setOrderExpireTime(OmsOrderSetting orderSetting) {
        // 查询秒杀的订单
        OmsOrderExample omsOrderExample = new OmsOrderExample();
        omsOrderExample.createCriteria().andOrderTypeEqualTo(Global.OrderType.SPIKE.getValue());
        List<OmsOrder> omsOrderList = orderMapper.selectByExample(omsOrderExample);
        if (omsOrderList!=null) {
            omsOrderList.forEach(it -> {
                log.info("开始存入redis...订单编号【{}】", it.getOrderSn());
                redisUtil.set(Global.ExpireKey.EXPIRED_KEY_ORDER_SPIKE + it.getOrderSn(), it.getOrderSn());
                redisUtil.expire(Global.ExpireKey.EXPIRED_KEY_ORDER_SPIKE + it.getOrderSn(), orderSetting.getFlashOrderOvertime(), TimeUnit.MINUTES);
            });
        }
        // 正常订单
        OmsOrderExample normalOrderExample = new OmsOrderExample();
        normalOrderExample.createCriteria().andOrderTypeEqualTo(Global.OrderType.NORMAL.getValue());
        List<OmsOrder> normalOrderList = orderMapper.selectByExample(normalOrderExample);
        if (normalOrderList!=null) {
            normalOrderList.forEach(it -> {
                log.info("开始存入redis...订单编号【{}】", it.getOrderSn());
                redisUtil.set(Global.ExpireKey.EXPIRED_KEY_ORDER_NORMAL + it.getOrderSn(), it.getOrderSn());
                redisUtil.expire(Global.ExpireKey.EXPIRED_KEY_ORDER_NORMAL + it.getOrderSn(), orderSetting.getNormalOrderOvertime(), TimeUnit.MINUTES);
            });
        }
    }
}
