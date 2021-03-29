package com.flame.mall.admin.dao;

import com.flame.mall.mbg.model.OmsOrderOperateHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>Title: 订单操作记录自定义Dao</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2020/12/28 23:42
 */
public interface OmsOrderOperateHistoryDao {

    /**
     * 批量创建
     */
    int insertList(@Param("list") List<OmsOrderOperateHistory> orderOperateHistoryList);
}
