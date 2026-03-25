package com.zyc.springframework;

import com.zyc.springframework.Pojo.product;
import com.zyc.springframework.Pojo.productRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class productRepositoryTest {

    @Autowired
    private productRepository repository;

    @Test
    void testCountProduct() {
        assertEquals(8,repository.countProduct());
    }

    @Test
    void testQueryAllProduct() {
        List<product> products = repository.queryAllProduct();
        assertNotNull(products);
        assertFalse(products.isEmpty());
    }

    @Test
    void testQueryForItem() {
        product item = repository.queryProductById(1L) ;
        assertItem(item, 1 , "iPhone 15","苹果手机");
    }

    private void assertItem(product product, Integer id, String name, String desc) {

        assertNotNull(product);
        assertEquals(id,product.getId());
        assertEquals(name,product.getName());
        assertEquals(desc,product.getDescription());
    }

    @Test
    void testInsertProducts() {
        List<product> products = Stream.of("Oppok10","Oppok11","Oppok12")
                .map(n -> product.builder()
                        .name(n)
                        .description("nice phone")
                        .build()).collect(Collectors.toList());
        assertEquals(3,repository.insertProduct(products));
        assertItem(repository.queryProductById(9),9,"Oppok10","nice phone");
        assertItem(repository.queryProductById(10),10,"Oppok11","nice phone");
        assertItem(repository.queryProductById(11),11,"Oppok12","nice phone");
    }

}
