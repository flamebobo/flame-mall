package com.flame.mall.admin.service.impl;

import com.flame.mall.admin.dao.OmsOrderDao;
import com.flame.mall.admin.dto.OmsMoneyInfoParam;
import com.flame.mall.admin.dto.OmsOrderDeliveryParam;
import com.flame.mall.admin.dto.OmsOrderDetail;
import com.flame.mall.admin.dto.OmsOrderQueryParam;
import com.flame.mall.admin.dto.OmsReceiverInfoParam;
import com.flame.mall.admin.service.OmsOrderService;
import com.flame.mall.mbg.model.OmsOrder;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @time: 2020/12/22 23:53
 */
@Service
@RequiredArgsConstructor
public class OmsOrderServiceImpl implements OmsOrderService {

    private final OmsOrderDao orderDao;

    @Override
    public List<OmsOrder> list(OmsOrderQueryParam queryParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return orderDao.getList(queryParam);
    }

    @Override
    public int delivery(List<OmsOrderDeliveryParam> deliveryParamList) {
        return 0;
    }

    @Override
    public int close(List<Long> ids, String note) {
        return 0;
    }

    @Override
    public int delete(List<Long> ids) {
        return 0;
    }

    @Override
    public OmsOrderDetail detail(Long id) {
        return null;
    }

    @Override
    public int updateReceiverInfo(OmsReceiverInfoParam receiverInfoParam) {
        return 0;
    }

    @Override
    public int updateMoneyInfo(OmsMoneyInfoParam moneyInfoParam) {
        return 0;
    }

    @Override
    public int updateNote(Long id, String note, Integer status) {
        return 0;
    }
}
