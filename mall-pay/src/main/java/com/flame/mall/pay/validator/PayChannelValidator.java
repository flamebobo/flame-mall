package com.flame.mall.pay.validator;

import com.flame.mall.pay.constants.PayChannelEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

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
public class PayChannelValidator implements ConstraintValidator<PayChannel, String> {

    private String payChannel = null;

    @Override
    public void initialize(PayChannel constraintAnnotation) {
        //可以在此做一些初始化工作  例如 从获取注解中的某些值
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return EnumUtils.containsVal(PayChannelEnum.values(), value);
    }
}
