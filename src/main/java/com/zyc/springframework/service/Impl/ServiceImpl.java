package com.zyc.springframework.service.Impl;


import com.zyc.springframework.Mapper.DataMapper;
import com.zyc.springframework.Pojo.product;
import com.zyc.springframework.service.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ServiceImpl implements DataService {

    private final DataMapper dataMapper;


    @Override
    public product getProducts(Integer id) {
        product products = dataMapper.getList(id);
        return products;
    }
}
