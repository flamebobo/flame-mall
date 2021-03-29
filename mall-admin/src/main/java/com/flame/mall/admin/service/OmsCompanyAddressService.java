package com.flame.mall.admin.service;

import com.flame.mall.mbg.model.OmsCompanyAddress;

import java.util.List;

/**
 * 收货地址管Service
 *
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2021/1/6 11:51
 */
public interface OmsCompanyAddressService {

    /**
     * 获取全部收货地址
     */
    List<OmsCompanyAddress> list();

}
