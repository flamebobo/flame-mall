package com.flame.mall.admin.dao;

import com.flame.mall.admin.dto.OmsOrderDeliveryParam;
import com.flame.mall.admin.dto.OmsOrderDetail;
import com.flame.mall.admin.dto.OmsOrderQueryParam;
import com.flame.mall.mbg.model.OmsOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2020/12/22 23:54
 */
public interface OmsOrderDao {
    /**
     * 条件查询订单
     */
    List<OmsOrder> getList(@Param("queryParam") OmsOrderQueryParam queryParam);

    /**
     * 批量发货
     */
    int delivery(@Param("list") List<OmsOrderDeliveryParam> deliveryParamList);

    /**
     * 获取订单详情
     */
    OmsOrderDetail getDetail(@Param("id") Long id);

    /**
     * 获取订单详情
     */
    OmsOrderDetail getDetailByOrderSn(@Param("orderSn") String orderSn);
}
