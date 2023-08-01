package com.FreeL00P.ssys.sys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ServiceSysApplication
 *
 * @author fj
 * @since 2023/7/30 21:33
 */
@SpringBootApplication
@MapperScan("com.FreeL00P.ssys.sys.mapper")
public class ServiceSysApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceSysApplication.class, args);
    }
}