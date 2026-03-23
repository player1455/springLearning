package com.zyc.springframework;

import com.zyc.springframework.Pojo.productProperty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

// 省略import部分
@SpringBootTest(classes = SpringFrameworkApplication.class, properties = {
        "product.ready=true",
        "product.open-hours=8:30-22:00"
})
public class ShopConfigurationEnableTest {
    @Autowired
    private ApplicationContext applicationContext;
    @Test
    void testPropertiesBeanAvailable() {
        // 验证能通过类型获取到bean
        productProperty bean = applicationContext.getBean(productProperty.class);
        assertNotNull(bean);
        
        // 验证bean存在（正确的bean名称是: product-com.zyc.springframework.Pojo.productProperty）
        assertTrue(applicationContext
                .containsBean("product-com.zyc.springframework.Pojo.productProperty"));
    }
    @Test
    void testPropertyValues() {
        productProperty properties =
                applicationContext.getBean(productProperty.class);
        assertTrue(properties.isReady());
        assertEquals("8:30-22:00", properties.getOpenHours());
    }
}
