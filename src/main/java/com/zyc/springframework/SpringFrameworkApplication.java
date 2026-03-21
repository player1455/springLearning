package com.zyc.springframework;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.zyc.springframework.Mapper")
@SpringBootApplication
public class SpringFrameworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringFrameworkApplication.class, args);
    }

}
