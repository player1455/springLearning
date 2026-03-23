package com.zyc.springframework.Config;


import com.zyc.springframework.Pojo.productProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
// 用于测试,配置product
@Configuration
@EnableConfigurationProperties(productProperty.class)
@ConditionalOnProperty(name = "product.ready", havingValue = "true")
public class shopConfig {
}
