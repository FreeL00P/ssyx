package com.FreeL00P.ssyx.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ServiceSearchApplication
 *
 * @author fj
 * @since 2023/8/2 20:33
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//取消数据源自动配置
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceSearchApplication.class, args);
    }

}
