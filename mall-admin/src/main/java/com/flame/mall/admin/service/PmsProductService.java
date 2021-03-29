package com.flame.mall.admin.service;

import com.flame.mall.admin.dto.PmsProductParam;
import com.flame.mall.admin.dto.PmsProductQueryParam;
import com.flame.mall.admin.dto.PmsProductResult;
import com.flame.mall.mbg.model.PmsProduct;
import com.flame.mall.util.CommonResult;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
 * @time: 2020/12/7 16:23
 */
public interface PmsProductService {

    /**
     * 分页查询商品
     */
    List<PmsProduct> list(PmsProductQueryParam productQueryParam, Integer pageSize, Integer pageNum);

    /**
     * 创建商品
     */
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    CommonResult create(PmsProductParam productParam);

    /**
     * 根据商品编号获取更新信息
     */
    PmsProductResult getUpdateInfo(Long id);

    /**
     * 更新商品
     */
    CommonResult update(Long id, PmsProductParam productParam);


    /**
     * 批量修改审核状态
     *
     * @param ids          产品id
     * @param verifyStatus 审核状态
     * @param detail       审核详情
     */
    CommonResult updateVerifyStatus(List<Long> ids, Integer verifyStatus, String detail);

    /**
     * 批量修改商品上架状态
     */
    CommonResult updatePublishStatus(List<Long> ids, Integer publishStatus);

    /**
     * 批量修改商品推荐状态
     */
    CommonResult updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    /**
     * 批量修改新品状态
     */
    CommonResult updateNewStatus(List<Long> ids, Integer newStatus);

    /**
     * 批量删除商品
     */
    CommonResult updateDeleteStatus(List<Long> ids, Integer deleteStatus);

    /**
     * 根据商品名称或者货号模糊查询
     */
    List<PmsProduct> list(String keyword);

}
