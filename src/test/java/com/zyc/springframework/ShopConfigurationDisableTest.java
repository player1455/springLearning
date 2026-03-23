package com.zyc.springframework;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = SpringFrameworkApplication.class, properties = {
        "product.ready=false"
})
public class ShopConfigurationDisableTest {
    @Autowired
    private ApplicationContext applicationContext;
    @Test
    void testPropertiesBeanUnavailable() {
        assertEquals("false",
                applicationContext.getEnvironment().getProperty("product.ready"));
        // 当product.ready=false时，shopConfig配置类不会被加载，因此productProperty bean不存在
        assertFalse(applicationContext.containsBean("product-com.zyc.springframework.Pojo.productProperty"));
    }
}