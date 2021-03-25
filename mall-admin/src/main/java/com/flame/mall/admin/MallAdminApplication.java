package com.flame.mall.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2020/11/12 16:02
 */
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(value = "com.flame.mall")
public class MallAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallAdminApplication.class, args);
    }

}
