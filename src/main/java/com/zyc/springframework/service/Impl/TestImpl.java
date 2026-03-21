package com.zyc.springframework.service.Impl;

import com.zyc.springframework.Mapper.DataMapper;
import com.zyc.springframework.Pojo.product;
import com.zyc.springframework.service.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TestImpl implements DataService {

    private final ServiceImpl service ;



    @Override
    public product getProducts(Integer id) {
        product product = service.getProducts(id);
        return product;
    }
}
