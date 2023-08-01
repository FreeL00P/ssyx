package com.FreeL00P.ssyx.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * ServiceProductApplication
 *
 * @author fj
 * @since 2023/8/1 12:13
 */


@SpringBootApplication
@MapperScan("com.FreeL00P.ssyx.product.mapper")
public class ServiceProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceProductApplication.class, args);
    }
}
