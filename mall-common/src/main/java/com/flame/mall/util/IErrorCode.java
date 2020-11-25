package com.flame.mall.util;

/**
 * 封装API的错误码
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2020/11/24 0:26
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
