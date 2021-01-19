package com.flame.mall.pay.service;

import com.flame.mall.exception.BizException;
import com.flame.mall.pay.dto.PaymentNotifyRequest;
import com.flame.mall.pay.dto.PaymentRequest;
import com.flame.mall.util.AbstractRequest;
import com.flame.mall.util.AbstractResponse;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2021/1/7 11:49
 */
public interface Payment {

    /**
     * 发起交易执行的过程
     * @param request
     * @return
     * @throws BizException
     */
     <T extends AbstractResponse> T process(AbstractRequest request) throws BizException;

    /**
     * 完成交易结果的处理
     * @param request
     * @return
     * @throws BizException
     */
    <T extends AbstractResponse> T completePayment(PaymentNotifyRequest request) throws BizException;
}
