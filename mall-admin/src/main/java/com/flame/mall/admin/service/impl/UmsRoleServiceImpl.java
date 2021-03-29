package com.flame.mall.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.flame.mall.admin.dao.UmsRoleDao;
import com.flame.mall.admin.dao.UmsRolePermissionRelationDao;
import com.flame.mall.admin.service.UmsRoleService;
import com.flame.mall.mbg.mapper.UmsRoleMapper;
import com.flame.mall.mbg.mapper.UmsRoleMenuRelationMapper;
import com.flame.mall.mbg.mapper.UmsRolePermissionRelationMapper;
import com.flame.mall.mbg.mapper.UmsRoleResourceRelationMapper;
import com.flame.mall.mbg.model.UmsMenu;
import com.flame.mall.mbg.model.UmsPermission;
import com.flame.mall.mbg.model.UmsResource;
import com.flame.mall.mbg.model.UmsRole;
import com.flame.mall.mbg.model.UmsRoleExample;
import com.flame.mall.mbg.model.UmsRoleMenuRelation;
import com.flame.mall.mbg.model.UmsRoleMenuRelationExample;
import com.flame.mall.mbg.model.UmsRolePermissionRelation;
import com.flame.mall.mbg.model.UmsRolePermissionRelationExample;
import com.flame.mall.mbg.model.UmsRoleResourceRelation;
import com.flame.mall.mbg.model.UmsRoleResourceRelationExample;
import com.flame.mall.util.CommonResult;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
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
    private final UmsRolePermissionRelationDao rolePermissionRelationDao;
    private final UmsRolePermissionRelationMapper rolePermissionRelationMapper;
    private final UmsRoleMenuRelationMapper roleMenuRelationMapper;
    private final UmsRoleResourceRelationMapper roleResourceRelationMapper;

    @Override
    public CommonResult create(UmsRole role) {
        role.setCreateTime(new Date());
        role.setSort(0);
        role.setAdminCount(0);
        int count = roleMapper.insert(role);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @Override
    public int update(Long id, UmsRole role) {
        role.setId(id);
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public int delete(List<Long> ids) {
        UmsRoleExample example = new UmsRoleExample();
        example.createCriteria().andIdIn(ids);
        return roleMapper.deleteByExample(example);
    }

    @Override
    public List<UmsPermission> getPermissionList(Long roleId) {
        return rolePermissionRelationDao.getPermissionList(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult updatePermission(Long roleId, List<Long> permissionIds) {
        // 先删除原有关系
        UmsRolePermissionRelationExample example = new UmsRolePermissionRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        rolePermissionRelationMapper.deleteByExample(example);
        // 批量插入新关系
        List<UmsRolePermissionRelation> relationList = new ArrayList<>();
        permissionIds.forEach(it -> {
            UmsRolePermissionRelation relation = new UmsRolePermissionRelation();
            relation.setRoleId(roleId);
            relation.setPermissionId(it);
            relationList.add(relation);
        });
        int count = rolePermissionRelationDao.insertList(relationList);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @Override
    public List<UmsRole> list() {
        return roleMapper.selectByExample(new UmsRoleExample());
    }

    @Override
    public List<UmsRole> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        UmsRoleExample umsRoleExample = new UmsRoleExample();
        if (!StrUtil.isEmpty(keyword)) {
            umsRoleExample.createCriteria().andNameLike("%" + keyword + "%");
        }
        return roleMapper.selectByExample(umsRoleExample);
    }

    @Override
    public List<UmsMenu> getMenuList(Long adminId) {
        return roleDao.getMenuList(adminId);
    }

    @Override
    public List<UmsMenu> listMenu(Long roleId) {
        return roleDao.getMenuListByRoleId(roleId);
    }

    @Override
    public List<UmsResource> listResource(Long roleId) {
        return roleDao.getResourceListByRoleId(roleId);
    }

    @Override
    public int allocMenu(Long roleId, List<Long> menuIds) {
        // 先删除原有关系
        UmsRoleMenuRelationExample example = new UmsRoleMenuRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        roleMenuRelationMapper.deleteByExample(example);
        // 批量插入新关系
        menuIds.forEach(it -> {
            UmsRoleMenuRelation relation = new UmsRoleMenuRelation();
            relation.setRoleId(roleId);
            relation.setMenuId(it);
            roleMenuRelationMapper.insert(relation);
        });

        return menuIds.size();
    }

    @Override
    public int allocResource(Long roleId, List<Long> resourceIds) {
        // 先删除原有关系
        UmsRoleResourceRelationExample example = new UmsRoleResourceRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        roleResourceRelationMapper.deleteByExample(example);
        // 批量插入新关系
        for (Long resourceId : resourceIds) {
            UmsRoleResourceRelation relation = new UmsRoleResourceRelation();
            relation.setRoleId(roleId);
            relation.setResourceId(resourceId);
            roleResourceRelationMapper.insert(relation);
        }
        return resourceIds.size();
    }
}
