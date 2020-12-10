package com.flame.mall.admin.service;

import com.flame.mall.mbg.model.UmsMemberLevel;

import java.util.List;

/**
 * 会员等级管理Service
 *
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2020/12/10 11:53
 */
public interface UmsMemberLevelService {

    /**
     * 获取所有会员登录
     * @param defaultStatus 是否为默认会员
     */
    List<UmsMemberLevel> list(Integer defaultStatus);

}
