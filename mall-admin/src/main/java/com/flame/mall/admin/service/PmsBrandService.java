package com.flame.mall.admin.service;

import com.flame.mall.admin.dto.PmsBrandParam;
import com.flame.mall.mbg.model.PmsBrand;
import com.flame.mall.util.CommonResult;
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
 * @time: 2020/12/9 16:51
 */
public interface PmsBrandService {
    /**
     * 获取所有品牌
     */
    List<PmsBrand> listAllBrand();

    /**
     * 创建品牌
     */
    CommonResult createBrand(PmsBrandParam pmsBrandParam);

    /**
     * 修改品牌
     */

    CommonResult updateBrand(Long id, PmsBrandParam pmsBrandParam);

    /**
     * 删除品牌
     */
    CommonResult deleteBrand(Long id);

    /**
     * 批量删除品牌
     */
    CommonResult deleteBrand(List<Long> ids);

    /**
     * 分页查询品牌
     */
    List<PmsBrand> listBrand(String keyword, int pageNum, int pageSize);

    /**
     * 获取品牌
     */
    PmsBrand getBrand(Long id);

    /**
     * 修改显示状态
     */
    CommonResult updateShowStatus(List<Long> ids, Integer showStatus);

    /**
     * 修改厂家制造商状态
     */
    CommonResult updateFactoryStatus(List<Long> ids, Integer factoryStatus);
}
