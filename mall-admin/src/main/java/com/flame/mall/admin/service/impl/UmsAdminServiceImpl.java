package com.flame.mall.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.flame.mall.admin.bo.AdminUserDetails;
import com.flame.mall.admin.dao.UmsAdminPermissionRelationDao;
import com.flame.mall.admin.dao.UmsAdminRoleRelationDao;
import com.flame.mall.admin.dto.UmsAdminParam;
import com.flame.mall.admin.dto.UpdateAdminPasswordParam;
import com.flame.mall.admin.enums.AccountEnum;
import com.flame.mall.admin.service.UmsAdminService;
import com.flame.mall.admin.service.UmsRoleService;
import com.flame.mall.mbg.mapper.UmsAdminMapper;
import com.flame.mall.mbg.mapper.UmsAdminPermissionRelationMapper;
import com.flame.mall.mbg.mapper.UmsAdminRoleRelationMapper;
import com.flame.mall.mbg.model.UmsAdmin;
import com.flame.mall.mbg.model.UmsAdminExample;
import com.flame.mall.mbg.model.UmsAdminPermissionRelation;
import com.flame.mall.mbg.model.UmsAdminPermissionRelationExample;
import com.flame.mall.mbg.model.UmsAdminRoleRelation;
import com.flame.mall.mbg.model.UmsAdminRoleRelationExample;
import com.flame.mall.mbg.model.UmsPermission;
import com.flame.mall.mbg.model.UmsResource;
import com.flame.mall.mbg.model.UmsRole;
import com.flame.mall.security.util.JwtTokenUtil;
import com.flame.mall.util.CommonResult;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2020/11/24 11:40
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UmsAdminServiceImpl implements UmsAdminService {

    private final UmsAdminMapper umsAdminMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    @NacosValue("${jwt.tokenHead}")
    private String tokenHead;
    private final UserDetailsService userDetailsService;
    private final UmsAdminRoleRelationDao adminRoleRelationDao;
    private final UmsRoleService roleService;
    private final UmsAdminRoleRelationMapper umsAdminRoleRelationMapper;
    private final UmsAdminPermissionRelationMapper umsAdminPermissionRelationMapper;
    private final UmsAdminPermissionRelationDao adminPermissionRelationDao;

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> adminList = umsAdminMapper.selectByExample(example);
        if (adminList!=null && adminList.size() > 0) {
            return adminList.get(0);
        }
        return null;
    }

    @Override
    public UmsAdmin register(UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtil.copyProperties(umsAdminParam, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(AccountEnum.Enable.getValue());
        // 查询是否有相同用户名的用户
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
        List<UmsAdmin> umsAdminList = umsAdminMapper.selectByExample(example);
        if (umsAdminList.size() > 0) {
            return null;
        }
        // 将密码进行加密操作
        String encodePassword = passwordEncoder.encode(umsAdminParam.getPassword());
        umsAdmin.setPassword(encodePassword);
//        umsAdminMapper.insert(umsAdmin);
        return umsAdmin;
    }

    @Override
    public CommonResult login(String username, String password) {
        String token = null;
        Map<String, String> tokenMap = new HashMap<>();
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确！");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
            if (token == null) {
               return CommonResult.validateFailed("用户名或密码错误");
            }
            tokenMap.put("token", token);
            tokenMap.put("tokenHead", tokenHead);
        } catch (AuthenticationException e) {
            log.warn("登录异常：{}", e.getMessage());
        }
        return CommonResult.success(tokenMap);
    }

    @Override
    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshHeadToken(oldToken);
    }

    @Override
    public UmsAdmin getItem(Long id) {
        return umsAdminMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        UmsAdminExample example = new UmsAdminExample();
        UmsAdminExample.Criteria criteria = example.createCriteria();
        if (!StrUtil.isEmpty(keyword)) {
            criteria.andUsernameLike("%" + keyword + "%");
            example.or(example.createCriteria().andNickNameLike("%" + keyword + "%"));
        }
        return umsAdminMapper.selectByExample(example);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(Long id, UmsAdmin admin) {
        // 以防在body里没传id
        admin.setId(id);
        UmsAdmin umsAdmin = umsAdminMapper.selectByPrimaryKey(id);
        if (StrUtil.isEmpty(admin.getPassword())) {
            admin.setPassword(null);
        }
        if (umsAdmin.getPassword().equals(admin.getPassword())) {
            admin.setPassword(null);
        }else{
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        }
        return umsAdminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Long id) {
        // 真实删除操作
        return umsAdminMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult updateRole(Long adminId, List<Long> roleIds) {
        if (CollUtil.isEmpty(roleIds)) {
            return CommonResult.failed();
        }
        // 先删除原来的关系
        UmsAdminRoleRelationExample umsAdminRoleRelationExample = new UmsAdminRoleRelationExample();
        umsAdminRoleRelationExample.createCriteria().andAdminIdEqualTo(adminId);
        umsAdminRoleRelationMapper.deleteByExample(umsAdminRoleRelationExample);
        // 建立新的关系
        if (!CollUtil.isEmpty(roleIds)) {
            List<UmsAdminRoleRelation> list = new ArrayList<>();
            roleIds.forEach(it ->{
                UmsAdminRoleRelation roleRelation = new UmsAdminRoleRelation();
                roleRelation.setAdminId(adminId);
                roleRelation.setRoleId(it);
                list.add(roleRelation);
            });
            adminRoleRelationDao.insertList(list);
        }
        return CommonResult.success(roleIds.size());
    }

    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        return adminRoleRelationDao.getRoleList(adminId);
    }

    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        return adminRoleRelationDao.getResourceList(adminId);
    }

    @Override
    public int updatePermission(Long adminId, List<Long> permissionIds) {
        // 删除原有的权限关系
        UmsAdminPermissionRelationExample relationExample = new UmsAdminPermissionRelationExample();
        relationExample.createCriteria().andAdminIdEqualTo(adminId);
        umsAdminPermissionRelationMapper.deleteByExample(relationExample);
        // 获取用户所有角色权限
        List<UmsPermission> permissionList = adminRoleRelationDao.getRolePermissionList(adminId);
        List<Long> idList = permissionList.stream().map(UmsPermission::getId).collect(Collectors.toList());
        if (!CollUtil.isEmpty(idList)) {
            List<UmsAdminPermissionRelation> relationList = new ArrayList<>();
            // 筛选出+权限
            List<Long> addPermissionIdList = permissionIds.stream().filter(permissionId -> !idList.contains(permissionId)).collect(Collectors.toList());
            // 筛选出-权限
            List<Long> subPermissionIdList = idList.parallelStream().filter(permissionId -> !permissionIds.contains(permissionId)).collect(Collectors.toList());
            //插入+-权限关系
            relationList.addAll(convert(adminId, 1, addPermissionIdList));
            relationList.addAll(convert(adminId, -1, subPermissionIdList));
            return adminPermissionRelationDao.insertList(relationList);
        }
        return 0;
    }

    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return adminRoleRelationDao.getPermissionList(adminId);
    }

    @Override
    public CommonResult updatePassword(UpdateAdminPasswordParam param) {
        if (StrUtil.isEmpty(param.getUsername()) || StrUtil.isEmpty(param.getOldPassword()) || StrUtil.isEmpty(param.getNewPassword())) {
            return CommonResult.failed("提交参数不合法");
        }
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(param.getUsername());
        List<UmsAdmin> umsAdminList = umsAdminMapper.selectByExample(example);
        if (CollUtil.isEmpty(umsAdminList)) {
            return CommonResult.failed("找不到该用户");
        }
        UmsAdmin umsAdmin = umsAdminList.get(0);
        if (!passwordEncoder.matches(param.getOldPassword(), umsAdmin.getPassword())) {
            return CommonResult.failed("旧密码错误");
        }
        umsAdmin.setPassword(passwordEncoder.encode(param.getNewPassword()));
        umsAdminMapper.updateByPrimaryKey(umsAdmin);
        return CommonResult.success(1);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        //获取用户信息
        UmsAdmin admin = getAdminByUsername(username);
        if (admin != null) {
            List<UmsResource> resourceList = getResourceList(admin.getId());
            return new AdminUserDetails(admin,resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
    public CommonResult getAdminInfo(Principal principal) {
        if(principal==null){
            return CommonResult.unauthorized(null);
        }
        String username = principal.getName();
        UmsAdmin umsAdmin = getAdminByUsername(username);
        Map<String, Object> data = new HashMap<>();
        data.put("username", umsAdmin.getUsername());
        data.put("roles", new String[]{"TEST"});
        data.put("menus", roleService.getMenuList(umsAdmin.getId()));
        data.put("icon", umsAdmin.getIcon());
        return CommonResult.success(data);
    }

    /**
     * 将+-权限关系转化为对象
     */
    private List<UmsAdminPermissionRelation> convert(Long adminId,Integer type,List<Long> permissionIdList) {
        List<UmsAdminPermissionRelation> relationList = permissionIdList.stream().map(permissionId -> {
            UmsAdminPermissionRelation relation = new UmsAdminPermissionRelation();
            relation.setAdminId(adminId);
            relation.setType(type);
            relation.setPermissionId(permissionId);
            return relation;
        }).collect(Collectors.toList());
        return relationList;
    }
}
