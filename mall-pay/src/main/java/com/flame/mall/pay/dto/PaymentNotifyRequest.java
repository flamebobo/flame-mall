package com.flame.mall.pay.dto;

import com.flame.mall.exception.BizException;
import com.flame.mall.pay.constants.PayChannelEnum;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2021/1/7 15:12
 */
@Data
public class PaymentNotifyRequest {

    /**
     * 支付渠道（alipay:支付宝/ wechat_pay:微信）
     */
    private String payChannel;

    /**
     * 服务端返回的支付通知结果
     */
    private Map<String, String[]> resultMap;

    /**
     * 微信返回的结果
     */
    private String xml;

    public void requestCheck() {
        if(payChannel.equals(PayChannelEnum.ALI_PAY)||payChannel.equals(PayChannelEnum.ALI_REFUND)){
            if(resultMap==null||resultMap.size() == 0){
                throw new BizException("0070001","异步通知返回的内容为空");
            }
        }
        if(payChannel.equals(PayChannelEnum.WECHAT_PAY)||payChannel.equals(PayChannelEnum.WECHAT_REFUND)){
            if(StringUtils.isBlank(xml)){
                throw new BizException("0070001","异步通知返回的内容为空");
            }
        }
    }
}
