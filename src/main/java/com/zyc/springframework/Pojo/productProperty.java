package com.zyc.springframework.Pojo;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

// 测试类,用于配置product
@Data
@ConfigurationProperties(prefix = "product")
public class productProperty {

    private boolean ready;
    private String openHours;

}
