package com.flame.mall.admin.service;

import com.flame.mall.admin.dto.OmsOrderReturnApplyResult;
import com.flame.mall.admin.dto.OmsReturnApplyQueryParam;
import com.flame.mall.admin.dto.OmsUpdateStatusParam;
import com.flame.mall.mbg.model.OmsOrderReturnApply;

import java.util.List;

/**
 * <p>Title: 退货申请管理Service</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2020/12/31 16:13
 */
public interface OmsOrderReturnApplyService {

    /**
     * 分页查询申请
     */
    List<OmsOrderReturnApply> list(OmsReturnApplyQueryParam queryParam, Integer pageSize, Integer pageNum);

    /**
     * 批量删除申请
     */
    int delete(List<Long> ids);

    /**
     * 修改申请状态
     */
    int updateStatus(Long id, OmsUpdateStatusParam statusParam);

    /**
     * 获取指定申请详情
     */
    OmsOrderReturnApplyResult getItem(Long id);
}
