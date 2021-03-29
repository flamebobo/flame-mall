package com.flame.mall.pay.abs;

import com.alibaba.fastjson.JSON;
import com.flame.mall.exception.BizException;
import com.flame.mall.pay.service.Payment;
import com.flame.mall.util.AbstractRequest;
import com.flame.mall.util.AbstractResponse;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

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
@Slf4j
public abstract class BasePayment implements Payment {

    public static Map<String, BasePayment> paymentMap = new ConcurrentHashMap<String, BasePayment>();

    @PostConstruct
    public void init() {
        paymentMap.put(getPayChannel(), this);
    }

    /**
     * 获取验证器
     *
     * @return
     */
    public abstract Validator getValidator();

    /**
     * 创建上下文信息
     *
     * @param request
     * @return
     */
    public abstract Context createContext(AbstractRequest request);

    /**
     * 为下层的支付渠道的数据做好准备
     *
     * @param request
     * @param context
     * @throws BizException
     */
    public  void prepare(AbstractRequest request, Context context)throws BizException{
        SortedMap<String, Object> sParaTemp = new TreeMap<String, Object>();
        context.setsParaTemp(sParaTemp);
    };


    /**
     * 基本业务处理
     *
     * @param request
     * @param context
     * @return AbstractResponse
     * @throws BizException
     */
    public abstract AbstractResponse generalProcess(AbstractRequest request, Context context) throws BizException;

    /***
     * 基本业务处理完成后执行的后续操作
     * @param request
     * @param respond
     * @param context
     * @return
     * @throws BizException
     */
    public abstract void afterProcess(AbstractRequest request, AbstractResponse respond, Context context) throws BizException;

    /**
     * 核心处理器
     */
    @Override
    public <T extends AbstractResponse> T process(AbstractRequest request) throws BizException {
        log.info("Begin BasePayment.process:{}", JSON.toJSONString(request));
        AbstractResponse response = null;
        //创建上下文
        Context context = createContext(request);
        //验证
        getValidator().validate(request);
        //准备
        prepare(request, context);
        //执行
        response = generalProcess(request, context);
        //善后
        afterProcess(request, response, context);
        return (T) response;
    }

    /**
     * 获取支付渠道
     *
     * @return
     */
    public abstract String getPayChannel();
}
