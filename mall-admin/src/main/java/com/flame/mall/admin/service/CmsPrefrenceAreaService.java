package com.flame.mall.admin.service;

import com.flame.mall.mbg.model.CmsPrefrenceArea;

import java.util.List;

/**
 * 优选专区Service
 *
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2020/12/10 15:10
 */
public interface CmsPrefrenceAreaService {

    /**
     * 获取所有优选专区
     */
    List<CmsPrefrenceArea> listAll();
}
