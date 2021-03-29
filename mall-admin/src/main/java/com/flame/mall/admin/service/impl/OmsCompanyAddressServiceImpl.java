package com.flame.mall.admin.service.impl;

import com.flame.mall.admin.service.OmsCompanyAddressService;
import com.flame.mall.mbg.mapper.OmsCompanyAddressMapper;
import com.flame.mall.mbg.model.OmsCompanyAddress;
import com.flame.mall.mbg.model.OmsCompanyAddressExample;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
 * @time: 2021/1/6 11:51
 */
@Service
@RequiredArgsConstructor
public class OmsCompanyAddressServiceImpl implements OmsCompanyAddressService {

    private final OmsCompanyAddressMapper companyAddressMapper;

    @Override
    public List<OmsCompanyAddress> list() {
        return companyAddressMapper.selectByExample(new OmsCompanyAddressExample());
    }
}
