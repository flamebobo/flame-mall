package com.flame.mall.pay.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.flame.mall.mbg.model.OmsOrder;
import com.flame.mall.mbg.model.PayForm;
import com.flame.mall.pay.dto.PaymentRequest;
import com.flame.mall.pay.dto.PaymentResponse;
import com.flame.mall.pay.service.PayService;
import com.flame.mall.util.CommonPage;
import com.flame.mall.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * 支付controller
 *
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2021/1/6 17:40
 */
@Slf4j
@RestController
@RequestMapping("/pay")
@Api(tags = "PayController", description = "支付")
@RequiredArgsConstructor
public class PayController {

    private final PayService payService;

    @ApiOperation("查询订单")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult pay(@RequestBody PayForm payForm, HttpServletRequest httpServletRequest){

        return payService.execPay(payForm, httpServletRequest);
    }
}
