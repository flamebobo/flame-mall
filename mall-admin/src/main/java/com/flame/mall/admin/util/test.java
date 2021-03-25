package com.flame.mall.admin.util;

import org.apache.commons.lang.StringUtils;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2021/1/6 9:26
 */
public class test {
    public static void main(String[] args) {
        String name = "qwe_qweqw_qweqw_qweqwqq_123123q";
        String str = StringUtils.substringAfterLast(name, "_");
        String str1 = StringUtils.substringBeforeLast(name, "_");
        System.out.println(str1);
        System.out.println(str);
    }
}
