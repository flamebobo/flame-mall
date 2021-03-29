package com.flame.mall.pay.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.flame.mall.mbg.model.PayForm;
import com.flame.mall.pay.abs.BasePayment;
import com.flame.mall.pay.dto.PaymentRequest;
import com.flame.mall.pay.dto.PaymentResponse;
import com.flame.mall.pay.service.PayService;
import com.flame.mall.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2021/1/6 23:54
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {

    @Override
    public CommonResult execPay(PayForm payForm, HttpServletRequest httpServletRequest) {
        log.info("支付表单数据:{}", payForm);
        PaymentRequest request = new PaymentRequest();
//        String userInfo=(String)httpServletRequest.getAttribute(TokenIntercepter.USER_INFO_KEY);
//        JSONObject object = JSON.parseObject(userInfo);
        Long uid = 12312L;
        request.setUserId(uid);
        BigDecimal money = payForm.getMoney();
        request.setOrderFee(money);
        request.setPayChannel(payForm.getPayType());
        request.setSubject(payForm.getInfo());
        request.setSpbillCreateIp("115.195.125.164");
        request.setOrderNo(payForm.getOrderId());
        request.setTotalFee(money);
        BasePayment.paymentMap.get(request.getPayChannel()).process(request);
        return CommonResult.failed();
    }
}
