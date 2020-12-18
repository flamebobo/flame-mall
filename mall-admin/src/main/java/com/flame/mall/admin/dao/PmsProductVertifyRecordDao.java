package com.flame.mall.admin.dao;

import com.flame.mall.mbg.model.PmsProductVertifyRecord;
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
 * @time: 2020/12/18 0:12
 */
public interface PmsProductVertifyRecordDao {

    int insertList(@Param("list") List<PmsProductVertifyRecord> list);
}
