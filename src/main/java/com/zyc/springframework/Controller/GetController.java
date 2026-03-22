package com.zyc.springframework.Controller;

import com.zyc.springframework.Pojo.Result;
import com.zyc.springframework.Pojo.product;
import com.zyc.springframework.anno.GlobalExceptionHanding;
import com.zyc.springframework.anno.RepeatLimit;
import com.zyc.springframework.service.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class GetController {


    private final DataService dataService;



    public GetController(@Qualifier("serviceImpl") DataService dataService) {
        this.dataService = dataService;
    }


    @RepeatLimit(expireSeconds = 10)
    @GlobalExceptionHanding
    @GetMapping("/api/{id}")
    public Result get(@PathVariable int id) {
        product product = dataService.getProducts(id);
        return Result.success(product);
    }

    @GlobalExceptionHanding
    @GetMapping("/api/test")
    public Result TestGlobalExceptionHanding() {
        int i = 1 / 0 ;
        return Result.success(null);
    }

}
