package com.flame.mall.mbg.model;

import lombok.Data;

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
 * @time: 2021/1/6 17:57
 */
@Data
public class PayForm {

    private String nickName;
    private BigDecimal money;
    private String info;
    private String orderId;
    private String payType;

}
