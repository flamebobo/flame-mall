package com.flame.mall.admin.dao;

import com.flame.mall.mbg.model.UmsMenu;
import com.flame.mall.mbg.model.UmsResource;
import org.apache.ibatis.annotations.Param;

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
 * @time: 2020/12/3 17:15
 */
public interface UmsRoleDao {

    List<UmsMenu> getMenuList(@Param("adminId") Long adminId);

    List<UmsMenu> getMenuListByRoleId(@Param("roleId") Long roleId);

    List<UmsResource> getResourceListByRoleId(@Param("roleId") Long roleId);
}
