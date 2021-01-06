package com.flame.mall.admin.dto;

import io.swagger.annotations.ApiModelProperty;
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
 * @time: 2020/12/31 16:24
 */
@Data
public class OmsUpdateStatusParam {
    @ApiModelProperty("服务单号")
    private Long id;
    @ApiModelProperty("收货地址关联id")
    private Long companyAddressId;
    @ApiModelProperty("确认退款金额")
    private BigDecimal returnAmount;
    @ApiModelProperty("处理备注")
    private String handleNote;
    @ApiModelProperty("处理人")
    private String handleMan;
    @ApiModelProperty("收货备注")
    private String receiveNote;
    @ApiModelProperty("收货人")
    private String receiveMan;
    @ApiModelProperty("申请状态：1->退货中；2->已完成；3->已拒绝")
    private Integer status;
}
