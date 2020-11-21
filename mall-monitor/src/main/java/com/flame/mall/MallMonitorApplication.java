package com.flame.mall;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2020/11/19 22:33
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableAdminServer
@NacosPropertySource(dataId = "monitor.yaml", autoRefreshed = true)
public class MallMonitorApplication {

    public static void main(String[] args) {

        SpringApplication.run(MallMonitorApplication.class, args);
    }

}
