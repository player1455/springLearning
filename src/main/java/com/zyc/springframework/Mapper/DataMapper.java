package com.zyc.springframework.Mapper;

import com.zyc.springframework.Pojo.product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface DataMapper {



    @Select("select * from product where id=#{id}")
    product getList(Integer id);

}
