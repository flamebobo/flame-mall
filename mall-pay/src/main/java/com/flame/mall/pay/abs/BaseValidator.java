
package com.flame.mall.pay.abs;

import com.flame.mall.admin.dto.OmsOrderDetail;
import com.flame.mall.admin.service.OmsOrderService;
import com.flame.mall.exception.BizException;
import com.flame.mall.pay.constants.PayReturnCodeEnum;
import com.flame.mall.pay.constants.RefundCodeEnum;
import com.flame.mall.pay.dto.PaymentRequest;
import com.flame.mall.pay.dto.RefundRequest;
import com.flame.mall.pay.util.ParamValidatorUtils;
import com.flame.mall.util.AbstractRequest;
import com.flame.mall.util.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * 验证器基类
 *
 * @author
 */
public abstract class BaseValidator implements Validator {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void validate(AbstractRequest request) {
        //基本参数校验
        ParamValidatorUtils.validateV2(request);
        //特殊校验
        specialValidate(request);
    }

    public abstract void specialValidate(AbstractRequest request);

    /**
     * 检验订单的公共方法
     *
     * @param request
     * @param orderService
     */
    public void commonValidate(AbstractRequest request, OmsOrderService orderService) {
        if (request instanceof PaymentRequest) {
            PaymentRequest paymentRequest = (PaymentRequest) request;
            // 校验订单是否存在
            CommonResult<OmsOrderDetail> detail = orderService.detail(paymentRequest.getOrderNo());
            if (null==detail.getData()) {
                throw new BizException(PayReturnCodeEnum.NO_ORDER_NOT_EXIST.getCode(), PayReturnCodeEnum.NO_ORDER_NOT_EXIST.getMsg());
            }
            // 校验订单是否已支付，同一订单支付幂等处理  status==3 为已完成
            if (!Objects.equals(detail.getData().getStatus(), 0)) {
                throw new BizException(PayReturnCodeEnum.HAD_PAY_ERROR.getCode(), PayReturnCodeEnum.HAD_PAY_ERROR.getMsg());
            }
            // 防止金额篡改等
            if (detail.getData().getTotalAmount().compareTo(paymentRequest.getOrderFee())!=0) {
                throw new BizException(PayReturnCodeEnum.ORDER_AMOUNT_ERROR.getCode(), PayReturnCodeEnum.ORDER_AMOUNT_ERROR.getMsg());
            }
        }
        //如果是退款请求
        else if (request instanceof RefundRequest) {
            RefundRequest refundRequest = (RefundRequest) request;
            //校验订单号是否正确
            CommonResult<OmsOrderDetail> detail = orderService.detail(refundRequest.getOrderId());
            if (null==detail.getData()) {
                throw new BizException(PayReturnCodeEnum.SYS_PARAM_NOT_RIGHT.getCode(), PayReturnCodeEnum.SYS_PARAM_NOT_RIGHT.getMsg());
            }
            //检查订单是否已退款
            if (Objects.equals(detail.getData().getStatus(), 7)) {
                throw new BizException(RefundCodeEnum.ORDER_HAD_REFUND.getCode(), RefundCodeEnum.ORDER_HAD_REFUND.getMsg());
            }
        }
    }
}
