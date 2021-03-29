package com.flame.mall.pay.abs;


import com.flame.mall.util.AbstractRequest;

/**
 * 数据验证接口类
 * @author
 */
public interface Validator {
    /**
     * 数据验证
     * @param request
     */
    void validate(AbstractRequest request);
}
