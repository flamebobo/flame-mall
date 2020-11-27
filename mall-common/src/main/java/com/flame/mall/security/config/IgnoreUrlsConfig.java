package com.flame.mall.security.config;

import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于配置不需要保护的资源路径
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2020/11/23 0:07
 */
@Getter
@Setter
@NacosConfigurationProperties(prefix = "secure.ignored", dataId = "mall-common.yaml")
public class IgnoreUrlsConfig {

    private List<String> urls = new ArrayList<>();
}
