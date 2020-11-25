package com.flame.mall.admin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2020/11/24 11:50
 */
@Getter
@AllArgsConstructor
public enum AccountEnum {
    /**
     * 账号禁用
     */
    Disable(0, "禁用"),
    /**
     * 账号启用
     */
    Enable(1, "启用");

    int value;
    String message;

}
