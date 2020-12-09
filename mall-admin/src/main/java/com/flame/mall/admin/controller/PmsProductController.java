package com.flame.mall.admin.controller;

import com.flame.mall.admin.dto.PmsProductQueryParam;
import com.flame.mall.admin.service.PmsProductService;
import com.flame.mall.mbg.model.PmsProduct;
import com.flame.mall.util.CommonPage;
import com.flame.mall.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品管理controller
 *
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2020/12/7 16:06
 */
@Controller
@Api(tags = "PmsProductController", description = "商品管理")
@RequestMapping("/product")
public class PmsProductController {

    @Autowired
    private PmsProductService pmsProductService;

    @ResponseBody
    @ApiOperation("查询商品")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<PmsProduct>> getList(PmsProductQueryParam pmsProductQueryParam,
                                                        @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        List<PmsProduct> pmsProducts = pmsProductService.list(pmsProductQueryParam, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(pmsProducts));
    }
}
