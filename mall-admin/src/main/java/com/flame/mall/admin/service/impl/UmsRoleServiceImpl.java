package com.flame.mall.admin.service.impl;

import com.flame.mall.admin.dao.UmsRoleDao;
import com.flame.mall.admin.service.UmsRoleService;
import com.flame.mall.mbg.mapper.UmsRoleMapper;
import com.flame.mall.mbg.model.UmsMenu;
import com.flame.mall.mbg.model.UmsPermission;
import com.flame.mall.mbg.model.UmsResource;
import com.flame.mall.mbg.model.UmsRole;
import com.flame.mall.mbg.model.UmsRoleExample;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @time: 2020/12/3 17:14
 */
@Service
@RequiredArgsConstructor
public class UmsRoleServiceImpl implements UmsRoleService {

    private final UmsRoleDao roleDao;
    private final UmsRoleMapper roleMapper;

    @Override
    public int create(UmsRole role) {
        return 0;
    }

    @Override
    public int update(Long id, UmsRole role) {
        return 0;
    }

    @Override
    public int delete(List<Long> ids) {
        return 0;
    }

    @Override
    public List<UmsPermission> getPermissionList(Long roleId) {
        return null;
    }

    @Override
    public int updatePermission(Long roleId, List<Long> permissionIds) {
        return 0;
    }

    @Override
    public List<UmsRole> list() {
        return roleMapper.selectByExample(new UmsRoleExample());
    }

    @Override
    public List<UmsRole> list(String keyword, Integer pageSize, Integer pageNum) {
        return null;
    }

    @Override
    public List<UmsMenu> getMenuList(Long adminId) {
        return roleDao.getMenuList(adminId);
    }

    @Override
    public List<UmsMenu> listMenu(Long roleId) {
        return null;
    }

    @Override
    public List<UmsResource> listResource(Long roleId) {
        return null;
    }

    @Override
    public int allocMenu(Long roleId, List<Long> menuIds) {
        return 0;
    }

    @Override
    public int allocResource(Long roleId, List<Long> resourceIds) {
        return 0;
    }
}
