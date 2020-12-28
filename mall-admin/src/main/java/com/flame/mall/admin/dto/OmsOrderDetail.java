package com.flame.mall.admin.dto;

import com.flame.mall.mbg.model.OmsOrder;
import com.flame.mall.mbg.model.OmsOrderItem;
import com.flame.mall.mbg.model.OmsOrderOperateHistory;
import lombok.Data;

import java.util.List;

/**
 * 订单详情信息
 *
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2020/12/22 23:37
 */
@Data
public class OmsOrderDetail extends OmsOrder {

    private List<OmsOrderItem> orderItemList;
    private List<OmsOrderOperateHistory> historyList;

}
