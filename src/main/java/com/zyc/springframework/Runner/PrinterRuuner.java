package com.zyc.springframework.Runner;


import com.zyc.springframework.Pojo.productRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j

public class PrinterRuuner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("共有{}种商品选择", productRepository.countProduct());
        productRepository.queryAllProduct().forEach(i -> log.info("商品:{}",i));
    }

    @Autowired
    private productRepository productRepository;



}
