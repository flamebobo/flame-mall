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


//    @Profile("insecure")
//    @Configuration
//    public static class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http.authorizeRequests().anyRequest().permitAll()//
//                    .and().csrf().disable();
//        }
//    }
//
//    @Profile("secure")
//    @Configuration
//    public static class SecuritySecureConfig extends WebSecurityConfigurerAdapter {
//
//        private final String adminContextPath;
//
//        public SecuritySecureConfig(AdminServerProperties adminServerProperties) {
//            this.adminContextPath = adminServerProperties.getContextPath();
//        }
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            // @formatter:off
//            SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
//            successHandler.setTargetUrlParameter("redirectTo");
//
//            http.authorizeRequests()
//                    .antMatchers(adminContextPath + "/assets/**").permitAll()
//                    .antMatchers(adminContextPath + "/login").permitAll()
//                    .anyRequest().authenticated()
//                    .and()
//                    .formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler)
//                    .and()
//                    .logout().logoutUrl(adminContextPath + "/logout").and()
//                    .httpBasic().and()
//                    .csrf().disable();
//            // @formatter:on
//        }
//    }


}
