package com.flame.mall.admin.dao;

import com.flame.mall.admin.dto.OmsOrderReturnApplyResult;
import com.flame.mall.admin.dto.OmsReturnApplyQueryParam;
import com.flame.mall.mbg.model.OmsOrderReturnApply;
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
 * @time: 2020/12/31 16:23
 */
public interface OmsOrderReturnApplyDao {

    /**
     * 查询申请列表
     */
    List<OmsOrderReturnApply> getList(@Param("queryParam") OmsReturnApplyQueryParam queryParam);

    /**
     * 获取申请详情
     */
    OmsOrderReturnApplyResult getDetail(@Param("id") Long id);

}
