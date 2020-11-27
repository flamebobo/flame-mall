package com.flame.mall.admin.controller;

import com.flame.mall.mbg.model.UmsAdmin;
import com.flame.mall.util.CommonResult;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后台用户管理
 *
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2020/11/24 11:29
 */
@Api(tags = "UmsAdminController", description = "后台用户管理")
@RestController("/admin")
public class UmsAdminController {
    public CommonResult<UmsAdmin> register(@RequestBody UmsAdmin umsAdmin){
        return null;

    }
}
