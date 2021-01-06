package com.flame.mall.admin.dto;

import com.flame.mall.mbg.model.OmsCompanyAddress;
import com.flame.mall.mbg.model.OmsOrderReturnApply;
import lombok.Data;

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
@Data
public class OmsOrderReturnApplyResult extends OmsOrderReturnApply {

    private OmsCompanyAddress companyAddress;
}
