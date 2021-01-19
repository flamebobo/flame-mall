package com.flame.mall.pay.service;

import com.flame.mall.mbg.model.PayForm;
import com.flame.mall.pay.dto.PaymentRequest;
import com.flame.mall.pay.dto.PaymentResponse;
import com.flame.mall.util.CommonResult;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2021/1/6 23:53
 */
public interface PayService {

    CommonResult execPay(PayForm payForm, HttpServletRequest httpServletRequest);

}
