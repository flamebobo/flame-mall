package com.flame.mall.pay.constants;


import com.flame.mall.pay.service.IEnum;
import lombok.Data;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2021/1/6 18:05
 */
public enum PayChannelEnum implements IEnum {

    ALI_PAY("ali_pay","支付宝支付渠道"),
    WECHAT_PAY("wechat_pay","微信支付渠道"),
    ALI_REFUND("ali_refund","支付宝退款渠道"),
    WECHAT_REFUND("wechat_refund","微信退款渠道");


    private String code;

    private String desc;

    PayChannelEnum(String code, String desc){
        this.code=code;
        this.desc=desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
